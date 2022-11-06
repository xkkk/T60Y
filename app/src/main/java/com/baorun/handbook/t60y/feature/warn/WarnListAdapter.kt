package com.baorun.handbook.t60y.feature.warn

import androidx.appcompat.widget.AppCompatTextView
import com.baorun.handbook.t60y.R
import com.baorun.handbook.t60y.data.ChildrenData
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder

/**
 * 功能：
 * 描述：
 * Created by xukun on 2021/8/20.
 */
class WarnListAdapter:BaseQuickAdapter<ChildrenData,BaseViewHolder>(R.layout.item_warn_list){
    override fun convert(holder: BaseViewHolder, item: ChildrenData) {
        holder.getView<AppCompatTextView>(R.id.titleTv).apply {
            text = item.name
            isSelected = item.checked
        }
    }

}