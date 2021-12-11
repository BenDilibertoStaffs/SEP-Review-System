package com.sep.restarauntreview.service;

import com.sep.restarauntreview.domain.User;
import com.sep.restarauntreview.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AuthService {

    private final UserRepository userRepository;

    public boolean areCredentialsValid(User user) {
        return userRepository.areCredentialsValidForUser(user);
    }
}
