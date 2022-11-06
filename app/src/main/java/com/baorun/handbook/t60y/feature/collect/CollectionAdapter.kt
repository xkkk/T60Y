package com.baorun.handbook.t60y.feature.collect

import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import com.baorun.handbook.t60y.R
import com.baorun.handbook.t60y.data.ChildrenData
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder

class CollectionAdapter:BaseQuickAdapter<ChildrenData,BaseViewHolder>(R.layout.item_collect_layout) {

    init {
        addChildClickViewIds(R.id.deleteIv)
    }

    override fun convert(holder: BaseViewHolder, item: ChildrenData) {
        holder.getView<AppCompatImageView>(R.id.iconIv).apply {
            when(item.belong.split("_")[0]){
                "cj"->setImageResource(R.drawable.assets_images_home_1)
                "gn"->setImageResource(R.drawable.assets_images_home_5)
                "sp"->setImageResource(R.drawable.assets_images_home_video)
                "wt"->setImageResource(R.drawable.assets_images_home_3)
                "gj"->setImageResource(R.drawable.assets_images_home_6)
            }
        }

        holder.getView<AppCompatTextView>(R.id.titleTv).apply {
            text = item.name
        }

        holder.getView<AppCompatTextView>(R.id.descriptionTv).apply {
            text = item.description
        }
    }
}