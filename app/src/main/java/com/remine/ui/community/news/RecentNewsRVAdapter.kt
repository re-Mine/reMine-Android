package com.remine.ui.community.news

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.remine.R

class RecentNewsRVAdapter (val recentNewsItems : ArrayList<String>) : RecyclerView.Adapter<RecentNewsRVAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.recent_news_rv_item, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItems(recentNewsItems[position])
    }

    override fun getItemCount(): Int {
        return recentNewsItems.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bindItems(item : String) {

        }

    }


}