package ru.usikov.crudtesttask.api.dto;

import lombok.*;


import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class HouseDto {

    private UUID id;
    private String address;
    private UUID ownerUserId;
}
