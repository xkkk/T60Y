package com.baorun.handbook.t60y.feature.scene.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import com.baorun.handbook.t60y.R
import com.baorun.handbook.t60y.data.ChildrenData
import com.baorun.handbook.t60y.databinding.ItemSceneListBinding

class NativeSceneAdapter(private val list:List<ChildrenData>):RecyclerView.Adapter<NativeSceneAdapter.ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
       val binding =  ItemSceneListBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val bean = list[position]
        holder.mCoverIv.setImageResource(bean.coverRes?:0)
        holder.mTitleTv.text = bean.name
    }

    override fun getItemCount(): Int = list.size


    class ViewHolder(view:View):RecyclerView.ViewHolder(view){

        val mCoverIv = view.findViewById<AppCompatImageView>(R.id.mCoverIv)
        val mTitleTv = view.findViewById<AppCompatTextView>(R.id.mTitleTv)



    }

}