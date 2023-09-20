package ru.usikov.crudtesttask.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.usikov.crudtesttask.entities.User;
import ru.usikov.crudtesttask.errors.NotFoundException;
import ru.usikov.crudtesttask.repositories.UserRepository;

import java.util.UUID;

import static ru.usikov.crudtesttask.api.ApiMessageConstants.USER_NOT_FOUND;

@Service
@RequiredArgsConstructor
public class UserServiceHelper {

    private final UserRepository userRepository;

    public User getById(final UUID id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(USER_NOT_FOUND));
    }

    public String encodePassword(final String password) {
        return password;
    }

}
