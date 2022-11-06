package com.baorun.handbook.t60y.data

import com.baorun.handbook.t60y.App
import com.baorun.handbook.t60y.R

/**
 * 功能：
 * 描述：
 * Created by xukun on 2021/8/15.
 */
data class VideoListData(val id: String, val cover: Int, val title: String)


private val videoList: List<VideoListData> by lazy {
    listOf(
        VideoListData("1", R.drawable.assets_video_preview_1, "驾驶模式"),
        VideoListData("1", R.drawable.assets_video_preview_2, "360全景影像"),
        VideoListData("1", R.drawable.assets_video_preview_3, "ACC自适应巡航"),
        VideoListData("1", R.drawable.assets_video_preview_4, "集成式巡航辅助系统"),
        VideoListData("1", R.drawable.assets_video_preview_5, "OTA升级"),
        VideoListData("1", R.drawable.assets_video_preview_6, "唱吧K歌"),
        VideoListData("1", R.drawable.assets_video_preview_7, "车道偏离辅助"),
        VideoListData("1", R.drawable.assets_video_preview_8, "打开和关闭车速上锁"),
        VideoListData("1", R.drawable.assets_video_preview_9, "打开和关闭发动机舱盖"),
        VideoListData("1", R.drawable.assets_video_preview_10, "打开和关闭行李箱"),
        VideoListData("1", R.drawable.assets_video_preview_11, "方向盘自定义按键操作"),
        VideoListData("1", R.drawable.assets_video_preview_12, "更换轮胎"),
        VideoListData("1", R.drawable.assets_video_preview_13, "盲区检测系统"),
        VideoListData("1", R.drawable.assets_video_preview_14, "前碰撞预警系统"),
        VideoListData("1", R.drawable.assets_video_preview_15, "手机APP远程控制"),
        VideoListData("1", R.drawable.assets_video_preview_16, "手机无线充电"),
        VideoListData("1", R.drawable.assets_video_preview_17, "胎压检测系统"),
        VideoListData("1", R.drawable.assets_video_preview_18, "语音助手"),
        VideoListData("1", R.drawable.assets_video_preview_19, "智能远光灯"),
        VideoListData("1", R.drawable.assets_video_preview_20, "主动制动辅助"),
        VideoListData("1", R.drawable.assets_video_preview_21, "驻车辅助"),
        VideoListData("1", R.drawable.assets_video_preview_22, "手机蓝牙钥匙"),
    )
}

fun generateVideoData(page: Int): List<VideoListData> {
    return when (page) {
        1 -> videoList.take(App.PAGE_SIZE)
        2 -> videoList.subList(App.PAGE_SIZE, App.PAGE_SIZE+App.PAGE_SIZE)
        3 -> videoList.takeLast(videoList.size-App.PAGE_SIZE*2)
        else-> emptyList()
    }
}
