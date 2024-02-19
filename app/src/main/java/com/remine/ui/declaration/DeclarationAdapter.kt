package com.remine.ui.declaration

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.remine.R
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class DeclarationAdapter(private val resultData: List<Declaration>) : RecyclerView.Adapter<DeclarationAdapter.DeclarationViewHolder>(){

//    var datas = mutableListOf<Declaration>()
    private var itemClickListener: OnItemClickListener? = null

    interface OnItemClickListener {
        fun onItemClick(item: String)
    }

    fun setOnItemClickListener(listener: OnItemClickListener) {
        itemClickListener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DeclarationViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_declaration, parent, false)
        return DeclarationViewHolder(view)
    }

    override fun onBindViewHolder(holder: DeclarationViewHolder, position: Int) {
        val declaration = resultData?.get(position)
        if (declaration != null) {
            holder.bind(declaration)
        }
    }

    override fun getItemCount(): Int {
        return resultData?.size ?: 0
    }

    inner class DeclarationViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {


        fun bind(declaration: Declaration) {
            val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSSSS", Locale.getDefault())
            val date = inputFormat.parse(declaration.date)
            val calendar = Calendar.getInstance()
            calendar.time = date
            val month = calendar.get(Calendar.MONTH) + 1 // 월은 0부터 시작하므로 1을 더해줌
            val day = calendar.get(Calendar.DAY_OF_MONTH)

            // String.format을 사용하여 원하는 형식으로 포맷팅
            val declDate =  String.format("%02d월 %02d일", month, day)
            itemView.findViewById<TextView>(R.id.tv_date).text = declDate
            itemView.findViewById<TextView>(R.id.tv_declaration).text = declaration.declaration
            //itemView.findViewById<TextView>(R.id.btn_replay).text = declaration.voice
        }

        init {
            // itemView를 클릭했을 때 해당 항목의 ProductData를 클릭 리스너를 통해 전달
            itemView.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    val item = resultData[position].voice
                    itemClickListener?.onItemClick(item)
                    //click listener 설정
                    //navigateToExperienceDetail(item)
                }
            }
        }
    }
}
