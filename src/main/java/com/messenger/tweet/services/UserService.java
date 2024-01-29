package com.messenger.tweet.services;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.messenger.tweet.dtos.UserDTO;
import com.messenger.tweet.models.UserModel;
import com.messenger.tweet.repositories.UserRepository;

@Service
public class UserService {

    final UserRepository userRepository;

    UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Optional<UserModel> save(UserDTO dto) {

        if (userRepository.existsByUsername(dto.getUsername())) {
            return Optional.empty();
        }
        UserModel user = new UserModel(dto);
        return Optional.of(userRepository.save(user));
    }
}
