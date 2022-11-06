package com.baorun.handbook.t60y.data

import com.baorun.handbook.t60y.App
import com.baorun.handbook.t60y.R

/**
 * 功能：
 * 描述：
 * Created by xukun on 2021/8/16.
 */
data class QuestionListData(val id:String,val cover:Int,val title:String)

val questionList = listOf(
    QuestionListData("1", R.drawable.assets_public_img_wt_item1,"如何打开发动机舱盖？"),
    QuestionListData("1", R.drawable.assets_public_img_wt_item2,"智能要是馈电如何应急开启车门？"),
    QuestionListData("1", R.drawable.assets_public_img_wt_item3,"智能要是馈电如何应急启动？"),
    QuestionListData("1", R.drawable.assets_public_img_wt_item4,"如何打开雨刷维护模式？"),
    QuestionListData("1", R.drawable.assets_public_img_wt_item5,"如何唤醒语音？"),
    QuestionListData("1", R.drawable.assets_public_img_wt_item6,"如何切换驾驶模式？"),
    QuestionListData("1", R.drawable.assets_public_img_wt_item7,"如何进入手动驾驶模式？"),
    QuestionListData("1", R.drawable.assets_public_img_wt_item8,"应急开始行李箱盖？"),
    QuestionListData("1", R.drawable.assets_public_img_wt_item9,"如何更换轮胎？"),
    QuestionListData("1", R.drawable.assets_public_img_wt_item10,"如何紧急牵引（拖车）？"),
)

fun generateQuestionList(page:Int):List<QuestionListData>{
    return when(page){
        1-> questionList.take(App.PAGE_SIZE)
        2-> questionList.takeLast(questionList.size-App.PAGE_SIZE)
        else-> emptyList()
    }
}
