package com.baorun.handbook.t60y.feature.search

import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import com.baorun.handbook.t60y.R
import com.baorun.handbook.t60y.data.ChildrenData
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder

class SearchResultAdapter(layoutId:Int):BaseQuickAdapter<ChildrenData,BaseViewHolder>(layoutId) {

    override fun convert(holder: BaseViewHolder, item: ChildrenData) {



        holder.getView<AppCompatImageView>(R.id.icon).apply {
            when(item.belong.split("_")[0]){
                "cj"->setImageResource(R.drawable.assets_images_home_1)
                "gn"->setImageResource(R.drawable.assets_images_home_5)
                "sp"->setImageResource(R.drawable.assets_images_home_video)
            }
        }


        holder.getView<AppCompatTextView>(R.id.content).apply {
            text = item.name
        }
    }
}