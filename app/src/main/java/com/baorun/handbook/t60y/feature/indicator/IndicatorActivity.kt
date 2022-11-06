package com.baorun.handbook.t60y.feature.indicator

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.View
import android.widget.FrameLayout
import androidx.annotation.DrawableRes
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.baorun.handbook.t60y.R
import com.baorun.handbook.t60y.data.*
import com.baorun.handbook.t60y.databinding.ActivityIndicatorBinding
import com.baorun.handbook.t60y.dialog.TipsDialog
import com.baorun.handbook.t60y.radius
import com.baorun.handbook.t60y.widget.GlideApp
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.CustomViewTarget
import com.bumptech.glide.request.transition.Transition
import kotlinx.coroutines.launch
import kotlin.math.roundToInt

/**
 * 功能：
 * 描述：
 * Created by xukun on 2021/8/16.
 */
class IndicatorActivity : AppCompatActivity() {


    private var hotspotLayoutWidth: Int = 1920
    private var hotspotLayoutHeight: Int = 720

    private lateinit var viewBinding: ActivityIndicatorBinding

    val options = RequestOptions()
        .skipMemoryCache(false) //是否允许内存缓存
        .onlyRetrieveFromCache(false) //是否只从缓存加载图片
        .diskCacheStrategy(DiskCacheStrategy.RESOURCE) //禁止磁盘缓存

    private var currentState: Int = 0 //0-3 分别代表红、黄、绿、蓝白
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityIndicatorBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)

        loadBackground()

        initView()
    }

    private fun initView() {
        //默认红色
        loadLayer(viewBinding.background, R.drawable.assets_images_zhishideng_bg_red)
        viewBinding.radioGroup.setOnCheckedChangeListener { group, checkedId ->
            when (checkedId) {
                R.id.redRb -> {
                    viewBinding.indicatorTips.text =
                        getString(R.string.indicator_choose_style, "红色")
                    loadLayer(viewBinding.background, R.drawable.assets_images_zhishideng_bg_red)
                    resetView(IndicatorStyle.RED)
                }
                R.id.yellowRb -> {
                    viewBinding.indicatorTips.text =
                        getString(R.string.indicator_choose_style, "黄色")
                    loadLayer(viewBinding.background, R.drawable.assets_images_zhishideng_bg_yellow)
                    resetView(IndicatorStyle.YELLOW)
                }
                R.id.greenRb -> {
                    viewBinding.indicatorTips.text =
                        getString(R.string.indicator_choose_style, "绿色")
                    loadLayer(viewBinding.background, R.drawable.assets_images_zhishideng_bg_green)
                    resetView(IndicatorStyle.GREEN)
                }
                R.id.blueRb -> {
                    viewBinding.indicatorTips.text =
                        getString(R.string.indicator_choose_style, "蓝&白色")
                    loadLayer(viewBinding.background, R.drawable.assets_images_zhishideng_bg_blue)
                    resetView(IndicatorStyle.BLUE)
                }
            }
        }
    }

    private fun loadBackground() {
        GlideApp.with(this).load(R.drawable.assets_images_zhishideng_zhishideng_1)
            .apply(options)
            .into(object : CustomViewTarget<FrameLayout,Drawable>(viewBinding.rootView){
                override fun onLoadFailed(errorDrawable: Drawable?) {

                }

                override fun onResourceReady(
                    resource: Drawable,
                    transition: Transition<in Drawable>?
                ) {
                    viewBinding.rootView.background = resource
                    viewBinding.rootView.post {
                        lifecycleScope.launch {
                            setBgSize()
                            resetView(IndicatorStyle.RED)
                        }
                    }
                }

                override fun onResourceCleared(placeholder: Drawable?) {
                    viewBinding.rootView.background = null
                }

            })
    }




    /**
     * 将热点区域设置的和图片一样大小
     */
    private fun setBgSize() {

        with(viewBinding) {
            val width = rootView.width
            val height = rootView.height
            val lp = background.layoutParams
            lp.width = width
            lp.height = height
            background.layoutParams = lp
            hotspotLayoutWidth = width
            hotspotLayoutHeight = height
        }

    }


    fun loadLayer(layout: FrameLayout, @DrawableRes res: Int) {
        GlideApp.with(this).load(res).apply(options).override(hotspotLayoutWidth,hotspotLayoutHeight)
            .into(object : CustomViewTarget<FrameLayout, Drawable>(layout) {
                override fun onLoadFailed(errorDrawable: Drawable?) {

                }

                override fun onResourceReady(
                    resource: Drawable,
                    transition: Transition<in Drawable>?
                ) {
                    layout.background = resource
                }

                override fun onResourceCleared(placeholder: Drawable?) {
                    layout.background = placeholder

                }

            })
    }

    private fun resetView(style: IndicatorStyle) {
        viewBinding.background.removeAllViews()
        when (style) {
            IndicatorStyle.RED -> {
                redIndicatorHotspotList.forEach {
                    addHotspot(style, it)
                }
            }
            IndicatorStyle.YELLOW -> {
                yellowIndicatorHotspotList.forEach {
                    addHotspot(style, it)
                }
            }
            IndicatorStyle.GREEN -> {
                greenIndicatorHotspotList.forEach {
                    addHotspot(style, it)
                }
            }
            IndicatorStyle.BLUE -> {
                blueIndicatorHotspotList.forEach {
                    addHotspot(style, it)
                }
            }

        }
    }


    override fun onDestroy() {
        super.onDestroy()
        viewBinding.background.background = null
        viewBinding.background.removeAllViews()
    }

    private fun addHotspot(style: IndicatorStyle, hotspot: Hotspot) {
        val view = View(this)
        val lp = FrameLayout.LayoutParams(radius * 2, radius * 2)
        lp.leftMargin = (hotspot.scaleX * hotspotLayoutWidth).roundToInt() - radius
        lp.topMargin = (hotspot.scaleY * hotspotLayoutHeight).roundToInt() - radius
        view.layoutParams = lp
//        view.setBackgroundColor(Color.RED)
//        view.alpha = 0.2f
        view.setOnClickListener {
            val dialog = TipsDialog.newInstance(style.name, hotspot.description)
            dialog.showDialog(supportFragmentManager)

        }
        viewBinding.background.addView(view,lp)
    }
}