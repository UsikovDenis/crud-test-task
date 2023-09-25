package ru.usikov.crudtesttask.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.usikov.crudtesttask.api.dto.AddResidentsRequestDto;
import ru.usikov.crudtesttask.api.dto.UserDto;
import ru.usikov.crudtesttask.entities.User;
import ru.usikov.crudtesttask.errors.NotFoundException;
import ru.usikov.crudtesttask.repositories.HouseRepository;
import ru.usikov.crudtesttask.repositories.UserRepository;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserServiceHelper userServiceHelper;
    private final UserRepository userRepository;
    private final HouseRepository houseRepository;

    @Transactional(readOnly = true)
    public List<UserDto> getAllUsers() {
        return userRepository.findAll().stream()
                .map(user -> UserDto.builder()
                        .id(user.getId())
                        .name(user.getName())
                        .age(user.getAge())
                        .houseId(user.getHouse().getId())
                        .build())
                .toList();
    }

    @Transactional(readOnly = true)
    public UserDto getUserById(final UUID id) {
        final User user = userServiceHelper.getById(id);
        return UserDto.builder()
                .id(user.getId())
                .name(user.getName())
                .age(user.getAge())
                .houseId(user.getHouse().getId())
                .build();
    }

    @Transactional
    public UUID saveOrUpdate(final UserDto userDto) {
        return userRepository.save(User.builder()
                        .id(userDto.getId())
                        .name(userDto.getName())
                        .age(userDto.getAge())
                        .passwordEncode(userServiceHelper.encodePassword(userDto.getPassword()))
                        .build())
                .getId();
    }

    @Transactional
    public void deleteUserById(final UUID id) {
        userRepository.delete(userServiceHelper.getById(id));
    }

    @Transactional
    public void addResidentsToHouse(final AddResidentsRequestDto addResidentsRequestDto) {
        final User ownerUser = userServiceHelper.getById(addResidentsRequestDto.getOwnerUserId());
        houseRepository.findByOwnerUserId(ownerUser.getId())
                .ifPresent(house -> {
                    house.getResidents()
                            .addAll(userRepository.findAllByIdIn(addResidentsRequestDto.getResidentIds()));
                    houseRepository.save(house);
                });
    }
}
