package org.example.streamingfix.service.serviceimpl;

import org.example.streamingfix.entity.Movie;
import org.example.streamingfix.repository.MovieRepo;
import org.example.streamingfix.service.MovieService;
import org.springframework.stereotype.Service;

@Service
public class MovieServiceImpl implements MovieService {
    private final MovieRepo movieRepo;
    public MovieServiceImpl(MovieRepo movieRepo) {
        this.movieRepo = movieRepo;
    }

    @Override
    public Movie createMovie(Movie movie)
    {
        return movieRepo.save(movie);
    }

    @Override
    public Movie updateMovie(Long movieId, Movie movie) {
        return movieRepo.save(movie);
    }

    @Override
    public Movie findByMovieId(Long movieId)
    {
        return movieRepo.findById(movieId).orElse(null);
    }

    @Override
    public void deleteMovie(Long movieId)
    {
        movieRepo.deleteById(movieId);
    }

}
