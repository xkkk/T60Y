package com.baorun.handbook.t60y.feature.maintenance

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.View
import android.widget.FrameLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.baorun.handbook.t60y.R
import com.baorun.handbook.t60y.data.Hotspot
import com.baorun.handbook.t60y.data.maintenanceHotspotList
import com.baorun.handbook.t60y.databinding.ActivityMaintenanceBinding
import com.baorun.handbook.t60y.radius
import com.blankj.utilcode.util.LogUtils
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.CustomViewTarget
import com.bumptech.glide.request.transition.Transition
import kotlinx.coroutines.launch
import kotlin.math.roundToInt

class MaintenanceActivity : AppCompatActivity() {


    private lateinit var viewBinding: ActivityMaintenanceBinding


    private var hotspotLayoutWidth = 2667
    private var hotspotLayoutHeight = 1000

    val options = RequestOptions()
        .skipMemoryCache(false) //是否允许内存缓存
        .onlyRetrieveFromCache(false) //是否只从缓存加载图片
        .diskCacheStrategy(DiskCacheStrategy.RESOURCE) //禁止磁盘缓存

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityMaintenanceBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)

        initView()
    }

    private fun initView() {
        loadBackground()
        viewBinding.rootView.post {
            lifecycleScope.launch{
                viewBinding.setBgSize()
                maintenanceHotspotList.forEach {
                    addHotspot(it)
                }
            }
        }

    }

    private fun loadBackground() {
        Glide.with(this).load(R.drawable.bg_weixiu).apply(options)
            .into(object : CustomViewTarget<FrameLayout, Drawable>(viewBinding.rootView) {
                override fun onLoadFailed(errorDrawable: Drawable?) {

                }

                override fun onResourceReady(
                    resource: Drawable,
                    transition: Transition<in Drawable>?
                ) {
                    viewBinding.rootView.background = resource

                }

                override fun onResourceCleared(placeholder: Drawable?) {
                }

            })
    }


    /**
     * 将热点区域设置的和图片一样大小
     */
    private fun ActivityMaintenanceBinding.setBgSize() {

        val width = rootView.width
        val height = rootView.height
        val lp = hotspotLayout.layoutParams
        lp.width = width
        lp.height = height
        hotspotLayout.layoutParams = lp
        hotspotLayoutWidth = width
        hotspotLayoutHeight = height
        LogUtils.i(lp.width, lp.height)
    }


    override fun onDestroy() {
        super.onDestroy()
        viewBinding.rootView.background = null
        viewBinding.hotspotLayout.removeAllViews()
    }

    private fun addHotspot(hotspot: Hotspot){
        val view = View(this)
        val lp = FrameLayout.LayoutParams((radius +10)*2, (radius +10) *2)
        lp.leftMargin = (hotspot.scaleX * hotspotLayoutWidth).roundToInt()- (radius+5)
        lp.topMargin = (hotspot.scaleY * hotspotLayoutHeight).roundToInt()- (radius+5)
        view.layoutParams = lp
//        view.setBackgroundColor(Color.RED)
        view.setOnClickListener {
            MaintenanceDetailDialog.newInstance(hotspot.description).show(supportFragmentManager,"MaintenanceDetailDialog")
        }
        viewBinding.hotspotLayout.addView(view,lp)
    }
}