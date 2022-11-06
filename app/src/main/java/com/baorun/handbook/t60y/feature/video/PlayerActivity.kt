package com.baorun.handbook.t60y.feature.video

import android.app.Activity
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.media.AudioAttributes
import android.media.AudioFocusRequest
import android.media.AudioManager
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import cn.jzvd.Jzvd
import cn.jzvd.JzvdStd
import com.baorun.handbook.t60y.KEY_BUNDLE_BELONG
import com.baorun.handbook.t60y.KEY_BUNDLE_ID
import com.baorun.handbook.t60y.KEY_BUNDLE_PATH
import com.baorun.handbook.t60y.KEY_BUNDLE_RES
import com.baorun.handbook.t60y.databinding.ActivityPlayerBinding
import com.baorun.handbook.t60y.ext.goActivity
import com.baorun.handbook.t60y.feature.collect.CollectionViewModel
import com.baorun.handbook.t60y.feature.search.SearchActivity
import com.baorun.handbook.t60y.widget.JZMediaExo
import com.baorun.handbook.t60y.widget.JZMediaSystemAssertFolder
import com.blankj.utilcode.util.ThreadUtils

/**
 * 功能：
 * 描述：视频播放页
 * Created by xukun on 2021/8/19.
 */
open abstract class  PlayerActivity : AppCompatActivity() {

    val ACTION = "com.gxatek.cockpit.screensaver"
    protected lateinit var viewBinding: ActivityPlayerBinding
    private lateinit var am: AudioManager
    private lateinit var audioFocus:AudioFocusRequest
    private val receiver: ScreenSaverReceiver by lazy {
        ScreenSaverReceiver()
    }

    private val mCollectViewModel by viewModels<CollectionViewModel>()

    protected val path: String by lazy {
        intent.getStringExtra(KEY_BUNDLE_PATH) ?: ""
    }

    protected val id: String by lazy {
        intent.getStringExtra(KEY_BUNDLE_ID) ?: ""
    }

    protected val belong: String by lazy {
        intent.getStringExtra(KEY_BUNDLE_BELONG) ?: ""
    }


    protected var isCollect = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityPlayerBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)

        initPlayer()

        viewBinding.player.setUp(
            path, "", JzvdStd.SCREEN_NORMAL,
            JZMediaSystemAssertFolder::class.java
        )

        Jzvd.SAVE_PROGRESS = false

        viewBinding.player.startVideo()


        lifecycleScope.launchWhenCreated {
            mCollectViewModel.isCollect(id)
        }

        viewBinding.collectLayout.searchIv.setOnClickListener {
            goActivity(SearchActivity::class.java)
        }

        viewBinding.collectLayout.collectIv.setOnClickListener {
            if (isCollect) {
                mCollectViewModel.delete(belong, id)
            } else {
                mCollectViewModel.insert(belong, id)
            }
            mCollectViewModel.collectStatus.value = !isCollect
        }

        mCollectViewModel.collectStatus.observe(this) {
            isCollect = it
            viewBinding.collectLayout.collectIv.isSelected = it
        }

        val intentFilter = IntentFilter()
        intentFilter.apply {
            addAction(ACTION)
        }
        registerReceiver(receiver, intentFilter)
    }

    private fun registerAudioFocus(){
        am = getSystemService(Context.AUDIO_SERVICE) as AudioManager
        val attributes = AudioAttributes.Builder()
        attributes.setContentType(AudioAttributes.CONTENT_TYPE_MOVIE)
        attributes.setUsage(AudioAttributes.USAGE_MEDIA)
        val builder = AudioFocusRequest.Builder(1)
        builder.setAudioAttributes(attributes.build())
        builder.setAcceptsDelayedFocusGain(false)
        builder.setWillPauseWhenDucked(false)
        builder.setOnAudioFocusChangeListener{

        }
        audioFocus = builder.build()

        am.requestAudioFocus(audioFocus)
    }

    private fun unregisterAudioFocus(){
        am.abandonAudioFocusRequest(audioFocus)
    }


    override fun onBackPressed() {
        if (Jzvd.backPress()) {
            return
        }
        super.onBackPressed()
    }

    override fun onPause() {
        super.onPause()
        Jzvd.goOnPlayOnPause()
    }

    override fun onResume() {
        super.onResume()
        Jzvd.goOnPlayOnResume();
    }

    override fun onDestroy() {
        super.onDestroy()
        Jzvd.releaseAllVideos()
    }

    abstract fun initPlayer()
    abstract fun isRegisterAudio():Boolean

    class ScreenSaverReceiver : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            intent?.let {

                val key = intent.getStringExtra("action")
                val key2 = intent.extras?.getString("action")
                // 进入屏保
                if ("enter" == key) {
                    ThreadUtils.runOnUiThread {
                        Jzvd.goOnPlayOnPause()
                    }
                } else if("exit"==key) {
                    ThreadUtils.runOnUiThread {
                        Jzvd.goOnPlayOnResume()
                    }
                }
            }

        }

    }

}