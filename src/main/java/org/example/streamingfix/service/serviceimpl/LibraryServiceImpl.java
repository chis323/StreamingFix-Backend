package org.example.streamingfix.service.serviceimpl;

import lombok.AllArgsConstructor;
import org.example.streamingfix.entity.Library;
import org.example.streamingfix.entity.Movie;
import org.example.streamingfix.repository.LibraryRepo;
import org.example.streamingfix.repository.MovieRepo;
import org.example.streamingfix.service.LibraryService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@AllArgsConstructor
@Service
public class LibraryServiceImpl implements LibraryService {
    private final LibraryRepo libraryRepo;
    private final MovieRepo movieRepo;


    @Override
    public Library createLibrary(Library library)
    {
        return libraryRepo.save(library);
    }

    @Override
    public Library findLibraryById(Long libraryId)
    {
        return libraryRepo.findById(libraryId).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Library with id %d not found", libraryId)));
    }

    @Override
    public Movie addMovieToLibrary(Long libraryId, Long movieId)
    {
        Library library = libraryRepo.findById(libraryId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Library with id %d not found", libraryId)));

        Movie movie = movieRepo.findById(movieId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Movie with id %d not found", movieId)));

        movie.setLibrary(library);
        return movieRepo.save(movie);
    }

    @Override
    public void deleteMovieFromLibrary(Long libraryId, Long movieId)
    {
        Library library = libraryRepo.findById(libraryId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Library with id %d not found", libraryId)));

        Movie movie = movieRepo.findById(movieId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Movie with id %d not found", movieId)));

        if (movie.getLibrary() == null || !movie.getLibrary().getLibraryId().equals(library.getLibraryId()))
        {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Movie does not belong to this library or library is empty");
        }

        movieRepo.delete(movie);

    }


}
