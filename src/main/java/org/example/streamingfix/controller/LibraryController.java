package org.example.streamingfix.controller;

import lombok.RequiredArgsConstructor;
import org.example.streamingfix.dto.LibraryDto;
import org.example.streamingfix.dto.MovieDto;
import org.example.streamingfix.entity.Library;
import org.example.streamingfix.entity.Movie;
import org.example.streamingfix.mapper.LibraryMapper;
import org.example.streamingfix.mapper.MovieMapper;
import org.example.streamingfix.service.LibraryService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/libraries")
@RequiredArgsConstructor
public class LibraryController {

    private final LibraryService libraryService;

    @GetMapping("/{libraryId}")
    public LibraryDto getLibrary(@PathVariable Long libraryId) {
        Library library = libraryService.findLibraryById(libraryId);
        if (library == null) {
            throw new RuntimeException("Library not found with id: " + libraryId);
        }
        return LibraryMapper.toDto(library);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public LibraryDto createLibrary(@RequestBody LibraryDto libraryDto) {
        Library library = LibraryMapper.toEntity(libraryDto);
        library.setLibraryId(null);
        Library saved = libraryService.createLibrary(library);
        return LibraryMapper.toDto(saved);
    }

    @PostMapping("/{libraryId}/movies/{movieId}")
    @ResponseStatus(HttpStatus.CREATED)
    public MovieDto addMovieToLibrary(@PathVariable Long libraryId,
                                      @PathVariable Long movieId) {
        Movie movie = libraryService.addMovieToLibrary(libraryId, movieId);
        if (movie == null) {
            throw new RuntimeException(
                    "Could not add movie " + movieId + " to library " + libraryId
            );
        }
        return MovieMapper.toDto(movie);
    }

    @DeleteMapping("/{libraryId}/movies/{movieId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteMovieFromLibrary(@PathVariable Long libraryId,
                                       @PathVariable Long movieId) {
        libraryService.deleteMovieFromLibrary(libraryId, movieId);
    }
}
