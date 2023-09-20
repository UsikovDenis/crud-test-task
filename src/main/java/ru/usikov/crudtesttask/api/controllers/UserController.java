package ru.usikov.crudtesttask.api.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.usikov.crudtesttask.api.dto.AddResidentsRequestDto;
import ru.usikov.crudtesttask.api.dto.UserDto;
import ru.usikov.crudtesttask.entities.User;
import ru.usikov.crudtesttask.service.UserService;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @GetMapping
    public List<UserDto> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    public UserDto getUserById(@PathVariable final UUID id) {
        return userService.getUserById(id);
    }

    @PostMapping
    public UUID create(@RequestBody final UserDto userDto) {
        userDto.setId(null);
        return userService.saveOrUpdate(userDto);
    }

    @PutMapping("/{id}")
    public UUID update(@PathVariable final UUID id, @RequestBody final UserDto userDto) {
        userDto.setId(id);
        return userService.saveOrUpdate(userDto);
    }

    @DeleteMapping("{id}")
    public void deleteUserById(@PathVariable final UUID id) {
        userService.deleteUserById(id);
    }

    @PostMapping("{id}/add-residents")
    public void addResidentsToHouse(
            @PathVariable("id") final UUID ownerUserId,
            @RequestBody final AddResidentsRequestDto addResidentsRequestDto
    ) {
        addResidentsRequestDto.setOwnerUserId(ownerUserId);
        userService.addResidentsToHouse(addResidentsRequestDto);
    }

}
