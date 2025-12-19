package org.example.streamingfix.service;


import org.example.streamingfix.entity.Library;
import org.example.streamingfix.entity.Movie;

public interface LibraryService {
    Library createLibrary(Library library);
    Library findLibraryById(Long libraryId);
    Movie addMovieToLibrary(Long libraryId, Long movieId);
    void  deleteMovieFromLibrary(Long libraryId, Long movieId);
}
