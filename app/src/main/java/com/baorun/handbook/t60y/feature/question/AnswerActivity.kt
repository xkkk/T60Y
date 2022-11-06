package com.baorun.handbook.t60y.feature.question

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.baorun.handbook.t60y.KEY_BUNDLE_BELONG
import com.baorun.handbook.t60y.KEY_BUNDLE_ID
import com.baorun.handbook.t60y.databinding.ActivityAnswerBinding
import com.baorun.handbook.t60y.ext.goActivity
import com.baorun.handbook.t60y.ext.releaseAllWebViewCallback
import com.baorun.handbook.t60y.ext.toSupportJavaScript
import com.baorun.handbook.t60y.feature.collect.CollectionViewModel
import com.baorun.handbook.t60y.feature.search.SearchActivity

/**
 * 功能：
 * 描述：常见问题详情页
 * Created by xukun on 2021/8/20.
 */
class AnswerActivity: AppCompatActivity() {
    private lateinit var viewBinding: ActivityAnswerBinding

    private val mCollectionViewModel :CollectionViewModel by viewModels()

    private var isCollect = false

    private val id by lazy {
        intent.getStringExtra(KEY_BUNDLE_ID)?: ""
    }

    private val belong by lazy {
        intent.getStringExtra(KEY_BUNDLE_BELONG)?: ""
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        viewBinding = ActivityAnswerBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)

        lifecycleScope.launchWhenCreated {
            mCollectionViewModel.getCurrentData(belong, id)
            mCollectionViewModel.isCollect(id)
        }

        initView()

        mCollectionViewModel.currentData.observe(this){

            viewBinding.webView.loadUrl("file:///android_asset/wenti${it.htmlUrl}")

        }

        mCollectionViewModel.collectStatus.observe(this){
            isCollect = it
            viewBinding.collectLayout.collectIv.isSelected = it
        }

    }

    private fun initView() {

        viewBinding.webView.apply {
            toSupportJavaScript()
        }

        viewBinding.collectLayout.searchIv.setOnClickListener {
            goActivity(SearchActivity::class.java)
        }
        viewBinding.collectLayout.collectIv.setOnClickListener {
            if(isCollect){
                mCollectionViewModel.delete(belong, id)
            }else{
                mCollectionViewModel.insert(belong, id)
            }

            mCollectionViewModel.collectStatus.value = !isCollect
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        viewBinding.webView.removeAllViews()
        releaseAllWebViewCallback()
        viewBinding.webView.destroy()
    }

    companion object{
        fun startAnswerActivity(activity: Activity, belong:String,id:String){
            Intent(activity, AnswerActivity::class.java).apply {
                putExtra(KEY_BUNDLE_ID,id)
                putExtra(KEY_BUNDLE_BELONG,belong)
            }.run {
                activity.startActivity(this)
            }
        }
    }
}