package com.baorun.handbook.t60y.data

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.baorun.handbook.t60y.R
import com.baorun.handbook.t60y.db.CollectEntity
import com.baorun.handbook.t60y.db.HistoryEntity

/**
 * 功能：
 * 描述：
 * Created by xukun on 2021/8/19.
 */
data class Data(val dataType: String, val data: List<ChildrenData>)

@Entity()
data class ChildrenData(
    var parentId: String,
    var belong: String,
    @PrimaryKey var id: String,
    var name: String,
    var htmlUrl: String,
    var description: String,
    @Ignore val data1:List<String> = emptyList(),
    @Ignore val data2:List<String> = emptyList(),
    @Ignore val children: List<ChildrenData> = emptyList()
) {

    constructor():this("","","","","","")

    //封面资源
    @Ignore var coverRes: Int? = null
    @Ignore var checked:Boolean = false
}


fun ChildrenData.toCollectEntity():CollectEntity{
    return CollectEntity(parentId,belong,id,name,htmlUrl, description)
}
fun ChildrenData.toHistoryEntity():HistoryEntity{
    return HistoryEntity(parentId,belong,id,name,htmlUrl, description)
}

/**
 * Get cj cover res by id
 * 获取场景模块的封面图
 * @param id
 * @return
 */
fun getCjCoverResById(id: String): Int {
    return when (id) {
        "cj_1" -> R.drawable.assets_public_img_01
        "cj_2" -> R.drawable.assets_public_img_02
        "cj_3" -> R.drawable.assets_public_img_03
        "cj_4" -> R.drawable.assets_public_img_04
        "cj_5" -> R.drawable.assets_public_img_05
        "cj_6" -> R.drawable.assets_public_img_06
        "cj_7" -> R.drawable.assets_public_img_07
        "cj_8" -> R.drawable.assets_public_img_08
        "cj_9" -> R.drawable.assets_public_img_09
        "cj_10" -> R.drawable.assets_public_img_10
        "cj_11" -> R.drawable.assets_public_img_11
        "cj_12" -> R.drawable.assets_public_img_12
        "cj_13" -> R.drawable.assets_public_img_13
        else -> -1
    }
}


/**
 * Get cj cover res by id
 * 获取视频模块的封面图
 * @param id
 * @return
 */
fun getVideoCoverResById(id: String): Int {
    return when (id) {
        "sp_1" -> R.drawable.assets_video_preview_1
        "sp_2" -> R.drawable.assets_video_preview_2
        "sp_3" -> R.drawable.assets_video_preview_3
        "sp_4" -> R.drawable.assets_video_preview_4
        "sp_5" -> R.drawable.assets_video_preview_5
        "sp_6" -> R.drawable.assets_video_preview_6
        "sp_7" -> R.drawable.assets_video_preview_7
        "sp_8" -> R.drawable.assets_video_preview_8
        "sp_9" -> R.drawable.assets_video_preview_9
        "sp_10" -> R.drawable.assets_video_preview_10
        "sp_11" -> R.drawable.assets_video_preview_11
        "sp_12" -> R.drawable.assets_video_preview_12
        "sp_13" -> R.drawable.assets_video_preview_13
        "sp_14" -> R.drawable.assets_video_preview_14
        "sp_15" -> R.drawable.assets_video_preview_15
        "sp_16" -> R.drawable.assets_video_preview_16
        "sp_17" -> R.drawable.assets_video_preview_17
        "sp_18" -> R.drawable.assets_video_preview_18
        "sp_19" -> R.drawable.assets_video_preview_19
        "sp_20" -> R.drawable.assets_video_preview_20
        "sp_21" -> R.drawable.assets_video_preview_21
        "sp_22" -> R.drawable.assets_video_preview_22
        else -> -1
    }
}

fun getQuestionCoverResById(id: String): Int {
    return when (id) {
        "wt_1" -> R.drawable.assets_public_img_wt_item1
        "wt_2" -> R.drawable.assets_public_img_wt_item2
        "wt_3" -> R.drawable.assets_public_img_wt_item3
        "wt_4" -> R.drawable.assets_public_img_wt_item4
        "wt_5" -> R.drawable.assets_public_img_wt_item5
        "wt_6" -> R.drawable.assets_public_img_wt_item6
        "wt_7" -> R.drawable.assets_public_img_wt_item7
        "wt_8" -> R.drawable.assets_public_img_wt_item8
        "wt_9" -> R.drawable.assets_public_img_wt_item9
        "wt_10" -> R.drawable.assets_public_img_wt_item10
        else -> -1
    }
}
