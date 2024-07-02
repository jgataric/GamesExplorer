package com.example.gamesexplorer.ui.onboarding

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.gamesexplorer.R
import com.example.gamesexplorer.data.model.Genre
import com.example.gamesexplorer.databinding.FragmentFinalBinding
import com.google.android.material.chip.Chip

class FinalFragment : Fragment() {

    private var _binding: FragmentFinalBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: OnboardingViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFinalBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(this).get(OnboardingViewModel::class.java)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.selectedGenres.observe(viewLifecycleOwner, { genres ->
            genres?.let { createSelectedGenreChips(it) }
        })

        binding.previousButton.setOnClickListener {
            findNavController().navigateUp()
        }

        binding.useAppButton.setOnClickListener {
        }
    }

    private fun createSelectedGenreChips(genres: List<Genre>) {
        binding.selectedGenreFlexboxLayout.removeAllViews()
        for (genre in genres) {
            val chip = LayoutInflater.from(context).inflate(R.layout.item_genre_chip, binding.selectedGenreFlexboxLayout, false) as Chip
            chip.text = genre.name
            chip.isClickable = false
            binding.selectedGenreFlexboxLayout.addView(chip)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
