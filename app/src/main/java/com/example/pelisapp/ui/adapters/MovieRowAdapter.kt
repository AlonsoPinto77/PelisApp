package com.example.pelisapp.ui.adapters

import android.app.Dialog
import android.content.Context
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.example.domain.interactors.movies.Movie
import com.example.pelisapp.R
import com.example.pelisapp.databinding.RowCatalogueItemBinding

class MovieRowAdapter(private val items: ArrayList<Movie>, private val context: Context):
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
                    showCustomDialog(context, this)
                }
                binding.txtMoreInfo.setOnClickListener {
                    showCustomDialog(context, this)
                }
            }
        }
    }

    private fun showCustomDialog(context: Context, movie: Movie){
        val dialog = Dialog(context)
        dialog.setContentView(R.layout.dialog_movie_detail)
        val poster : ImageView = dialog.findViewById(R.id.imgPosterView)
        val window = dialog.window
        val param: WindowManager.LayoutParams = window?.attributes!!
        param.gravity = Gravity.CENTER

        dialog.window?.setLayout(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        val title : TextView = dialog.findViewById(R.id.txtMovieTitle)
        val votes : TextView = dialog.findViewById(R.id.txtVotes)
        val releaseDate: TextView = dialog.findViewById(R.id.txtReleaseDate)
        val summary : TextView = dialog.findViewById(R.id.txtSummary)
        val closeButton : ImageView = dialog.findViewById(R.id.imgClose)

        Glide.with(context)
            .load("https://image.tmdb.org/t/p/w500${movie.posterUrl}")
            .into(poster)

        title.text = movie.title
        votes.text = "Votos: ${movie.voteAverage}"
        releaseDate.text = "Fecha de Lanzamiento: ${movie.releaseDate}"
        summary.text = movie.overview

        closeButton.setOnClickListener {
            dialog.dismiss()
        }

        dialog.setCancelable(true)
        dialog.show()
    }


    inner class MovieRowViewHolder(val binding: RowCatalogueItemBinding)
        :RecyclerView.ViewHolder(binding.root)
}