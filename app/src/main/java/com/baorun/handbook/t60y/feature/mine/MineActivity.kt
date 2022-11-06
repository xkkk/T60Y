package com.baorun.handbook.t60y.feature.mine

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.baorun.handbook.t60y.R
import com.baorun.handbook.t60y.databinding.ActivityMineBinding


/**
 * 功能：
 * 描述：我的页面
 * Created by xukun on 2021/8/15.
 */
class MineActivity : AppCompatActivity() {

    private lateinit var viewBinding: ActivityMineBinding

    private val feedbackFragment: FeedbackFragment by lazy {
        FeedbackFragment()
    }
    private val historyFragment: HistoryFragment by lazy {
        HistoryFragment()
    }
    private val aboutFragment: AboutFragment by lazy {
        AboutFragment()
    }

    private var mFragment: Fragment? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityMineBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)
//        checkPermission()
        switchFragment(R.id.feedbackRb)
        viewBinding.radioGroup.setOnCheckedChangeListener { _, checkedId ->
            switchFragment(checkedId)
        }
    }

    private fun switchFragment(checkedId: Int) {
        when (checkedId) {
            R.id.feedbackRb -> replaceFragment(feedbackFragment).commit()
            R.id.historyRb -> replaceFragment(historyFragment).commit()
//            R.id.questionnaireRb -> replaceFragment(QuestionnaireFragment())
            R.id.aboutRb -> replaceFragment(aboutFragment).commit()
        }
    }

    private fun replaceFragment(fragment: Fragment): FragmentTransaction {
        val transaction = supportFragmentManager.beginTransaction()
        if (!fragment.isAdded) {
            if (mFragment != null) {
                transaction.hide(mFragment!!)
            }
            transaction.add(R.id.container, fragment, fragment::class.java.simpleName)
        } else {
            transaction.hide(mFragment!!).show(fragment)
        }
        mFragment = fragment
        return transaction
    }

}