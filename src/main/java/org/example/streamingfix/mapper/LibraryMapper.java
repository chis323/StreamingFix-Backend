package org.example.streamingfix.mapper;

import org.example.streamingfix.dto.LibraryDto;
import org.example.streamingfix.entity.Library;

import java.util.Collections;

public final class LibraryMapper {

    private LibraryMapper() {}

    public static LibraryDto toDto(Library library) {
        if (library == null) return null;

        return new LibraryDto(
                library.getLibraryId(),
                library.getLibraryTitle(),
                library.getMovies() == null
                        ? Collections.emptyList()
                        : library.getMovies().stream().map(MovieMapper::toDto).toList()
        );
    }


    public static Library toEntity(LibraryDto dto) {
        if (dto == null) return null;

        Library library = new Library();
        library.setLibraryTitle(dto.libraryTitle());
        return library;
    }
}
