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
    Hotspot(50, 76, "1"),
    Hotspot(22, 272, "3"),
    Hotspot(22, 298, "2"),
    Hotspot(200, 363, "4"),
    Hotspot(22, 327, "5"),
    Hotspot(933, 162, "6"),
    Hotspot(300, 358, "7"),
    Hotspot(267, 360, "8"),
    Hotspot(933, 243, "9"),
    Hotspot(930, 105, "10"),
    Hotspot(668, 360, "11"),
    Hotspot(237, 360, "12"),
    Hotspot(610, 360, "13"),
)


val redIndicatorHotspotList = originRedIndicatorHotspotList.map {
    it.calculateScale(954, 400)
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
    Hotspot(23, 275, "1"),
    Hotspot(23, 247, "2"),
    Hotspot(774, 401, "3"),
    Hotspot(23, 175, "4"),
    Hotspot(1024, 239, "5"),
    Hotspot(1024, 273, "6"),
    Hotspot(1024, 359, "7"),
    Hotspot(23, 208, "8"),
    Hotspot(23, 113, "9"),
    Hotspot(169, 50, "10"),
    Hotspot(1024, 113, "11"),
    Hotspot(132, 58, "12"),
    Hotspot(91, 52, "13"),
    Hotspot(932, 52, "14"),
    Hotspot(1024, 157, "15"),
    Hotspot(868, 52, "16"),
    Hotspot(33, 53, "17")
    )
val yellowIndicatorHotspotList = originYellowIndicatorHotspotList.map {
    it.calculateScale(1046, 439)
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
    Hotspot(275,52,"1"),
    Hotspot(800,52,"2"),
    Hotspot(303,400,"3"),
    Hotspot(234,52,"4"),
    Hotspot(190,55,"5"),
    Hotspot(146,54,"6"),
    Hotspot(1038,195,"7"),
    Hotspot(92,60,"8"),
    Hotspot(50,51,"9"),
    Hotspot(857,53,"10"),
    Hotspot(1036,152,"11"),
    Hotspot(22,115,"12"),
)

val greenIndicatorHotspotList = originGreenIndicatorHotspotList.map {
    it.calculateScale(1060,445)
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
    Hotspot(852,52,"1"),
    Hotspot(253,55,"2"),
    Hotspot(214,55,"3"),
    Hotspot(174,55,"4"),
    Hotspot(1042,193,"5"),
    Hotspot(93,52,"6"),
    Hotspot(698,405,"7"),
    Hotspot(894,52,"8"),
    Hotspot(31,52,"9"),

)

val blueIndicatorHotspotList = originBlueIndicatorHotspotList.map {
    it.calculateScale(1060,445)
}
