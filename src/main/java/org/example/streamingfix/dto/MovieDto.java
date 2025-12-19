package org.example.streamingfix.dto;

public record MovieDto(
        Long movieId,
        String movieTitle,
        String movieDescription,
        Integer duration
) {
}
