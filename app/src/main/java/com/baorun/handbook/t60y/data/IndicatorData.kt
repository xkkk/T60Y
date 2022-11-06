package com.baorun.handbook.t60y.data


enum class IndicatorStyle{
    BLUE,GREEN,YELLOW,RED
}

data class Indicator(
    val blueData: List<IndicatorData>,
    val greenData: List<IndicatorData>,
    val yellowData: List<IndicatorData>,
    val redData: List<IndicatorData>,
)

data class IndicatorData(val id: String, val title: String, val content: Content) {
    data class Content(val text: String)
}


/**
"id": 1,
"title": "车门开启指示灯",

"id": 2,
"title": "充电系统报警灯",

"id": 3,
"title": "机油压力低报警灯",

"id": 4,
"title": "发动机冷却液温度高指示灯",

"id": 5,
"title": "辅助保护系统（SRS）指示灯",

"id": 6,
"title": "车道偏离状态指示灯*",

"id": 7,
"title": "电子驻车（EPB）状态指示灯",

"id": 8,
"title": "驻车制动与制动系统指示灯",

"id": 9,
"title": "电动助力转向（EPS）指示灯",

"id": 10,
"title": "前碰缓解状态指示灯*",

"id": 11,
"title": "前乘客座椅安全带提示灯",

"id": 12,
"title": "驾驶员座椅安全带提示灯",

"id": 13,
"title": "后排座椅安全带提示灯*",
 */
val originRedIndicatorHotspotList = listOf(
    Hotspot(121, 98, "1"),
    Hotspot(63, 608, "2"),
    Hotspot(65, 546, "3"),
    Hotspot(1931, 569, "4"),
    Hotspot(68, 682, "5"),
    Hotspot(1934, 368, "6"),
    Hotspot(1845, 100, "7"),
    Hotspot(1932, 642, "8"),
    Hotspot(1934, 495, "9"),
    Hotspot(1928, 194, "10"),
    Hotspot(290, 103, "11"),
    Hotspot(220, 103, "12"),
    Hotspot(430, 103, "13"),
    Hotspot(63, 480, "14"),
)


val redIndicatorHotspotList = originRedIndicatorHotspotList.map {
    it.calculateScale(1976, 828)
}

/**
"id": 1,
"title": "发动机故障指示灯",

"id": 2,
"title": "排放故障指示灯",

"id": 3,
"title": "燃油低指示灯",

"id": 4,
"title": "电子驻车（EPB）故障指示灯",

"id": 5,
"title": "车辆稳定性辅助（ESP）指示灯",

"id": 6,
"title": "车辆稳定性辅助关闭（ESP OFF）指示灯",

"id": 7,
"title": "防抱死制动系统（ABS）指示灯 ",

"id": 8,
"title": "变速器故障指示灯*",

"id": 9,
"title": "胎压监测系统（TPMS）指示灯*",

"id": 10,
"title": "自适应巡航故障指示灯*",

"id": 11,
"title": "前碰缓解状态指示灯*",

"id": 12,
"title": "手扶方向盘指示灯*",

"id": 13,
"title": "横向控制状态指示灯*",

"id": 14,
"title": "后雾灯指示灯",

"id": 15,
"title": "盲区监测状态指示灯*",

"id": 16,
"title": "智能远光灯指示灯*",
 */
private val originYellowIndicatorHotspotList = listOf(
    Hotspot(50, 565, "1"),
    Hotspot(50, 499, "2"),
    Hotspot(1452, 752, "3"),
    Hotspot(50, 340, "4"),
    Hotspot(1934, 453, "5"),
    Hotspot(1934, 528, "6"),
    Hotspot(1934, 678, "7"),
//    Hotspot(23, 208, "8"),
    Hotspot(50, 218, "9"),
    Hotspot(911, 172, "10"),
    Hotspot(1934, 240, "11"),
    Hotspot(632, 180, "12"),
    Hotspot(539, 174, "13"),
    Hotspot(1768, 100, "14"),
    Hotspot(96, 101, "17"),
    Hotspot(410, 751, "18"),
    Hotspot(1934, 166, "19"),
    Hotspot(1934, 97, "20"),
    Hotspot(1934, 320, "21"),
    Hotspot(1629, 98, "22"),
    )
val yellowIndicatorHotspotList = originYellowIndicatorHotspotList.map {
    it.calculateScale(1975, 828)
}

/**
    "id": 1,
    "title": "左转向信号与危险警告指示灯",

    "id": 2,
    "title": "右转向信号与危险警告指示灯",

    "id": 3,
    "title": "电子驻车（EPB）状态指示灯",

    "id": 4,
    "title": "定速巡航指示灯*",

    "id": 5,
    "title": "自适应巡航前方无车辆指示灯*",

    "id": 6,
    "title": "自适应巡航前方有车辆指示灯*",

    "id": 7,
    "title": "车道偏移状态指示灯*",

    "id": 8,
    "title": "手扶方向盘指示灯*",

    "id": 9,
    "title": "横向控制状态指示灯*",

    "id": 10,
    "title": "位置灯指示灯",

    "id": 11,
    "title": "盲区监测状态指示灯*",
 */

//1060*445
private val originGreenIndicatorHotspotList = listOf(
    Hotspot(516,99,"1"),
    Hotspot(1491,97,"2"),
    Hotspot(1846,102,"3"),
    Hotspot(1082,172,"4"),
    Hotspot(988,172,"5"),
    Hotspot(905,172,"6"),
    Hotspot(1937,368,"7"),
    Hotspot(634,181,"8"),
    Hotspot(538,176,"9"),
    Hotspot(1597,100,"10"),
    Hotspot(1932,283,"11"),
    Hotspot(114,682,"12"),
    Hotspot(523,753,"13"),
    Hotspot(625,746,"14")
)

val greenIndicatorHotspotList = originGreenIndicatorHotspotList.map {
    it.calculateScale(1976,828)
}


/**
    "id": 1,
    "title": "远光灯指示灯",

    "id": 2,
    "title": "定速巡航指示灯*",

    "id": 3,
    "title": "自适应巡航前方无车辆指示灯*",

    "id": 4,
    "title": "自适应巡航前方有车辆指示灯*",

    "id": 5,
    "title": "车道偏移状态指示灯*",

    "id": 6,
    "title": "横向控制状态指示灯*",

    "id": 7,
    "title": "第二排座椅安全带提示灯*",

    "id": 8,
    "title": "智能远光灯指示灯*",
*/
//1060*445
private val originBlueIndicatorHotspotList = listOf(
    Hotspot(1604,102,"1"),
    Hotspot(1690,102,"2"),
    Hotspot(1929,366,"3"),
    Hotspot(431,102,"4"),
    Hotspot(1772,102,"5"),
    Hotspot(94,94,"6"),
    Hotspot(1082,182,"7"),
    Hotspot(543,174,"8"),
    Hotspot(903,170,"9"),
    Hotspot(992,179,"10"),

)

val blueIndicatorHotspotList = originBlueIndicatorHotspotList.map {
    it.calculateScale(1976,827)
}
