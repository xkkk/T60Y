package com.baorun.handbook.t60y.feature.guide

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.baorun.handbook.t60y.KEY_BUNDLE_BELONG
import com.baorun.handbook.t60y.KEY_BUNDLE_CHILDREN
import com.baorun.handbook.t60y.KEY_BUNDLE_PARENT
import com.baorun.handbook.t60y.data.ChildrenData
import com.baorun.handbook.t60y.databinding.ActivityUserGuideBinding
import com.baorun.handbook.t60y.ext.goActivity
import com.baorun.handbook.t60y.ext.releaseAllWebViewCallback
import com.baorun.handbook.t60y.ext.toSupportJavaScript
import com.baorun.handbook.t60y.feature.collect.CollectionViewModel
import com.baorun.handbook.t60y.feature.guide.adapter.node.FirstNode
import com.baorun.handbook.t60y.feature.guide.adapter.node.ForthNode
import com.baorun.handbook.t60y.feature.guide.adapter.node.SecondNode
import com.baorun.handbook.t60y.feature.guide.adapter.node.ThirdNode
import com.baorun.handbook.t60y.feature.search.SearchActivity
import com.blankj.utilcode.util.LogUtils


class UserGuideActivity : AppCompatActivity() {


    private val mViewModel: UserGuideViewModel by viewModels()
    private val mCollectViewModel: CollectionViewModel by viewModels()
    private lateinit var viewBinding: ActivityUserGuideBinding

    private var lastPosition = 0
    private lateinit var mAdapter: UserGuideAdapter

    private val belong: String by lazy {
        intent.getStringExtra(KEY_BUNDLE_BELONG) ?: ""
    }

    private val childrenId: String by lazy {
        intent.getStringExtra(KEY_BUNDLE_CHILDREN) ?: ""
    }
    private val parentId: String by lazy {
        intent.getStringExtra(KEY_BUNDLE_PARENT) ?: ""
    }

