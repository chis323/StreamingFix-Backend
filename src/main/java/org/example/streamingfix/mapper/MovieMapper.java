package org.example.streamingfix.mapper;

import org.example.streamingfix.dto.MovieDto;
import org.example.streamingfix.entity.Movie;

public final class MovieMapper {

    private MovieMapper() {
        // utility class
    }

    public static MovieDto toDto(Movie movie) {
        if (movie == null) {
            return null;
        }

        return new MovieDto(
                movie.getMovieId(),
                movie.getMovieTitle(),
                movie.getMovieDescription(),
                movie.getDuration()
        );
    }

    public static Movie toEntity(MovieDto dto) {
        if (dto == null) {
            return null;
        }

        Movie movie = new Movie();
        movie.setMovieTitle(dto.movieTitle());
        movie.setMovieDescription(dto.movieDescription());
        movie.setDuration(dto.duration());

        return movie;
    }

    public static void updateEntityFromDto(MovieDto dto, Movie movie) {
        if (dto == null || movie == null) {
            return;
        }

        if (dto.movieTitle() != null) {
            movie.setMovieTitle(dto.movieTitle());
        }
        if (dto.movieDescription() != null) {
            movie.setMovieDescription(dto.movieDescription());
        }
        if (dto.duration() != null) {
            movie.setDuration(dto.duration());
        }
    }
}
