package com.example.pelisapp.ui.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.example.domain.interactors.movies.Movie
import com.example.pelisapp.R
import com.example.pelisapp.databinding.RowCatalogueItemBinding

class MovieRowAdapter(private val items: List<Movie>):
    RecyclerView.Adapter<MovieRowAdapter.MovieRowViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieRowViewHolder {
        val binding = RowCatalogueItemBinding.inflate(LayoutInflater.from(parent.context),
            parent, false)
        return MovieRowViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: MovieRowViewHolder, position: Int) {
        with(holder){
            with(items[position]){
                binding.txtRowMovieTitle.text = title
                binding.txtAverage.text = voteAverage.toString()
                Glide.with(holder.itemView.context)
                    .load("https://image.tmdb.org/t/p/w500${posterUrl}")
                    .into(binding.imgMovie)
                binding.imgMoreInfo.setOnClickListener {
                    showCustomDialog()
                }
                binding.txtMoreInfo.setOnClickListener {
                    showCustomDialog()
                }
            }
        }
    }

    private fun showCustomDialog(){

    }


    inner class MovieRowViewHolder(val binding: RowCatalogueItemBinding)
        :RecyclerView.ViewHolder(binding.root)
}