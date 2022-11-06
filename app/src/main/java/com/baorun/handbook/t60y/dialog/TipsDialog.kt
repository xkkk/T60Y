package com.baorun.handbook.t60y.dialog

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.lifecycleScope
import com.baorun.handbook.t60y.KEY_BUNDLE_ID
import com.baorun.handbook.t60y.KEY_BUNDLE_TYPE
import com.baorun.handbook.t60y.data.DataManager
import com.baorun.handbook.t60y.databinding.DialogTipsBinding

/**
 * 功能：
 * 描述：
 * Created by xukun on 2021/8/16.
 */
class TipsDialog:DialogFragment() {

    private lateinit var viewBinding: DialogTipsBinding

    private val type:String by lazy {
        requireArguments().getString(KEY_BUNDLE_TYPE,"")
    }

    private val id:String by lazy {
        requireArguments().getString(KEY_BUNDLE_ID,"")
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewBinding = DialogTipsBinding.inflate(inflater,container,false)
        viewBinding.root.background.alpha = 200
        return viewBinding.root
    }

    override fun onStart() {
        super.onStart()
        dialog?.window?.let {
            it.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        }
    }



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
       lifecycleScope.launchWhenCreated {
           val pair = DataManager.getTipsPairData(type, id)
           viewBinding.titleTv.text = pair?.first
           viewBinding.contentTv.text = pair?.second
       }
    }



    fun showDialog(fm: FragmentManager){
        show(fm,"TipsDialog")
    }

    companion object{
        fun newInstance(type:String,id:String):TipsDialog{
            return TipsDialog().apply {
                arguments = Bundle().apply {
                    putString(KEY_BUNDLE_TYPE,type)
                    putString(KEY_BUNDLE_ID,id)
                }
            }

        }
    }
}