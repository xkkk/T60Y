package com.baorun.handbook.t60y.feature.home

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.baorun.handbook.t60y.R
import com.baorun.handbook.t60y.databinding.ActivityMainBinding
import com.baorun.handbook.t60y.ext.goActivity
import com.baorun.handbook.t60y.ext.loadImage
import com.baorun.handbook.t60y.ext.showToast
import com.baorun.handbook.t60y.feature.collect.CollectionActivity
import com.baorun.handbook.t60y.feature.guide.UserGuideActivity
import com.baorun.handbook.t60y.feature.indicator.IndicatorActivity
import com.baorun.handbook.t60y.feature.maintenance.MaintenanceActivity
import com.baorun.handbook.t60y.feature.mine.MineActivity
import com.baorun.handbook.t60y.feature.question.QuestionActivity
import com.baorun.handbook.t60y.feature.scene.SceneActivity
import com.baorun.handbook.t60y.feature.search.SearchActivity
import com.baorun.handbook.t60y.feature.video.VideoActivity
import com.baorun.handbook.t60y.feature.vision.VisionActivity
import com.baorun.handbook.t60y.feature.warn.WarnActivity
import com.blankj.utilcode.util.ActivityUtils
import com.blankj.utilcode.util.ClickUtils


/**
 * 功能：
 * 描述：首页
 * Created by xukun on 2021/8/14.
 */
const val EXIT_DURATION = 2000L
class MainActivity:AppCompatActivity(),View.OnClickListener {

    private lateinit var mViewBinding: ActivityMainBinding
    private var backPressTime = 0L
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mViewBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mViewBinding.root)
        initView()
    }

    private fun initView() {
        with(mViewBinding){
            loadImage(this@MainActivity,R.drawable.assets_images_home_360,home360)
           ClickUtils.applyPressedBgAlpha(tabVision,0.8f)
           ClickUtils.applyPressedBgAlpha(searchView,0.8f)
        }
    }

    override fun onClick(v: View) {
        when(v.id){
            R.id.tabScene->goSceneActivity()
//            R.id.tabVideo->goVideoActivity()
            R.id.tabMine->goMineActivity()
            R.id.tabVision->goVisionActivity()
            R.id.tabQuestion->goQuestionActivity()
            R.id.tabGuide->goActivity(UserGuideActivity::class.java)
            R.id.tabIndicator->goActivity(IndicatorActivity::class.java)
            R.id.tabWarning->goActivity(WarnActivity::class.java)
            R.id.tabMaintenance->goActivity(MaintenanceActivity::class.java)
            R.id.searchView->goActivity(SearchActivity::class.java)
            R.id.tabCollection->goActivity(CollectionActivity::class.java)
        }
    }

    override fun onBackPressed() {
        val now = System.currentTimeMillis()
        if (now - backPressTime > EXIT_DURATION) {
            showToast(R.string.quite_app)
            backPressTime = now
        } else {
            ActivityUtils.finishAllActivities()
        }
    }


    private fun goQuestionActivity() {
        goActivity(QuestionActivity::class.java)
    }

    private fun goVisionActivity(){
       goActivity(VisionActivity::class.java)
    }

    private fun goMineActivity() {
        goActivity(MineActivity::class.java)
    }

    private fun goSceneActivity(){
        goActivity(SceneActivity::class.java)
    }

    private fun goVideoActivity(){
        goActivity(VideoActivity::class.java)
    }

    private fun testScheme(){
        val uri = "a55://app/warn/5"
        val intent = Intent(
            Intent.ACTION_VIEW,
            Uri.parse(uri)
        )
        startActivity(intent)
    }
}