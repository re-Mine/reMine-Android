package com.remine.ui.community

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.recyclerview.widget.RecyclerView
import com.remine.R

class NewsDeclarationViewPagerAdapter (var list : ArrayList<Int>,var context : Context)  : RecyclerView.Adapter<RecyclerView.ViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        var view = LayoutInflater.from(parent.context).inflate(R.layout.declaration_list_item,parent,false)
        return viewHolder(view)

    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as viewHolder).layout.setBackgroundColor(list.get(position))
    }

    inner class viewHolder(var view : View) : RecyclerView.ViewHolder(view){
        var layout : LinearLayout  = view.findViewById(R.id.declaration_item_list)
    }
}