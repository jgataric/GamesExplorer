package com.example.gamesexplorer.ui.onboarding

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.gamesexplorer.data.model.Genre
import com.example.gamesexplorer.databinding.ItemGenreChipBinding

class GenreChipAdapter(private var genres: List<Genre>, private val onGenreClicked: (Genre) -> Unit) :
    RecyclerView.Adapter<GenreChipAdapter.GenreChipViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GenreChipViewHolder {
        val binding = ItemGenreChipBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return GenreChipViewHolder(binding)
    }

    override fun onBindViewHolder(holder: GenreChipViewHolder, position: Int) {
        holder.bind(genres[position])
    }

    override fun getItemCount() = genres.size

    fun updateGenres(newGenres: List<Genre>) {
        genres = newGenres
        notifyDataSetChanged()
    }

    inner class GenreChipViewHolder(private val binding: ItemGenreChipBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(genre: Genre) {
            binding.chip.text = genre.name
            binding.chip.setOnClickListener {
                onGenreClicked(genre)
            }
        }
    }
}
