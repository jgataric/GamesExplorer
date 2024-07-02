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
import com.example.gamesexplorer.databinding.FragmentOnboardingBinding
import com.google.android.material.chip.Chip

class OnboardingFragment : Fragment() {

    private var _binding: FragmentOnboardingBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: OnboardingViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentOnboardingBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(this).get(OnboardingViewModel::class.java)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.genres.observe(viewLifecycleOwner, { genres ->
            genres?.let { createGenreChips(it) }
        })

        binding.previousButton.setOnClickListener {
            findNavController().navigateUp()
        }

        binding.nextButton.setOnClickListener {
            findNavController().navigate(R.id.action_onboardingFragment_to_finalFragment)
        }

        viewModel.fetchGenres("2c2645a3d8ce4fba967cf75329ef5cd8")
    }

    private fun createGenreChips(genres: List<Genre>) {
        binding.genreFlexboxLayout.removeAllViews()
        for (genre in genres) {
            val chip = LayoutInflater.from(context).inflate(R.layout.item_genre_chip, binding.genreFlexboxLayout, false) as Chip
            chip.text = genre.name
            chip.setOnClickListener {
                viewModel.toggleGenreSelection(genre)
                updateProceedButton()
            }
            binding.genreFlexboxLayout.addView(chip)
        }
    }

    private fun updateProceedButton() {
        binding.nextButton.isEnabled = viewModel.canProceed()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
