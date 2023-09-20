package ru.usikov.crudtesttask.api.dto;

import lombok.*;

import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

    private UUID id;
    private String name;
    private int age;
    private UUID houseId;
    private String password;

}
