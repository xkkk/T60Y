package com.baorun.handbook.t60y.ext

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.drawable.Drawable
import android.webkit.WebView
import androidx.core.content.ContextCompat
import androidx.viewpager2.widget.ViewPager2

/**
 * 功能：
 * 描述：
 * Created by xukun on 2021/8/18.
 */

fun Int.toDrawable(context: Context): Drawable?{
    return ContextCompat.getDrawable(context,this)
}

@SuppressLint("SetJavaScriptEnabled")
fun WebView.toSupportJavaScript(){
    this.settings.javaScriptEnabled = true
//    this.settings.javaScriptCanOpenWindowsAutomatically = true
//    this.settings.cacheMode = WebSettings.LOAD_NO_CACHE
//
//    this.webChromeClient = WebChromeClient()
//    this.webViewClient = WebViewClient()
//    this.settings.allowContentAccess = true
//    this.settings.builtInZoomControls = true
//    this.settings.useWideViewPort = true
//    this.settings.loadWithOverviewMode = true
//    this.settings.domStorageEnabled = true
    setBackgroundColor(0)
}



fun releaseAllWebViewCallback() {

}



fun ViewPager2.last(){
    setCurrentItem(this.currentItem-1,true)
}

fun ViewPager2.next(){
    setCurrentItem(this.currentItem+1,true)
}