package com.baorun.handbook.t60y.feature.vision

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.DrawableRes
import androidx.fragment.app.Fragment
import com.baorun.handbook.t60y.KEY_BUNDLE_RES
import com.baorun.handbook.t60y.databinding.FragmentImageBinding
import com.baorun.handbook.t60y.ext.loadBackground

/**
 * 功能：
 * 描述：
 * Created by xukun on 2021/8/18.
 */
class ImageFragment:Fragment() {

    private lateinit var viewBinding: FragmentImageBinding

    private val backgroundRes:Int by lazy {
        requireArguments().getInt(KEY_BUNDLE_RES)
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        viewBinding = FragmentImageBinding.inflate(inflater,container,false)
        return viewBinding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        viewBinding.mImageView.setBackgroundResource(backgroundRes)
        loadBackground(requireActivity(),backgroundRes,viewBinding.mImageView)
    }


    companion object{
        fun newInstance(@DrawableRes res:Int):ImageFragment{
            return ImageFragment().apply {
                arguments = Bundle().apply {
                    putInt(KEY_BUNDLE_RES,res)
                }
            }
        }
    }
}