package com.example.movielibrarye193.ui.edit

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.movielibrarye193.databinding.FragmentMovieEditBinding
import com.example.movielibrarye193.db.AppDatabase
import com.example.movielibrarye193.model.Movie
import com.example.movielibrarye193.repository.MovieRepository
import com.example.movielibrarye193.viewmodel.MovieViewModel

class MovieEditFragment : Fragment() {

    private var _binding: FragmentMovieEditBinding? = null
    private val binding get() = _binding!!

    private val args: MovieEditFragmentArgs by navArgs()

    private val viewModel: MovieViewModel by viewModels {
        val db = AppDatabase.getDatabase(requireContext())
        MovieViewModel.factory(MovieRepository(db.movieDao()))
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentMovieEditBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val movieId = args.movieId

        if (movieId != 0) {
            viewModel.getMovie(movieId).observe(viewLifecycleOwner) { movie ->
                movie ?: return@observe
                binding.etTitle.setText(movie.title)
                binding.etYear.setText(movie.year.toString())
                binding.etGenre.setText(movie.genre)
                binding.etRating.setText(movie.rating.toString())
                binding.cbWatched.isChecked = movie.watched
            }
        }

        binding.btnSave.setOnClickListener {
            val title = binding.etTitle.text.toString()
            val year = binding.etYear.text.toString().toIntOrNull() ?: 0
            val genre = binding.etGenre.text.toString()
            val rating = binding.etRating.text.toString().toFloatOrNull() ?: 0f
            val watched = binding.cbWatched.isChecked

            if (movieId == 0) {
                viewModel.insert(Movie(title = title, year = year, genre = genre, rating = rating, watched = watched))
            } else {
                viewModel.update(Movie(id = movieId, title = title, year = year, genre = genre, rating = rating, watched = watched))
            }
            findNavController().popBackStack()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
