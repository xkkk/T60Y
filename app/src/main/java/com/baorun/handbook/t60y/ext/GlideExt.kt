package com.baorun.handbook.t60y.ext

import android.content.Context
import android.graphics.drawable.Drawable
import android.widget.FrameLayout
import android.widget.ImageView
import com.baorun.handbook.t60y.widget.GlideApp
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.CustomViewTarget
import com.bumptech.glide.request.transition.Transition

val options = RequestOptions()
    .skipMemoryCache(true) //是否允许内存缓存
    .onlyRetrieveFromCache(false) //是否只从缓存加载图片
    .diskCacheStrategy(DiskCacheStrategy.NONE) //禁止磁盘缓存



fun loadBackground(context: Context,res: Int,target: ImageView,width:Int,height:Int){
    GlideApp.with(context).load(res).apply(options).override(width, height).into(target)
}

fun loadBackground(context:Context,res:Int,width: Int,height: Int,target:FrameLayout){
    GlideApp.with(context).load(res).apply(options).override(width, height)
        .into(object : CustomViewTarget<FrameLayout, Drawable>(target) {
            override fun onLoadFailed(errorDrawable: Drawable?) {

            }

            override fun onResourceReady(
                resource: Drawable,
                transition: Transition<in Drawable>?
            ) {
                target.background = resource

            }

            override fun onResourceCleared(placeholder: Drawable?) {
                target.background = null
            }

        })
}

fun loadImage(context: Context,res:Int,target:ImageView){
    GlideApp.with(context).load(res).apply(options).into(target)
}

fun loadBackground(context:Context,res:Int,target:ImageView){
    GlideApp.with(context).load(res).apply(options)
        .into(object : CustomViewTarget<ImageView, Drawable>(target) {
            override fun onLoadFailed(errorDrawable: Drawable?) {

            }

            override fun onResourceReady(
                resource: Drawable,
                transition: Transition<in Drawable>?
            ) {
                target.background = resource

            }

            override fun onResourceCleared(placeholder: Drawable?) {
                target.background = null
            }

        })
}

fun loadDrawableRes(context: Context,res:Int,target:ImageView,width: Int,height: Int){
    GlideApp.with(context).load(res).apply(options).override(width, height).into(target)
}
