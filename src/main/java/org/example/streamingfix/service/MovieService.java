package org.example.streamingfix.service;

import org.example.streamingfix.entity.Movie;

public interface MovieService {
    Movie createMovie(Movie movie);
    void deleteMovie(Long movieId);
    Movie updateMovie(Long movieId, Movie movie);
    Movie findByMovieId(Long movieId);
}