    private lateinit var curData: ChildrenData
    private var isCollect = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityUserGuideBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)
        lifecycleScope.launchWhenCreated {
            mViewModel.getData()
        }
        initView()
        mViewModel.treeDataLiveData.observe(this) {list->
            if(belong.isNotEmpty()){

                val first = list.find { (it as FirstNode).data.belong == belong }as FirstNode
                first.isExpanded = true
                /* 1级 cj_1
                *  2级 cj_1.1
                *  3级 cj_1.1.1
                *  4级 cj_1.1.1.1
                 */
                val split = childrenId.split(".")
                when(split.size){
                    1->{
                        curData = first.data
                        loadWebView(first.data.id,first.data.htmlUrl)
                        first.data.checked = true
                        lastPosition = list.indexOf(first)
                    }
                    2->{
                        val second = first.childNode!!.find { (it as SecondNode).data.id == childrenId } as SecondNode
                        curData = second.data
                        loadWebView(second.data.id,second.data.htmlUrl)
                        second.data.checked = true
                        lastPosition = list.indexOf(first)+first.childNode.indexOf(second)+1
                        LogUtils.i("position = ${mAdapter.data.indexOf(second)}","lastPosition = $lastPosition")
                    }
                    3->{
                        val second = first.childNode!!.find { (it as SecondNode).data.id == parentId } as SecondNode
                        second.isExpanded = true
                        val third = second.childNode?.find { (it as ThirdNode).data.id == childrenId } as ThirdNode
                        curData = third.data
                        loadWebView(third.data.id,third.data.htmlUrl)
                        third.data.checked = true
                        lastPosition = list.indexOf(first)+first.childNode.indexOf(second)+1+second.childNode.indexOf(third)+1
                        LogUtils.i("position = ${mAdapter.data.indexOf(third)}","lastPosition = $lastPosition")
                    }
                    4->{
                        val secondId = split[0].plus(".").plus(split[1])
                        val second = first.childNode!!.find { (it as SecondNode).data.id == secondId } as SecondNode
                        second.isExpanded = true
                        val third = second.childNode?.find { (it as ThirdNode).data.id == parentId } as ThirdNode
                        third.isExpanded = true
                        val forth = third.childNode?.find { (it as ForthNode).data.id == childrenId } as ForthNode
                        curData = forth.data
                        loadWebView(forth.data.id,forth.data.htmlUrl)
                        forth.data.checked = true
                        lastPosition = list.indexOf(first)+first.childNode.indexOf(second)+1+second.childNode.indexOf(third)+1+third.childNode.indexOf(forth)+1
                        LogUtils.i("position = ${mAdapter.data.indexOf(forth)}","lastPosition = $lastPosition")

                    }
                }
            }else {
                val first = list.first() as FirstNode
                first.data.checked = true
                curData = first.data
                loadWebView(first.data.id,first.data.htmlUrl)
            }


            mAdapter.setList(list)
        }

        mCollectViewModel.collectStatus.observe(this){
            isCollect = it
            viewBinding.collectLayout.collectIv.isSelected = it
        }

    }

    private fun initView() {
        viewBinding.webView.apply {
            toSupportJavaScript()
        }
        viewBinding.recyclerView.apply {
            layoutManager = LinearLayoutManager(this@UserGuideActivity)
            mAdapter = UserGuideAdapter()
            mAdapter.setOnItemClickListener { adapter, _, position ->
                val entity = adapter.data[position]

                if(position==lastPosition){
                    mAdapter.expandOrCollapse(position, true, true, EXPAND_COLLAPSE_PAYLOAD)
                }else {

                    if(lastPosition!=-1) {
                        when (val lastNode = adapter.data[lastPosition]) {
                            is FirstNode -> lastNode.data.checked = false
                            is SecondNode -> lastNode.data.checked = false
                            is ThirdNode -> lastNode.data.checked = false
                            is ForthNode -> lastNode.data.checked = false
                        }
                    }

                    when (entity) {
                        is FirstNode -> {
                            entity.data.checked = true
                            if(entity.data.htmlUrl.isNotEmpty()) {
                                curData = entity.data
                            }
                            onItemClick(entity.data.id,entity.data.htmlUrl, position)
                        }
                        is SecondNode -> {
                            entity.data.checked = true
                            if(entity.data.htmlUrl.isNotEmpty()) {
                                curData = entity.data
                            }
                            onItemClick(entity.data.id,entity.data.htmlUrl, position)
                        }
                        is ThirdNode -> {
                            entity.data.checked = true
                            if(entity.data.htmlUrl.isNotEmpty()) {
                                curData = entity.data
                            }
                            onItemClick(entity.data.id,entity.data.htmlUrl, position)
                        }
                        is ForthNode -> {
                            entity.data.checked = true
                            if(entity.data.htmlUrl.isNotEmpty()) {
                                curData = entity.data
                            }
                            onItemClick(entity.data.id,entity.data.htmlUrl, position)
                        }
                    }
                    mAdapter.notifyDataSetChanged()
                    lastPosition = position
                }
            }
            adapter = mAdapter

        }

        viewBinding.collectLayout.searchIv.setOnClickListener {
            goActivity(SearchActivity::class.java)
        }

        viewBinding.collectLayout.collectIv.setOnClickListener {
            if(isCollect){
                mCollectViewModel.delete(curData)
            }else{
                mCollectViewModel.insert(curData)
            }
            mCollectViewModel.collectStatus.value = !isCollect
        }
    }

    private fun onItemClick(
        id: String,
        htmlUrl: String,
        position: Int
    ) {
        if (htmlUrl.isNotEmpty()) {
            loadWebView(id,htmlUrl)
        } else {
            mAdapter.expandOrCollapse(position, true, true, EXPAND_COLLAPSE_PAYLOAD)
        }

    }


    private fun loadWebView(id:String,url: String) {
        mCollectViewModel.isCollect(id)
        viewBinding.webView.loadUrl("file:///android_asset/gongneng$url")
    }

    override fun onDestroy() {
        super.onDestroy()
        viewBinding.webView.removeAllViews()
        releaseAllWebViewCallback()
        viewBinding.webView.destroy()
    }



    companion object{
        fun goUserGuideActivity(act: Activity,belong: String,childrenId:String,parentId:String){
            Intent(act,UserGuideActivity::class.java).apply {
                putExtra(KEY_BUNDLE_BELONG,belong)
                putExtra(KEY_BUNDLE_CHILDREN,childrenId)
                putExtra(KEY_BUNDLE_PARENT,parentId)
            }.run {
                act.startActivity(this)
            }
        }
    }
}