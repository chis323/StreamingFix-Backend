package org.example.streamingfix.controller;

import lombok.RequiredArgsConstructor;
import org.example.streamingfix.dto.MovieDto;
import org.example.streamingfix.entity.Movie;
import org.example.streamingfix.mapper.MovieMapper;
import org.example.streamingfix.service.MovieService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/movies")
@RequiredArgsConstructor
public class MovieController {

    private final MovieService movieService;

    @GetMapping("/{movieId}")
    public MovieDto getMovie(@PathVariable Long movieId) {
        Movie movie = movieService.findByMovieId(movieId);
        if (movie == null) {
            throw new RuntimeException("Movie not found with id: " + movieId);
        }
        return MovieMapper.toDto(movie);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public MovieDto createMovie(@RequestBody MovieDto movieDto) {
        Movie movie = MovieMapper.toEntity(movieDto);
        movie.setMovieId(null); // ensure CREATE
        Movie saved = movieService.createMovie(movie);
        return MovieMapper.toDto(saved);
    }

    @PutMapping("/{movieId}")
    public MovieDto updateMovie(@PathVariable Long movieId,
                                @RequestBody MovieDto movieDto) {
        Movie existing = movieService.findByMovieId(movieId);
        if (existing == null) {
            throw new RuntimeException("Movie not found with id: " + movieId);
        }

        MovieMapper.updateEntityFromDto(movieDto, existing);
        Movie saved = movieService.updateMovie(movieId, existing);
        return MovieMapper.toDto(saved);
    }

    @DeleteMapping("/{movieId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteMovie(@PathVariable Long movieId) {
        movieService.deleteMovie(movieId);
    }
}
