package com.example.gamesexplorer.ui.genre

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.gamesexplorer.R
import com.example.gamesexplorer.data.model.Genre
import com.example.gamesexplorer.databinding.ItemGenreBinding

class GenreAdapter(private var genres: List<Genre>, private val onSaveClicked: (Genre) -> Unit) :
    RecyclerView.Adapter<GenreAdapter.GenreViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GenreViewHolder {
        val binding = ItemGenreBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return GenreViewHolder(binding)
    }

    override fun onBindViewHolder(holder: GenreViewHolder, position: Int) {
        holder.bind(genres[position])
    }

    override fun getItemCount() = genres.size

    fun updateGenres(newGenres: List<Genre>) {
        genres = newGenres
        notifyDataSetChanged()
    }

    inner class GenreViewHolder(private val binding: ItemGenreBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(genre: Genre) {
            binding.genreImage.load(genre.imageBackground) {
                placeholder(R.drawable.placeholder)
            }
            binding.genreTitle.text = genre.name
            binding.genreFollowers.text = "${genre.gamesCount} followers"
            binding.genreDescription.text = genre.slug
            binding.saveButton.setOnClickListener {
                onSaveClicked(genre)
            }
        }
    }
}
