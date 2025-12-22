package org.example.streamingfix.service.serviceimpl;

import lombok.AllArgsConstructor;
import org.example.streamingfix.entity.Library;
import org.example.streamingfix.entity.User;
import org.example.streamingfix.repository.LibraryRepo;
import org.example.streamingfix.repository.UserRepo;
import org.example.streamingfix.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class UserServiceImpl implements UserService {
    private final UserRepo userRepo;
    private final LibraryRepo libraryRepo;

    public List<User> findAllUsers() {
        return userRepo.findAll();
    }


    @Override
    public User findByUserId(Long userId)
    {
        return userRepo.findById(userId).orElse(null);
    }

    @Override
    public User createUser(User user)
    {
        return userRepo.save(user);
    }

    @Override
    public User editUserCredentials(Long userId, User user)
    {
        return userRepo.save(user);
    }

    @Override
    public void deleteByUserId(Long userId){
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
