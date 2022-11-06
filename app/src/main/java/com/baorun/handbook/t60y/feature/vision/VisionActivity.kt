package com.baorun.handbook.t60y.feature.vision

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentTransaction
import com.baorun.handbook.t60y.R
import com.baorun.handbook.t60y.databinding.ActivityVisionBinding
import com.baorun.handbook.t60y.ext.loadBackground


/**
 * 功能：
 * 描述：360 视觉效果
 * Created by xukun on 2021/8/16.
 */
class VisionActivity:AppCompatActivity() {

    private lateinit var viewBinding : ActivityVisionBinding

    private  var mFragment:VisionInFragment? = null
    private val mInFragment:VisionInFragment by lazy {
        VisionInFragment.newInstance(VisionInFragment.TYPE_IN)
    }
    private val mOutFragment:VisionInFragment by lazy {
        VisionInFragment.newInstance(VisionInFragment.TYPE_OUT)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding  = ActivityVisionBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)
        loadBackground(this,R.drawable.assets_images_360_bg,1920,720,viewBinding.root)
//        replaceFragment(VisionInFragment.newInstance(VisionInFragment.TYPE_OUT))
        switchFragment(R.id.outRb)
        viewBinding.visionGroup.setOnCheckedChangeListener { _, checkedId ->
            switchFragment(checkedId)
        }
    }

    private fun switchFragment(checkedId: Int) {
        when (checkedId) {
            R.id.inRb -> {
                viewBinding.outRb.background.alpha = 140
                viewBinding.inRb.background.alpha = 255
                replaceFragment(mInFragment).commit()
            }
            R.id.outRb -> {
                viewBinding.inRb.background.alpha = 140
                viewBinding.outRb.background.alpha = 255
                replaceFragment(mOutFragment).commit()
            }

        }
    }

    private fun replaceFragment(fragment: VisionInFragment): FragmentTransaction {
        val transaction =  supportFragmentManager.beginTransaction()
//        transaction.replace(R.id.container,fragment)
        if(!fragment.isAdded){
            if(mFragment!=null){
                transaction.hide(mFragment!!)
            }
            transaction.add(R.id.container,fragment,"VisionInFragment")
        }else{
            transaction.hide(mFragment!!).show(fragment)
        }
        mFragment = fragment
        return transaction
    }
}