package com.remine.ui.declaration

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.remine.R

class DeclarationAdapter(private val declarationList: MutableList<Declaration>) : RecyclerView.Adapter<DeclarationAdapter.DeclarationViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DeclarationViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_declaration, parent, false)
        return DeclarationViewHolder(view)
    }

    override fun onBindViewHolder(holder: DeclarationViewHolder, position: Int) {
        val declaration = declarationList[position]
        holder.bind(declaration)
    }

    override fun getItemCount(): Int {
        return declarationList.size
    }

    inner class DeclarationViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(declaration: Declaration) {
            itemView.findViewById<TextView>(R.id.tv_date).text = declaration.date
            itemView.findViewById<TextView>(R.id.tv_declaration).text = declaration.declaration
        }
    }
}
