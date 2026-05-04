package com.example.movielibrarye193.ui.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.movielibrarye193.databinding.FragmentMovieListBinding
import com.example.movielibrarye193.db.AppDatabase
import com.example.movielibrarye193.repository.MovieRepository
import com.example.movielibrarye193.viewmodel.MovieViewModel

class MovieListFragment : Fragment() {

    private var _binding: FragmentMovieListBinding? = null
    private val binding get() = _binding!!

    private lateinit var adapter: MovieAdapter

    private val viewModel: MovieViewModel by viewModels {
        val db = AppDatabase.getDatabase(requireContext())
        MovieViewModel.factory(MovieRepository(db.movieDao()))
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentMovieListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = MovieAdapter { movie ->
            val action = MovieListFragmentDirections.actionListToDetail(movie.id)
            findNavController().navigate(action)
        }
        binding.rvMovies.layoutManager = LinearLayoutManager(requireContext())
        binding.rvMovies.adapter = adapter

        viewModel.movies.observe(viewLifecycleOwner) { movies ->
            adapter.submitList(movies)
            binding.tvCount.text = "Total: ${movies.size}"
            binding.tvEmpty.visibility = if (movies.isEmpty()) View.VISIBLE else View.GONE
        }

        binding.fabAdd.setOnClickListener {
            val action = MovieListFragmentDirections.actionListToEdit()
            findNavController().navigate(action)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
