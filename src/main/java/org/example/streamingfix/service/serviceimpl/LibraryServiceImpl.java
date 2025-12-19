package org.example.streamingfix.service.serviceimpl;

import org.example.streamingfix.entity.Library;
import org.example.streamingfix.entity.Movie;
import org.example.streamingfix.repository.LibraryRepo;
import org.example.streamingfix.repository.MovieRepo;
import org.example.streamingfix.service.LibraryService;
import org.springframework.stereotype.Service;

@Service
public class LibraryServiceImpl implements LibraryService {
    private final LibraryRepo libraryRepo;
    private final MovieRepo movieRepo;

    public LibraryServiceImpl(LibraryRepo libraryRepo,MovieRepo movieRepo) {
        this.libraryRepo = libraryRepo;
        this.movieRepo = movieRepo;
    }


    @Override
    public Library createLibrary(Library library)
    {
        return libraryRepo.save(library);
    }

    @Override
    public Library findLibraryById(Long libraryId)
    {
        return libraryRepo.findById(libraryId).orElse(null);
    }

    @Override
    public Movie addMovieToLibrary(Long libraryId, Long movieId)
    {
        Library library = libraryRepo.findById(libraryId)
                .orElseThrow(() -> new RuntimeException("Library not found with id: " + libraryId));

        Movie movie = movieRepo.findById(movieId)
                .orElseThrow(() -> new RuntimeException("Movie not found with id: " + movieId));

        movie.setLibrary(library);
        return movieRepo.save(movie);
    }

    @Override
    public void deleteMovieFromLibrary(Long libraryId, Long movieId)
    {
        Library library = libraryRepo.findById(libraryId)
                .orElseThrow(() -> new RuntimeException("Library not found with id: " + libraryId));

        Movie movie = movieRepo.findById(movieId)
                .orElseThrow(() -> new RuntimeException("Movie not found with id: " + movieId));

        if (movie.getLibrary() == null || !movie.getLibrary().getLibraryId().equals(library.getLibraryId()))
        {
            throw new RuntimeException("Movie does not belong to this library");
        }

        movieRepo.delete(movie);

    }


}
