package ru.usikov.crudtesttask.api.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
public class AddResidentsRequestDto {

    @JsonIgnore
    private UUID ownerUserId;

    private List<UUID> residentIds;
}
