package com.example.movielibrarye193.ui.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.movielibrarye193.databinding.ItemMovieBinding
import com.example.movielibrarye193.model.Movie

class MovieAdapter(private val onClick: (Movie) -> Unit) : RecyclerView.Adapter<MovieAdapter.ViewHolder>() {

    private var movies = emptyList<Movie>()

    fun submitList(list: List<Movie>) {
        movies = list
        notifyDataSetChanged()
    }

    inner class ViewHolder(private val binding: ItemMovieBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(movie: Movie) {
            binding.tvItemTitle.text = movie.title
            binding.tvItemYearGenre.text = "${movie.year} · ${movie.genre}"
            binding.tvItemWatched.text = if (movie.watched) "Vista" else "Pendiente"
            binding.root.setOnClickListener { onClick(movie) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(movies[position])

    override fun getItemCount() = movies.size
}
