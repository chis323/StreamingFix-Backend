package org.example.streamingfix.mapper;

import org.example.streamingfix.dto.UserDto;
import org.example.streamingfix.entity.User;

public final class UserMapper {

    private UserMapper() {
        // utility class
    }

    public static UserDto toDto(User user) {
        if (user == null) {
            return null;
        }

        return new UserDto(
                user.getUserId(),
                user.getEmail(),
                user.getUsername(),
                user.getPassword(),
                user.getSubscriptionType()
        );
    }

    public static User toEntity(UserDto dto) {
        if (dto == null) {
            return null;
        }

        User user = new User();
        user.setUserId(dto.userId());
        user.setEmail(dto.email());
        user.setUsername(dto.username());
        user.setPassword(dto.password());
        user.setSubscriptionType(dto.subscriptionType());
        // library is not in DTO → leave null
        return user;
    }

    /**
     * Update existing entity (PUT)
     */
    public static void updateEntityFromDto(UserDto dto, User user) {
        if (dto == null || user == null) {
            return;
        }

        user.setEmail(dto.email());
        user.setUsername(dto.username());
        user.setPassword(dto.password());
        user.setSubscriptionType(dto.subscriptionType());
    }
}
