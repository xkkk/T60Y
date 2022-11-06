package com.baorun.handbook.t60y.feature.search

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import com.baorun.handbook.t60y.R
import com.baorun.handbook.t60y.data.ChildrenData
import com.baorun.handbook.t60y.databinding.ActivitySearchBinding
import com.baorun.handbook.t60y.databinding.IncludeEmptyLayoutBinding
import com.baorun.handbook.t60y.feature.guide.UserGuideActivity
import com.baorun.handbook.t60y.feature.scene.SceneDetailActivity
import com.baorun.handbook.t60y.feature.video.MediaPlayerActivity
import com.fondesa.recyclerviewdivider.dividerBuilder

class SearchActivity : AppCompatActivity() {

    private lateinit var viewBinding: ActivitySearchBinding

    private lateinit var mResultAdapter: SearchResultAdapter
    private lateinit var mHistoryAdapter: SearchResultAdapter

    private val mViewModel: SearchViewModel by viewModels()

    private val emptyView: View by lazy {
        val viewBinding = IncludeEmptyLayoutBinding.inflate(layoutInflater)
        viewBinding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivitySearchBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)
        initView()
//        lifecycleScope.launchWhenCreated {
//            mViewModel.searchResultList.value = emptyList()
//        }
    }

    private fun initView() {
        with(viewBinding) {

            with(searchResultList) {
                dividerBuilder().asSpace().size(20).build().addTo(this)
                mResultAdapter = SearchResultAdapter(R.layout.item_search_result)
                mResultAdapter.setOnItemClickListener { adapter, view, position ->
                    val bean = mResultAdapter.data[position]
                    goDetailPage(bean)
                    mViewModel.insertHistory(bean)
                }
                adapter = mResultAdapter
            }
            with(historyList) {
                dividerBuilder().asSpace().size(20).build().addTo(this)
                mHistoryAdapter = SearchResultAdapter(R.layout.item_search_history)
                mHistoryAdapter.setOnItemClickListener { adapter, view, position ->
                    val bean = mHistoryAdapter.data[position]
                    goDetailPage(bean)
                }
                adapter = mHistoryAdapter
            }

            searchView.addTextChangedListener {
                val key = it?.toString()?.trim()
                if (!key.isNullOrEmpty()) {
                    closeIv.visibility = View.VISIBLE
                    mViewModel.search(key)
                } else {
                    mViewModel.searchResultList.value = emptyList()
                    closeIv.visibility = View.GONE
                }
            }

            closeIv.setOnClickListener {
                searchView.setText("")
            }
        }


        mViewModel.searchResultList.observe(this) {
            if (it.isNotEmpty()) {
                mResultAdapter.setList(it)
            } else {
                mResultAdapter.setNewInstance(null)
                mResultAdapter.setEmptyView(emptyView)
            }
        }

        mViewModel.historyList.observe(this) {
            if (it.isNotEmpty()) {
                mHistoryAdapter.setList(it)
            }
        }
    }

    override fun onResume() {
        super.onResume()
        mViewModel.getHistoryList()
    }

    private fun goDetailPage(data: ChildrenData) {

        when (data.belong.split("_")[0]) {
            "cj" -> SceneDetailActivity.goSceneDataActivity(
                this,
                data.belong,
                data.id,
                data.parentId
            )
            "sp" -> MediaPlayerActivity.playerActivity(this, data.belong,
                data.id,"video" + data.htmlUrl, data.coverRes ?: -1)
            "gn" -> UserGuideActivity.goUserGuideActivity(this, data.belong, data.id, data.parentId)
        }
    }

}
