package ru.usikov.crudtesttask.api;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ApiMessageConstants {

    public static final String HOUSE_NOT_FOUND = "Такой дом не найден";
    public static final String USER_NOT_FOUND = "Такой пльзователь не найден";
    public static final String OWNER_USER_NOT_FOUND = "Владелец дома не указан";

}
