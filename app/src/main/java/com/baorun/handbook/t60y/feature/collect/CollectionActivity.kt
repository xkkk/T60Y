package com.baorun.handbook.t60y.feature.collect

import android.os.Bundle
import android.util.TypedValue
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.baorun.handbook.t60y.R
import com.baorun.handbook.t60y.data.ChildrenData
import com.baorun.handbook.t60y.databinding.ActivityCollectBinding
import com.baorun.handbook.t60y.databinding.IncludeEmptyLayoutBinding
import com.baorun.handbook.t60y.feature.guide.UserGuideActivity
import com.baorun.handbook.t60y.feature.question.AnswerActivity
import com.baorun.handbook.t60y.feature.scene.SceneDetailActivity
import com.baorun.handbook.t60y.feature.video.MediaPlayerActivity
import com.baorun.handbook.t60y.feature.warn.WarnActivity
import com.fondesa.recyclerviewdivider.dividerBuilder

class CollectionActivity:AppCompatActivity() {


    private lateinit var mAdapter: CollectionAdapter

    private lateinit var viewBinding:ActivityCollectBinding

    private val mViewModel:CollectionViewModel by viewModels()


    private val emptyView: View by lazy {
        val viewBinding = IncludeEmptyLayoutBinding.inflate(layoutInflater)
        viewBinding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewBinding = ActivityCollectBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)


        viewBinding.recyclerView.apply {
            layoutManager = LinearLayoutManager(this@CollectionActivity)
            dividerBuilder().size(20,TypedValue.COMPLEX_UNIT_DIP).asSpace().build().addTo(this)
            mAdapter = CollectionAdapter()
            mAdapter.setOnItemClickListener { adapter, view, position ->
                val data = mAdapter.data[position]
                goDetailPage(data)
            }
            mAdapter.setOnItemChildClickListener { adapter, view, position ->
                if(view.id == R.id.deleteIv){
                    val data = mAdapter.data[position]

                    mViewModel.delete(data)

                    mAdapter.data.removeAt(position)

                    mAdapter.notifyItemRemoved(position)
                    if(mAdapter.data.isEmpty()){
                        mViewModel.collectionList.value = emptyList()
                    }
                }
            }
            adapter = mAdapter
        }

        mViewModel.collectionList.observe(this){
            if(it.isNullOrEmpty()){
                mAdapter.setNewInstance(null)
                mAdapter.setEmptyView(emptyView)
            }else{
                mAdapter.setList(it)
            }
        }
    }


    override fun onResume() {
        super.onResume()
        mViewModel.getCollectionList()
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
             "wt"->AnswerActivity.startAnswerActivity(this,data.belong,data.id)
            "gj"->WarnActivity.goWarnActivity(this,data.id)
        }
    }
}