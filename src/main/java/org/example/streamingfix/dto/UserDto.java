package org.example.streamingfix.dto;

public record UserDto (
        Long userId,
        String email,
        String username,
        String password,
           //  Subscription can be of 3 types:
           //  * 1 - no subscription, just account created
           //  * 2 - 5$ subscription, 3 profiles, HD quality
           //  * 3 - 7$ subscription, 5 profiles, 4k quality
        Integer subscriptionType
){
}
