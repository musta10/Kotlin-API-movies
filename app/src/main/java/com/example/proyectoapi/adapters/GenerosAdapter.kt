package com.example.proyectoapi.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewParent
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.proyectoapi.R
import com.example.proyectoapi.models.Genero
import okhttp3.internal.notify
import java.text.FieldPosition

class GenerosAdapter(val onClick: (Genero) -> Unit): RecyclerView.Adapter<GenerosAdapter.ViewHolder>() {
    var data = mutableListOf<Genero>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_generos, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int){
        holder.bind(data[position])
    }
    override fun getItemCount() : Int{
        return  data.size
    }

    fun updateData(generosData: List<Genero>){
        data = generosData.toMutableList()
        notifyDataSetChanged()
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val genero = itemView.findViewById<TextView>(R.id.name)
        val card = itemView.findViewById<CardView>(R.id.card)

        fun bind(item: Genero) {
            genero.text = item.name
            card.setOnClickListener {
                Log.v("pulso sobre", item.id.toString())
                onClick(item)
            }
        }
    }
}