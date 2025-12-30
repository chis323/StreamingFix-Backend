package org.example.streamingfix.service.serviceimpl;

import lombok.AllArgsConstructor;
import org.example.streamingfix.entity.Movie;
import org.example.streamingfix.repository.MovieRepo;
import org.example.streamingfix.service.MovieService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@AllArgsConstructor
@Service
public class MovieServiceImpl implements MovieService {
    private final MovieRepo movieRepo;

    @Override
    public Movie createMovie(Movie movie)
    {
        return movieRepo.save(movie);
    }

    @Override
    public Movie updateMovie(Long movieId, Movie updatedMovie) {
        movieRepo.findById(movieId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Movie with id %d not found", movieId)));
        return movieRepo.save(updatedMovie);
    }

    @Override
    public Movie findByMovieId(Long movieId)
    {
        return movieRepo.findById(movieId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Movie with id %d not found", movieId)));
    }

    @Override
    public void deleteMovie(Long movieId)
    {
        movieRepo.findById(movieId)
                 .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Movie with id %d not found", movieId)));
        movieRepo.deleteById(movieId);
    }

}
