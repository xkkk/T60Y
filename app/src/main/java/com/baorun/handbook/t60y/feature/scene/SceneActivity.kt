package com.baorun.handbook.t60y.feature.scene

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.baorun.handbook.t60y.R
import com.baorun.handbook.t60y.databinding.ActivitySceneBinding
import com.baorun.handbook.t60y.ext.last
import com.baorun.handbook.t60y.ext.next
import com.zhpan.indicator.enums.IndicatorSlideMode
import com.zhpan.indicator.enums.IndicatorStyle

/**
 * 功能：
 * 描述：场景列表页
 * Created by xukun on 2021/8/15.
 */
class SceneActivity:AppCompatActivity() {

    private lateinit var viewBinding: ActivitySceneBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivitySceneBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)
        viewBinding.viewPager.apply {
//            offscreenPageLimit = 2
            val fragments = listOf(SceneFragment.newInstance(1), SceneFragment.newInstance(2))
            adapter = object :FragmentStateAdapter(this@SceneActivity){
                override fun getItemCount(): Int  = 2
                override fun createFragment(position: Int) =  fragments[position]
            }
            registerOnPageChangeCallback(object :ViewPager2.OnPageChangeCallback(){
                override fun onPageSelected(position: Int) {
                    super.onPageSelected(position)
                    if(position==0){
                        viewBinding.lastPageIv.visibility = View.GONE
                        viewBinding.nextPageIv.visibility = View.VISIBLE
                    }else if(position==fragments.size-1){
                        viewBinding.lastPageIv.visibility = View.VISIBLE
                        viewBinding.nextPageIv.visibility = View.GONE
                    }else{
                        viewBinding.lastPageIv.visibility = View.VISIBLE
                        viewBinding.nextPageIv.visibility = View.VISIBLE
                    }
                }
            })
        }
        viewBinding.indicatorView.apply {
                setSliderColor(resources.getColor(R.color.indicator_scene_nor,null), resources.getColor(R.color.indicator_scene_sel,null))
                setSliderWidth(resources.getDimension(R.dimen.margin_10),resources.getDimension(R.dimen.margin_20),)
                setSliderGap(resources.getDimension(R.dimen.margin_20))
                setSliderHeight(resources.getDimension(R.dimen.margin_5))
                setSlideMode(IndicatorSlideMode.SCALE)
                setIndicatorStyle(IndicatorStyle.CIRCLE)
                setupWithViewPager(viewBinding.viewPager)
        }
        viewBinding.lastPageIv.setOnClickListener {
            viewBinding.viewPager.last()
        }

        viewBinding.nextPageIv.setOnClickListener {
            viewBinding.viewPager.next()
        }
    }


}


