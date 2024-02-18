/*
package com.remine.ui.community

import android.text.Layout
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.lifecycle.viewmodel.savedstate.R
import androidx.recyclerview.widget.RecyclerView

class DeclarationViewPagerAdapter (declarationList: ArrayList<Int>) : RecyclerView.Adapter<DeclarationViewPagerAdapter.PagerViewHolder>() {
    var item = declarationList

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = PagerViewHolder((parent))

    override fun getItemCount(): Int = item.size

    override fun onBindViewHolder(holder: PagerViewHolder, position: Int) {
        holder.items.setImageResource(item[position])
    }

    inner class PagerViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder
        (LayoutInflater.from(parent.context).inflate(R.layout., parent, false)) {
        val items = itemView.findViewById<Layout>(R.id.)

    }
}*/
