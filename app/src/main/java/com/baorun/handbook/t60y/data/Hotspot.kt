package com.baorun.handbook.t60y.data

import com.baorun.handbook.t60y.R

/**
 * 功能：
 * 描述：
 * Created by xukun on 2021/8/18.
 */
const val ORIGIN_WIDTH = 1780
const val ORIGIN_HEIGHT = 720


data class Hotspot(val x: Int, val y: Int, val description: String, val drawableRes: ArrayList<Int>){
    //将id当作description传递到下一级
    constructor(x: Int,y: Int,id:String):this(x, y, id, arrayListOf())
    var scaleX = x.toDouble()/ ORIGIN_WIDTH
    var scaleY = y.toDouble()/ ORIGIN_HEIGHT
}

fun Hotspot.calculateScale(width:Int,height:Int):Hotspot{
    this.scaleX = x.toDouble()/width
    this.scaleY = y.toDouble()/height
    return this
}

/**
 * 1。通过图片工具测量出热点的在原图中的坐标，左上角为坐标原点
 * 2。通过坐标位置/原图大小 得到热点在原图中的宽高比
 * 3。添加的时候需要先计算中原图大小、在按照比例添加热点
 */


val originMaintenanceHotspotList = listOf(
    Hotspot(983,679,"wx_1"),
    Hotspot(601,635,"wx_2"),
    Hotspot(375,355,"wx_3"),
    Hotspot(617,146,"wx_4"),
    Hotspot(932,205,"wx_5"),
    Hotspot(1237,383,"wx_6"),
    Hotspot(1581,558,"wx_7"),
    Hotspot(1959,517,"wx_8"),
    Hotspot(2140,217,"wx_9"),
)

/**
 * Origin vision in hotspot list1
 * out_1 的热点原始坐标点
 */

val originVisionOut1HotspotList = listOf(
    Hotspot(630, 280, "前雨刷", arrayListOf(R.drawable.img_vision_2_1)),
    Hotspot(
        667,
        382,
        "前大灯",
        arrayListOf(R.drawable.img_vision_3_1,
            R.drawable.img_vision_3_2,
            R.drawable.img_vision_3_3,
            R.drawable.img_vision_3_4
        )
    ),
    Hotspot(
        493,
        338,
        "发动机仓盖",
        arrayListOf(R.drawable.img_vision_1_1,
            R.drawable.img_vision_1_2,
            R.drawable.img_vision_1_3,
            R.drawable.img_vision_1_4,
            R.drawable.img_vision_1_5,
        )
    ),
    Hotspot(1196, 294, "儿童安全锁", arrayListOf(R.drawable.img_vision_4_1)),
)



/**
 * Origin vision in hotspot list2
 * out_2 的热点原始坐标点
 */
val originVisionOut2HotspotList = listOf(
    Hotspot(
        427,
        274,
        "行李箱",
        arrayListOf(R.drawable.img_vision_5_1, R.drawable.img_vision_5_2, R.drawable.img_vision_5_3)
    ),
    Hotspot(
        801,
        307,
        "燃油箱盖",
        arrayListOf(R.drawable.img_vision_6_1, R.drawable.img_vision_6_2, R.drawable.img_vision_6_3)
    ),
)





val originVisionIn1HotspotList = listOf(
    Hotspot(
        515,
        429,
        "方向盘",
        arrayListOf(R.drawable.img_vision_v3_1,
            R.drawable.img_vision_v3_2,
            R.drawable.img_vision_v3_3,
            R.drawable.img_vision_v3_4,
            R.drawable.img_vision_v3_5
        )
    ),
    Hotspot(
        422,
        536,
        "仪表盘左侧开关",
        arrayListOf(
            R.drawable.img_vision_v1_1,
            R.drawable.img_vision_v1_2,
            R.drawable.img_vision_v1_3,
            R.drawable.img_vision_v1_4
        )
    ),
    Hotspot(
        189,
        552,
        "左前门开关",
        arrayListOf(R.drawable.img_vision_v2_1, R.drawable.img_vision_v2_2, R.drawable.img_vision_v2_3)
    ),
    Hotspot(893, 531, "空调控制面板", arrayListOf(R.drawable.img_vision_v4_1)),
    Hotspot(
        893,
        623,
        "换挡控制面板",
        arrayListOf(R.drawable.img_vision_v5_1, R.drawable.img_vision_v5_2, R.drawable.img_vision_v5_3)
    ),
)


val originVisionIn2HotspotList = listOf(
    Hotspot(
        884,
        221,
        "顶灯",
        arrayListOf(
            R.drawable.img_vision_v7_1,
            R.drawable.img_vision_v7_2,
            R.drawable.img_vision_v7_3,
            R.drawable.img_vision_v7_4,
            R.drawable.img_vision_v7_5,
            R.drawable.img_vision_v7_6
        )
    ),
    Hotspot(
        465,
        205,
        "主驾座椅",
        arrayListOf(
            R.drawable.img_vision_v6_1,
            R.drawable.img_vision_v6_2,
            R.drawable.img_vision_v6_3,
            R.drawable.img_vision_v6_4,
            R.drawable.img_vision_v6_5,
        )
    ),
)




val maintenanceHotspotList =
    originMaintenanceHotspotList.map {
       it.calculateScale(2667,1000)
    }
