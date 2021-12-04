package com.sep.restarauntreview.service;

import com.sep.restarauntreview.domain.User;
import com.sep.restarauntreview.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
@AllArgsConstructor
public record UserService(UserRepository userRepository) {

    public Collection<String> getAllUsernames() {
        return userRepository.getAllUsernames();
    }

    public void save(User user) {
        userRepository.save(user);
    }

    public void deleteByUsername(String username) {
        userRepository.deleteByUsername(username);
    }
}
