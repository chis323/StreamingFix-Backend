package org.example.streamingfix.dto;
import java.util.List;

public record LibraryDto(
        Long libraryId,
        String libraryTitle,
        List<MovieDto> movies
) {
}
