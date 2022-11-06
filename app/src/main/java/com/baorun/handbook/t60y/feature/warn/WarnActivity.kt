package com.baorun.handbook.t60y.feature.warn

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.baorun.handbook.t60y.KEY_BUNDLE_ID
import com.baorun.handbook.t60y.data.ChildrenData
import com.baorun.handbook.t60y.databinding.ActivityWarnBinding
import com.baorun.handbook.t60y.ext.toSupportJavaScript
import com.baorun.handbook.t60y.feature.collect.CollectionViewModel

/**
 * 功能：
 * 描述：
 * Created by xukun on 2021/8/20.
 */
class WarnActivity:AppCompatActivity() {

    private lateinit var viewBinding: ActivityWarnBinding

    private lateinit var mAdapter: WarnListAdapter
    private val viewModel:WarnViewModel by viewModels()
    private val mCollectViewModel:CollectionViewModel by viewModels()

    private val id:String? by lazy {
        intent.getStringExtra(KEY_BUNDLE_ID)
    }


    private var lastPos = 0
    private var isCollect = false
    private lateinit var curData:ChildrenData
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityWarnBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)


        viewModel.getWarnList()

        initView()

        initData()
    }

    private fun initData() {
        viewModel.warnListLiveData.observe(this){
            if(id.isNullOrEmpty()){
                it.first().checked = true
                lastPos = 0
                curData = it.first()
                loadWebView(it.first().id,it.first().htmlUrl)
            }else{
                val currentPos =  it.indexOfFirst { it.id == id }
                it[currentPos].checked = true
                lastPos = currentPos
                curData = it[currentPos]
                loadWebView(it[currentPos].id,it[currentPos].htmlUrl)
            }

            mAdapter.setList(it)

        }

        mCollectViewModel.collectStatus.observe(this){
            isCollect = it
            viewBinding.collectLayout.collectIv.isSelected = it
        }
    }

    private fun initView() {

        viewBinding.collectLayout.searchIv.visibility = View.GONE
        viewBinding.collectLayout.collectIv.setOnClickListener {
            if(isCollect){
                mCollectViewModel.delete(curData)
            }else{
                mCollectViewModel.insert(curData)
            }

            mCollectViewModel.collectStatus.value = !isCollect
        }

        viewBinding.webView.apply {
           toSupportJavaScript()
        }
        viewBinding.recyclerView.apply {
            layoutManager = LinearLayoutManager(this@WarnActivity)

             mAdapter = WarnListAdapter()
            mAdapter.setOnItemClickListener { adapter, view, position ->
                if(position == lastPos){
                    return@setOnItemClickListener
                }
                val data = mAdapter.data[position]
                val lastData = mAdapter.data[lastPos]
                data.checked = true
                lastData.checked = false
                mAdapter.notifyItemChanged(position)
                mAdapter.notifyItemChanged(lastPos)

                curData = data
                loadWebView(data.id,data.htmlUrl)

                lastPos = position
            }
            adapter = mAdapter
        }
    }

    private fun loadWebView(id: String,url:String){
        mCollectViewModel.isCollect(id)
        viewBinding.webView.loadUrl("file:///android_asset/warn$url")
    }

    companion object{
        fun goWarnActivity(act:Activity,id:String?=null){
            Intent(act,WarnActivity::class.java).apply {
                if(id!=null)
                putExtra(KEY_BUNDLE_ID,id)
            }.run {
                act.startActivity(this)
            }
        }
    }
}