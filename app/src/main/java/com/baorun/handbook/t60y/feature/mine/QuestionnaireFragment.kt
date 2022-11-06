package com.baorun.handbook.t60y.feature.mine

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.baorun.handbook.t60y.databinding.FragmentQuestionnaireBinding

/**
 * 功能：
 * 描述：问卷调查 二维码
 * Created by xukun on 2021/8/15.
 */
class QuestionnaireFragment:Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = FragmentQuestionnaireBinding.inflate(inflater,container,false)
        return view.root
    }
}