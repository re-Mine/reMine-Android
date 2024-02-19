package com.remine.ui.community

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.recyclerview.widget.RecyclerView
import com.remine.R

class NewsDeclarationViewPagerAdapter (var list : ArrayList<Int>,var context : Context)  : RecyclerView.Adapter<RecyclerView.ViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        return when(viewType) {
            R.layout.declaration_list_item1 -> ViewHolder1(LayoutInflater.from(parent.context).inflate(viewType, parent, false))
            R.layout.declaration_list_item2 -> ViewHolder2(LayoutInflater.from(parent.context).inflate(viewType, parent, false))
            R.layout.declaration_list_item3 -> ViewHolder3(LayoutInflater.from(parent.context).inflate(viewType, parent, false))
            else -> throw IllegalArgumentException("Invalid view type")
        }

    }
    override fun getItemViewType(position: Int): Int {
        return list[position]
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(holder) {
            is ViewHolder1 -> holder.layout.setBackgroundColor(list[position])
            is ViewHolder2 -> holder.layout.setBackgroundColor(list[position])
            is ViewHolder3 -> holder.layout.setBackgroundColor(list[position])
        }
    }

    inner class ViewHolder1(var view : View) : RecyclerView.ViewHolder(view){
        var layout : LinearLayout  = view.findViewById(R.id.declaration_item_list1)
    }

    inner class ViewHolder2(var view : View) : RecyclerView.ViewHolder(view){
        var layout : LinearLayout  = view.findViewById(R.id.declaration_item_list2)
    }

    inner class ViewHolder3(var view : View) : RecyclerView.ViewHolder(view){
        var layout : LinearLayout  = view.findViewById(R.id.declaration_item_list3)
    }
}