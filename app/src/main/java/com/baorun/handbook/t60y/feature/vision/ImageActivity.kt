package com.baorun.handbook.t60y.feature.vision

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatImageView
import androidx.viewpager.widget.PagerAdapter
import com.baorun.handbook.t60y.KEY_BUNDLE_RES
import com.baorun.handbook.t60y.R
import com.baorun.handbook.t60y.databinding.ActivityImageBinding
import com.baorun.handbook.t60y.ext.loadImage
import com.zhpan.indicator.enums.IndicatorSlideMode
import com.zhpan.indicator.enums.IndicatorStyle

/**
 * 功能：
 * 描述：
 * Created by xukun on 2021/8/18.
 */
class ImageActivity:AppCompatActivity() {
    private lateinit var viewBinding: ActivityImageBinding


    private val list by lazy {
         intent.getIntegerArrayListExtra(KEY_BUNDLE_RES)?: emptyList()
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityImageBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)

        viewBinding.viewPager.apply {

//            offscreenPageLimit = list.size-1

            adapter = object :PagerAdapter(){
                override fun getCount(): Int  = list.size

                override fun isViewFromObject(view: View, any: Any): Boolean {
                    return view ==any
                }

                override fun instantiateItem(container: ViewGroup, position: Int): Any {
                    val iv = AppCompatImageView(this@ImageActivity)
                    loadImage(this@ImageActivity,list[position],iv)
                    container.addView(iv)
                    return iv
                }

                override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
                    container.removeView(`object` as View)
                }

            }

        }

        viewBinding.indicatorView.apply {
            setSliderColor(resources.getColor(R.color.indicator_scene_nor,null), resources.getColor(
                R.color.indicator_scene_sel,null))
            setSliderWidth(resources.getDimension(R.dimen.margin_10),resources.getDimension(R.dimen.margin_20),)
            setSliderGap(resources.getDimension(R.dimen.margin_20))
            setSliderHeight(resources.getDimension(R.dimen.margin_5))
            setSlideMode(IndicatorSlideMode.SCALE)
            setIndicatorStyle(IndicatorStyle.CIRCLE)
            setupWithViewPager(viewBinding.viewPager)
        }
    }


    companion object{
        fun startImageActivity(activity:Activity,res:ArrayList<Int>){
            Intent(activity,ImageActivity::class.java).apply {
                putIntegerArrayListExtra(KEY_BUNDLE_RES,res)
            }.run {
                activity.startActivity(this)
            }
        }
    }
}