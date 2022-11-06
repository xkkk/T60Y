package com.baorun.handbook.t60y

import android.app.Application
import android.content.ContextWrapper
import android.provider.Settings
import com.bumptech.glide.Glide

/**
 * 功能：
 * 描述：
 * Created by xukun on 2021/8/15.
 */

lateinit var mApp: Application
class App:Application() {


    override fun onCreate() {
        super.onCreate()
        mApp = this
        userId = Settings.System.getString(AppContext.contentResolver,Settings.Secure.ANDROID_ID)
    }

    companion object{
        const val PAGE_SIZE = 8
        var userId:String = ""
    }

    override fun onLowMemory() {
        super.onLowMemory()
        Glide.get(this).clearMemory()
    }

    override fun onTrimMemory(level: Int) {
        super.onTrimMemory(level)
        if (level == TRIM_MEMORY_UI_HIDDEN){
            Glide.get(this).clearMemory()
        }
        Glide.get(this).trimMemory(level)
    }
}


object AppContext : ContextWrapper(mApp)//ContextWrapper对Context上下文进行包装(装饰者模式)