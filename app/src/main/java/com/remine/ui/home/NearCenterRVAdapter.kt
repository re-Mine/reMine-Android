package com.remine.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.remine.R

class NearCenterRVAdapter (val nearCenterItems : ArrayList<String>) : RecyclerView.Adapter<NearCenterRVAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.center_rv_item, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItems(nearCenterItems[position])
    }

    override fun getItemCount(): Int {
        return nearCenterItems.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val btnSend1: Button = itemView.findViewById(R.id.buttonSend1)

        private val btnSend2: Button = itemView.findViewById(R.id.buttonSend2)

        private val btnSend3: Button = itemView.findViewById(R.id.buttonSend3)

        private val btnSend4: Button = itemView.findViewById(R.id.buttonSend4)

        fun bindItems(item: String) {
            btnSend1.setOnClickListener {
                Toast.makeText(it.context, "보고서 전송이 완료되었습니다.", Toast.LENGTH_SHORT).show()
            }

            btnSend2.setOnClickListener {
                Toast.makeText(it.context, "보고서 전송이 완료되었습니다.", Toast.LENGTH_SHORT).show()
            }

            btnSend3.setOnClickListener {
                Toast.makeText(it.context, "보고서 전송이 완료되었습니다.", Toast.LENGTH_SHORT).show()
            }

            btnSend4.setOnClickListener {
                Toast.makeText(it.context, "보고서 전송이 완료되었습니다.", Toast.LENGTH_SHORT).show()
            }
        }
    }
}