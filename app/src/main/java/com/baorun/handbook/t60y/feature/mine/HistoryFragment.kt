package com.baorun.handbook.t60y.feature.mine

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.baorun.handbook.t60y.R
import com.baorun.handbook.t60y.databinding.FragmentHistoryBinding
import com.baorun.handbook.t60y.databinding.IncludeEmptyLayoutBinding
import com.blankj.utilcode.util.LogUtils
import com.fondesa.recyclerviewdivider.dividerBuilder

/**
 * 功能：
 * 描述：反馈历史
 * Created by xukun on 2021/8/15.
 */
class HistoryFragment:Fragment() {
    private lateinit var viewBinding: FragmentHistoryBinding

    private lateinit var mAdapter:FeedbackHistoryAdapter

    private val mViewModel:FeedbackViewModel by viewModels()


    private val emptyView: View by lazy {
        val viewBinding = IncludeEmptyLayoutBinding.inflate(layoutInflater)
        viewBinding.root
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewBinding = FragmentHistoryBinding.inflate(inflater,container,false)
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        LogUtils.i("onViewCreated")
        mViewModel.feedbackList()
        viewBinding.recyclerView.apply {
            requireActivity().dividerBuilder().size(20).asSpace().build().addTo(this)
            mAdapter = FeedbackHistoryAdapter()
            mAdapter.setOnItemChildClickListener { _, view, position ->
                if(view.id== R.id.deleteIv){
                    val data = mAdapter.data[position]

                    mViewModel.deleteFeedback(data.id)

                    mAdapter.data.removeAt(position)

                    mAdapter.notifyItemRemoved(position)
                    if(mAdapter.data.isEmpty()){
                        mViewModel.feedbackList.value = emptyList()
                    }
                }

            }
            adapter = mAdapter
        }


        mViewModel.feedbackList.observe(viewLifecycleOwner){
            if(it.isNullOrEmpty()){
                mAdapter.setNewInstance(null)
                mAdapter.setEmptyView(emptyView)
            }else{
                mAdapter.setList(it)
            }
        }

//        mViewModel.refreshData.observe(viewLifecycleOwner){
//            mViewModel.feedbackList()
//        }
    }

    override fun onHiddenChanged(hidden: Boolean) {
        super.onHiddenChanged(hidden)
        LogUtils.i("onHiddenChanged")
        if(!hidden){
            mViewModel.feedbackList()
        }
    }
}