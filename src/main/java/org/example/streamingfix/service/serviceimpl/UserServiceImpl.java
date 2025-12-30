package org.example.streamingfix.service.serviceimpl;

import lombok.AllArgsConstructor;
import org.example.streamingfix.entity.Library;
import org.example.streamingfix.entity.User;
import org.example.streamingfix.repository.LibraryRepo;
import org.example.streamingfix.repository.UserRepo;
import org.example.streamingfix.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@AllArgsConstructor
@Service
public class UserServiceImpl implements UserService {
    private final UserRepo userRepo;
    private final LibraryRepo libraryRepo;

    @Override
    public User createUser(User user)
    {
        return userRepo.save(user);
    }

    public List<User> findAllUsers() {
        return userRepo.findAll();
    }


    @Override
    public User findByUserId(Long userId)
    {
        return userRepo.findById(userId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("User with id %d not found", userId)));
    }

    @Override
    public User editUserCredentials(Long userId, User user)
    {
        userRepo.findById(userId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("User with id %d not found", userId)));
        return userRepo.save(user);
    }

    @Override
    public void deleteByUserId(Long userId){
        userRepo.findById(userId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("User with id %d not found", userId)));
        userRepo.deleteById(userId);
    }

    @Override
    public Library addLibraryToUser(Long userId, Long libraryId) {
        User user = userRepo.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + userId));

        Library library = libraryRepo.findById(libraryId)
                .orElseThrow(() -> new RuntimeException("Library not found with id: " + libraryId));

        user.setLibrary(library);
        userRepo.save(user);

        return library;
    }
}
