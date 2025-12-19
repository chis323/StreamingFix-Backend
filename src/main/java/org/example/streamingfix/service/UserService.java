package org.example.streamingfix.service;

import org.example.streamingfix.entity.Library;
import org.example.streamingfix.entity.User;

public interface UserService {
    User findByUserId(Long userId);
    User createUser(User user);
    User editUserCredentials(Long userId, User user);
    void deleteByUserId(Long userId);
    Library addLibraryToUser(Long userId, Long libraryId);
}
