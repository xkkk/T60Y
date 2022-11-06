package com.baorun.handbook.t60y.feature.guide.adapter.node

import com.baorun.handbook.t60y.data.ChildrenData
import com.chad.library.adapter.base.entity.node.BaseNode

class ForthNode(val data: ChildrenData) : BaseNode() {
    override val childNode: MutableList<BaseNode>?
        get() = null
}