package com.baorun.handbook.t60y.feature.warn

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.baorun.handbook.t60y.databinding.ActivitySchemeWarnBinding
import com.baorun.handbook.t60y.ext.goActivity
import com.baorun.handbook.t60y.ext.toSupportJavaScript
import com.baorun.handbook.t60y.feature.home.MainActivity


/**
 * 通过scheme 打开app 跳转的页面
 */
class SchemeWarnActivity:AppCompatActivity() {

    private lateinit var viewBinding:ActivitySchemeWarnBinding

    private val viewModel:WarnViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivitySchemeWarnBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)
        viewBinding.webView.toSupportJavaScript()

        parserData(intent)

        viewModel.warnData.observe(this){
            if(it!=null){
                showEmpty(false)
                viewBinding.webView.loadUrl("file:///android_asset/warn${it.htmlUrl}")
            }else{
                showEmpty()
            }
        }
    }


    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        if(intent!=null){
            parserData(intent)
        }
    }

    private fun parserData(intent:Intent){
        val data = intent.data
        if(data!=null){
            val path =  data.path
            if(path!=null){
                val split = path.split("/")
                if(split.isNotEmpty()){
                    val id = split.last().trim()
                    viewModel.getWarnById(id)
                }else{
                    showEmpty()
                }
            }else{
                showEmpty()
            }

        }else{
            showEmpty()
        }

    }

    override fun onBackPressed() {
//        super.onBackPressed()
        goActivity(MainActivity::class.java,true)
        finish()
    }
    private fun showEmpty(visible:Boolean = true){
        if(visible) {
            viewBinding.webView.visibility = View.GONE
            viewBinding.empty.root.visibility = View.VISIBLE
        }else{
            viewBinding.webView.visibility = View.VISIBLE
            viewBinding.empty.root.visibility = View.GONE
        }
    }
}