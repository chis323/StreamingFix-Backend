package org.example.streamingfix.controller;

import lombok.RequiredArgsConstructor;
import org.example.streamingfix.dto.LibraryDto;
import org.example.streamingfix.dto.UserDto;
import org.example.streamingfix.entity.Library;
import org.example.streamingfix.entity.User;
import org.example.streamingfix.mapper.LibraryMapper;
import org.example.streamingfix.mapper.UserMapper;
import org.example.streamingfix.service.serviceimpl.UserServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserServiceImpl userService;

    @GetMapping("/{userId}")
    public UserDto getUser(@PathVariable Long userId) {
        User user = userService.findByUserId(userId);
        if (user == null) {
            throw new RuntimeException("User not found with id: " + userId);
        }
        return UserMapper.toDto(user);
    }

    @PostMapping("/{userId}/libraries/{libraryId}")
    public LibraryDto addLibraryToUser(@PathVariable Long userId,
                                       @PathVariable Long libraryId) {
        Library library = userService.addLibraryToUser(userId, libraryId);
        return LibraryMapper.toDto(library);
    }


    @GetMapping
    public List<UserDto> getAllUsers() {
        return userService.findAllUsers()
                .stream()
                .map(UserMapper::toDto)
                .toList();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserDto createUser(@RequestBody UserDto userDto) {
        User user = UserMapper.toEntity(userDto);
        user.setUserId(null); // ensure CREATE
        User saved = userService.createUser(user);
        return UserMapper.toDto(saved);
    }

    @PutMapping("/{userId}")
    public UserDto updateUser(
            @PathVariable Long userId,
            @RequestBody UserDto userDto
    ) {
        User existing = userService.findByUserId(userId);
        if (existing == null) {
            throw new RuntimeException("User not found with id: " + userId);
        }

        UserMapper.updateEntityFromDto(userDto, existing);
        User saved = userService.editUserCredentials(userId, existing);
        return UserMapper.toDto(saved);
    }

    @DeleteMapping("/{userId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUser(@PathVariable Long userId) {
        userService.deleteByUserId(userId);
    }
}
