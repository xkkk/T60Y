package com.baorun.handbook.t60y.feature.scene.adapter

import com.baorun.handbook.t60y.R
import com.baorun.handbook.t60y.data.ChildrenData
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder

/**
 * 功能：
 * 描述：
 * Created by xukun on 2021/8/15.
 */
class SceneAdapter:BaseQuickAdapter<ChildrenData,BaseViewHolder>(R.layout.item_scene_list) {
    override fun convert(holder: BaseViewHolder, item: ChildrenData) {
//        ClickUtils.applyPressedViewAlpha(holder.itemView,0.8f)
//        holder.getView<AppCompatImageView>(R.id.mCoverIv).apply {
//            loadDrawableRes(this.context,item.coverRes?:-1,this,360,220)
//            setImageResource(item.coverRes?:-1)
//        }

//        holder.getView<AppCompatTextView>(R.id.mTitleTv).apply {
//            text = item.name
//        }
        holder.setText(R.id.mTitleTv,item.name)
        holder.setImageResource(R.id.mCoverIv,item.coverRes?:0)
    }
}