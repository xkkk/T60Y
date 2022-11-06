package com.baorun.handbook.t60y.data

import com.baorun.handbook.t60y.R

/**
 * 功能：
 * 描述：
 * Created by xukun on 2021/8/15.
 */
data class SceneListData(val id: String, val cover: Int, val title: String)

fun generateSceneData(page: Int): List<SceneListData> {
    return if (page == 1) {
        listOf(
            SceneListData("1", R.drawable.assets_public_img_01, "行程准备"),
            SceneListData("1", R.drawable.assets_public_img_02, "接近"),
            SceneListData("1", R.drawable.assets_public_img_03, "解锁"),
            SceneListData("1", R.drawable.assets_public_img_04, "载物"),
            SceneListData("1", R.drawable.assets_public_img_05, "进入"),
            SceneListData("1", R.drawable.assets_public_img_06, "出行乘坐"),
            SceneListData("1", R.drawable.assets_public_img_07, "驾驶"),
            SceneListData("1", R.drawable.assets_public_img_08, "泊车"),
        )
    } else {
        listOf( SceneListData("1", R.drawable.assets_public_img_09, "下车"),
            SceneListData("1", R.drawable.assets_public_img_10, "锁车"),
            SceneListData("1", R.drawable.assets_public_img_11, "离车"),
            SceneListData("1", R.drawable.assets_public_img_12, "保养清洁"),
            SceneListData("1", R.drawable.assets_public_img_13, "应急救援"))
    }
}