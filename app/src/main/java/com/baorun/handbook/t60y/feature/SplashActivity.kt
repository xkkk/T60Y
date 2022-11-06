package com.baorun.handbook.t60y.feature


import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatImageView
import androidx.lifecycle.lifecycleScope
import com.baorun.handbook.t60y.R
import com.baorun.handbook.t60y.ext.goActivity
import com.baorun.handbook.t60y.ext.loadDrawableRes
import com.baorun.handbook.t60y.feature.home.MainActivity
import kotlinx.coroutines.delay

/**
 * Splash activity
 * 启动页 延迟2秒
 * @constructor Create empty Splash activity
 */
const val DELAY_DURATION = 2_000L
class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        val imageView = findViewById<AppCompatImageView>(R.id.image)
        loadDrawableRes(this,R.drawable.img_splash,imageView,640,360)
        lifecycleScope.launchWhenCreated {
            delay(DELAY_DURATION)
            goActivity(MainActivity::class.java)
            finish()
        }

    }
}