package org.example.streamingfix;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.List;

import org.example.streamingfix.controller.LibraryController;
import org.example.streamingfix.controller.MovieController;
import org.example.streamingfix.controller.UserController;
import org.example.streamingfix.dto.LibraryDto;
import org.example.streamingfix.dto.MovieDto;
import org.example.streamingfix.dto.UserDto;
import org.example.streamingfix.entity.Library;
import org.example.streamingfix.entity.Movie;
import org.example.streamingfix.entity.User;
import org.example.streamingfix.service.LibraryService;
import org.example.streamingfix.service.MovieService;
import org.example.streamingfix.service.serviceimpl.UserServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class StreamingFixApplicationTests {

    @Mock private LibraryService libraryService;
    @Mock private MovieService movieService;
    @Mock private UserServiceImpl userService;

    //library controller

    @Test
    void getLibrary_whenFound_returnsDto() {
        LibraryController controller = new LibraryController(libraryService);
        when(libraryService.findLibraryById(1L)).thenReturn(new Library());

        LibraryDto dto = controller.getLibrary(1L);

        assertThat(dto).isNotNull();
        verify(libraryService).findLibraryById(1L);
        verifyNoMoreInteractions(libraryService);
    }

    @Test
    void getLibrary_whenMissing_throwsException() {
        LibraryController controller = new LibraryController(libraryService);
        when(libraryService.findLibraryById(99L)).thenReturn(null);

        assertThatThrownBy(() -> controller.getLibrary(99L))
                .isInstanceOf(RuntimeException.class)
                .hasMessageContaining("Library not found");

        verify(libraryService).findLibraryById(99L);
        verifyNoMoreInteractions(libraryService);
    }

    //movie controller

    @Test
    void getMovie_whenFound_returnsDto() {
        MovieController controller = new MovieController(movieService);
        when(movieService.findByMovieId(1L)).thenReturn(new Movie());

        MovieDto dto = controller.getMovie(1L);

        assertThat(dto).isNotNull();
        verify(movieService).findByMovieId(1L);
        verifyNoMoreInteractions(movieService);
    }

    @Test
    void updateMovie_whenMissing_throwsException() {
        MovieController controller = new MovieController(movieService);
        when(movieService.findByMovieId(99L)).thenReturn(null);

        assertThatThrownBy(() ->
                controller.updateMovie(
                        99L,
                        new MovieDto(null, "Test", "Desc", 120)
                )
        )
                .isInstanceOf(RuntimeException.class)
                .hasMessageContaining("Movie not found");

        verify(movieService).findByMovieId(99L);
        verify(movieService, never()).updateMovie(anyLong(), any(Movie.class));
        verifyNoMoreInteractions(movieService);
    }

    //user controller

    @Test
    void getAllUsers_returnsDtos() {
        UserController controller = new UserController(userService);
        when(userService.findAllUsers()).thenReturn(List.of(new User(), new User()));

        List<UserDto> result = controller.getAllUsers();

        assertThat(result).hasSize(2);
        verify(userService).findAllUsers();
        verifyNoMoreInteractions(userService);
    }

    @Test
    void deleteUser_callsService() {
        UserController controller = new UserController(userService);

        controller.deleteUser(1L);

        verify(userService).deleteByUserId(1L);
        verifyNoMoreInteractions(userService);
    }
}
