package ru.usikov.crudtesttask.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.usikov.crudtesttask.api.dto.AddResidentsRequestDto;
import ru.usikov.crudtesttask.api.dto.UserDto;
import ru.usikov.crudtesttask.entities.User;
import ru.usikov.crudtesttask.repositories.UserRepository;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserServiceHelper userServiceHelper;
    private final HouseService houseService;
    private final UserRepository userRepository;

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

    public UserDto getUserById(final UUID id) {
        final User user = userServiceHelper.getById(id);
        return UserDto.builder()
                .id(user.getId())
                .name(user.getName())
                .age(user.getAge())
                .houseId(user.getHouse().getId())
                .build();
    }

    public UUID saveOrUpdate(final UserDto userDto) {
        return userRepository.save(User.builder()
                        .id(userDto.getId())
                        .name(userDto.getName())
                        .age(userDto.getAge())
                        .passwordEncode(userServiceHelper.encodePassword(userDto.getPassword()))
                        .build())
                .getId();
    }

    public void deleteUserById(final UUID id) {
        userRepository.delete(userServiceHelper.getById(id));
    }


    public void addResidentsToHouse(final AddResidentsRequestDto addResidentsRequestDto) {
        final User ownerUser = userServiceHelper.getById(addResidentsRequestDto.getOwnerUserId());
        ownerUser.getHouse().getResidents()
                .addAll(userRepository.findAllByIdIn(addResidentsRequestDto.getResidentIds()));
        userRepository.save(ownerUser);
    }
}
