package com.example.gamesexplorer.ui.genre

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.gamesexplorer.databinding.FragmentGenreBinding

class GenreFragment : Fragment() {

    private var _binding: FragmentGenreBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: GenreViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentGenreBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(this).get(GenreViewModel::class.java)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.genreRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        val adapter = GenreAdapter(emptyList()) { genre ->
            viewModel.saveGenreSelection(genre)
            // Navigate to the main screen or handle genre selection
        }
        binding.genreRecyclerView.adapter = adapter

        viewModel.genres.observe(viewLifecycleOwner, { genres ->
            adapter.updateGenres(genres)
        })

        // Fetch genres with your API key
        viewModel.fetchGenres("2c2645a3d8ce4fba967cf75329ef5cd8")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
