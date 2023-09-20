package ru.usikov.crudtesttask.api.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
public class AddResidentsRequestDto {
    private UUID ownerUserId;
    private List<UUID> residentIds;
}
