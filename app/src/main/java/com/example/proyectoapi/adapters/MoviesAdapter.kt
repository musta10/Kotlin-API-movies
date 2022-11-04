package com.example.proyectoapi.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.proyectoapi.R
import com.example.proyectoapi.models.Genero
import com.example.proyectoapi.models.Movie
import com.example.proyectoapi.remote.ApiService


class MoviesAdapter(val onClick: (Movie) -> Unit) :
    RecyclerView.Adapter<MoviesAdapter.ViewHolder>() {
    var data = mutableListOf<Movie>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_movies, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int {
        return data.size
    }

    fun updateData(MovieData: List<Movie>) {
        data = MovieData.toMutableList()
        notifyDataSetChanged()
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val title = itemView.findViewById<TextView>(R.id.name)
        val card = itemView.findViewById<CardView>(R.id.card)
        val poster = itemView.findViewById<ImageView>(R.id.image_movie)


        // add images with GLIDE
        fun bind(item: Movie) {
            title.text = item.title
            val urlImage = ApiService.URL_IMAGES + item.poster_path
            Glide.with(card).load(urlImage).into(poster)
            card.setOnClickListener {
                Log.v("pulso sobre", item.id.toString())
                onClick(item)
            }
        }
    }
}
