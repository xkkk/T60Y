package com.baorun.handbook.t60y.feature.scene

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.baorun.handbook.t60y.databinding.FragmentSceneBinding
import com.baorun.handbook.t60y.feature.scene.adapter.SceneAdapter
import com.baorun.handbook.t60y.feature.scene.vm.SceneViewModel
import com.fondesa.recyclerviewdivider.dividerBuilder

/**
 * 功能：
 * 描述：场景列表
 * Created by xukun on 2021/8/15.
 */
class SceneFragment : Fragment() {

    private lateinit var viewBinding: FragmentSceneBinding
    private lateinit var mAdapter: SceneAdapter

    private val mViewModel: SceneViewModel by viewModels()

    private val page: Int by lazy {
        requireArguments().getInt(KEY_PAGE, 1)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewBinding = FragmentSceneBinding.inflate(inflater, container, false)
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        lifecycleScope.launchWhenCreated {
            with(viewBinding.recyclerView) {
                requireActivity().dividerBuilder().asSpace().size(10).build().addTo(this)
                mAdapter = SceneAdapter()
                mAdapter.setOnItemClickListener { _, _, position ->
                    val data = mAdapter.data[position]
                    SceneDetailActivity.goSceneDataActivity(requireActivity(), data.belong)
                }
                adapter = mAdapter


            }

            mViewModel.getSceneList(page)
            mViewModel.sceneListLiveDataNew.observe(viewLifecycleOwner) {
                mAdapter.setList(it)
            }
        }


    }

    companion object {
        const val KEY_PAGE = "key_page"

        fun newInstance(page: Int): SceneFragment {
            return SceneFragment().apply {
                arguments = Bundle().apply {
                    putInt(KEY_PAGE, page)
                }
            }
        }
    }
}