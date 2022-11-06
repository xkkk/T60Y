package com.baorun.handbook.t60y.feature.scene

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
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
import com.baorun.handbook.t60y.feature.guide.EXPAND_COLLAPSE_PAYLOAD
import com.baorun.handbook.t60y.feature.guide.UserGuideAdapter
import com.baorun.handbook.t60y.feature.guide.UserGuideViewModel
import com.baorun.handbook.t60y.feature.guide.adapter.node.FirstNode
import com.baorun.handbook.t60y.feature.guide.adapter.node.ForthNode
import com.baorun.handbook.t60y.feature.guide.adapter.node.SecondNode
import com.baorun.handbook.t60y.feature.guide.adapter.node.ThirdNode
import com.baorun.handbook.t60y.feature.search.SearchActivity

/**
 * 场景详情页面
 */
class SceneDetailActivity : AppCompatActivity() {

    private val mViewModel: UserGuideViewModel by viewModels()
    private val mCollectViewModel: CollectionViewModel by viewModels()
    private lateinit var viewBinding: ActivityUserGuideBinding

    private val belong: String by lazy {
        intent.getStringExtra(KEY_BUNDLE_BELONG) ?: ""
    }

    //当前选中的id
    private val childrenId: String by lazy {
        intent.getStringExtra(KEY_BUNDLE_CHILDREN) ?: ""
    }

    //当前选中的父id
    private val parentId: String by lazy {
        intent.getStringExtra(KEY_BUNDLE_PARENT) ?: ""
    }

    //上次选中的位置
    private var lastPosition = 0

    //当前选中的数据
    private lateinit var curData: ChildrenData
    private lateinit var mAdapter: UserGuideAdapter
    private var isCollect = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityUserGuideBinding.inflate(layoutInflater)

        setContentView(viewBinding.root)
        initView()
        mViewModel.getSceneDataById(belong)
        mViewModel.sceneTreeDataLiveData.observe(this) {
            if (childrenId.isEmpty()) {
                val first = it.first() as FirstNode
                first.isExpanded = true
                if (first.data.htmlUrl.isNotEmpty()) {
                    loadWebView(first.data)
                    first.data.checked = true
                } else {
                    val second = first.childNode?.first() as SecondNode
                    second.isExpanded = true
                    if (second.data.htmlUrl.isNotEmpty()) {
                        loadWebView(second.data)
                        second.data.checked = true
                        lastPosition = 1
                    } else {
                        val third = second.childNode?.first() as ThirdNode
                        loadWebView(third.data)
                        third.data.checked = true
                        lastPosition = 2
                    }
                }
            } else {

                val first = it.first() as FirstNode
                first.isExpanded = true
                /* 1级 cj_1
                *  2级 cj_1.1
                *  3级 cj_1.1.1
                *  4级 cj_1.1.1.1
                 */
                val split = childrenId.split(".")
                when (split.size) {
                    1->{
                        val first = it.first() as FirstNode
                        val position = it.indexOf(first)
                        loadWebView(first.data)
                        first.data.checked = true
                        lastPosition = position
                    }
                    2 -> {
                        val second =
                            first.childNode!!.find { (it as SecondNode).data.id == childrenId } as SecondNode
                        loadWebView(second.data)
                        second.data.checked = true
                        lastPosition = first.childNode.indexOf(second) + 1
                    }
                    3 -> {
                        val second =
                            first.childNode!!.find { (it as SecondNode).data.id == parentId } as SecondNode
                        second.isExpanded = true
                        val third =
                            second.childNode?.find { (it as ThirdNode).data.id == childrenId } as ThirdNode
                        loadWebView(third.data)
                        third.data.checked = true
                        lastPosition =
                            first.childNode.indexOf(second) + 1 + second.childNode.indexOf(third) + 1
                    }
                    4 -> {
                        val secondId = split[0].plus(".").plus(split[1])
                        val second =
                            first.childNode!!.find { (it as SecondNode).data.id == secondId } as SecondNode
                        second.isExpanded = true
                        val third =
                            second.childNode?.find { (it as ThirdNode).data.id == parentId } as ThirdNode
                        third.isExpanded = true
                        val forth =
                            third.childNode?.find { (it as ForthNode).data.id == childrenId } as ForthNode
                        loadWebView(forth.data)
                        forth.data.checked = true
                        lastPosition =
                            first.childNode.indexOf(second) + 1 + second.childNode.indexOf(third) + 1 + third.childNode.indexOf(
                                forth
                            ) + 1
                    }
                }
            }
            mAdapter.setList(it)

        }

        mCollectViewModel.collectStatus.observe(this) {
            isCollect = it
            viewBinding.collectLayout.collectIv.isSelected = it
        }
    }


    private fun initView() {

        viewBinding.webView.apply {
            toSupportJavaScript()
        }
        viewBinding.recyclerView.apply {
            layoutManager = LinearLayoutManager(this@SceneDetailActivity)
            mAdapter = UserGuideAdapter()
            mAdapter.setOnItemClickListener { adapter, view, position ->
                val entity = adapter.data[position]

                if (position == lastPosition) {
                    mAdapter.expandOrCollapse(position, true, true, EXPAND_COLLAPSE_PAYLOAD)
                } else {
                    if (lastPosition != -1) {
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
                            onItemClick(entity.data, position)
                        }
                        is SecondNode -> {
                            entity.data.checked = true
                            onItemClick(entity.data, position)
                        }
                        is ThirdNode -> {
                            entity.data.checked = true
                            onItemClick(entity.data, position)
                        }
                        is ForthNode -> {
                            entity.data.checked = true
                            onItemClick(entity.data, position)
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
            if (isCollect) {
                mCollectViewModel.delete(curData)
            } else {
                mCollectViewModel.insert(curData)
            }

            mCollectViewModel.collectStatus.value = !isCollect
        }


    }

    private fun onItemClick(
        data: ChildrenData,
        position: Int
    ) {
        if (data.htmlUrl.isNotEmpty()) {
            loadWebView(data)
        } else {
            mAdapter.expandOrCollapse(position, true, true, EXPAND_COLLAPSE_PAYLOAD)
        }

    }


    private fun loadWebView(data: ChildrenData) {
        curData = data
        mCollectViewModel.isCollect(data.id)
        viewBinding.webView.loadUrl("file:///android_asset/changjing${data.htmlUrl}")
    }


    override fun onDestroy() {
        super.onDestroy()
        viewBinding.webView.removeAllViews()
        releaseAllWebViewCallback()
        viewBinding.webView.destroy()
    }

    companion object {
        fun goSceneDataActivity(act: Activity, belong: String) {
            Intent(act, SceneDetailActivity::class.java).apply {
                putExtra(KEY_BUNDLE_BELONG, belong)
            }.run {
                act.startActivity(this)
            }
        }

        /**
         * belong 所属模块
         * childrenId:当前选中的id
         * parentId:当前选中的id的父id
         */
        fun goSceneDataActivity(
            act: Activity,
            belong: String,
            childrenId: String,
            parentId: String
        ) {
            Intent(act, SceneDetailActivity::class.java).apply {
                putExtra(KEY_BUNDLE_BELONG, belong)
                putExtra(KEY_BUNDLE_CHILDREN, childrenId)
                putExtra(KEY_BUNDLE_PARENT, parentId)
            }.run {
                act.startActivity(this)
            }
        }
    }
}