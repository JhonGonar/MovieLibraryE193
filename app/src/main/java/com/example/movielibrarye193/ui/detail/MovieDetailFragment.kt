package com.example.movielibrarye193.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.movielibrarye193.databinding.FragmentMovieDetailBinding
import com.example.movielibrarye193.db.AppDatabase
import com.example.movielibrarye193.repository.MovieRepository
import com.example.movielibrarye193.viewmodel.MovieViewModel

class MovieDetailFragment : Fragment() {

    private var _binding: FragmentMovieDetailBinding? = null
    private val binding get() = _binding!!

    private val args: MovieDetailFragmentArgs by navArgs()

    private val viewModel: MovieViewModel by viewModels {
        val db = AppDatabase.getDatabase(requireContext())
        MovieViewModel.factory(MovieRepository(db.movieDao()))
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentMovieDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val movieId = args.movieId

        viewModel.getMovie(movieId).observe(viewLifecycleOwner) { movie ->
            movie ?: return@observe
            binding.tvTitle.text = movie.title
            binding.tvYear.text = "Año: ${movie.year}"
            binding.tvGenre.text = "Género: ${movie.genre}"
            binding.tvRating.text = "Calificación: ${movie.rating}"
            binding.tvWatched.text = if (movie.watched) "Estado: Vista ✓" else "Estado: Pendiente"
            binding.btnToggleWatched.text = if (movie.watched) "Marcar como no vista" else "Marcar como vista"

            binding.btnToggleWatched.setOnClickListener {
                viewModel.update(movie.copy(watched = !movie.watched))
            }
            binding.btnDelete.setOnClickListener {
                viewModel.delete(movie)
                findNavController().popBackStack()
            }
        }

        binding.btnEdit.setOnClickListener {
            val action = MovieDetailFragmentDirections.actionDetailToEdit(args.movieId)
            findNavController().navigate(action)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
