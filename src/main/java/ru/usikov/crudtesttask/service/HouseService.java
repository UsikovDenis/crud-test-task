package ru.usikov.crudtesttask.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.usikov.crudtesttask.api.dto.HouseDto;
import ru.usikov.crudtesttask.entities.House;
import ru.usikov.crudtesttask.entities.User;
import ru.usikov.crudtesttask.errors.NotFoundException;
import ru.usikov.crudtesttask.repositories.HouseRepository;
import ru.usikov.crudtesttask.repositories.UserRepository;

import java.util.List;
import java.util.UUID;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;
import static ru.usikov.crudtesttask.api.ApiMessageConstants.HOUSE_NOT_FOUND;
import static ru.usikov.crudtesttask.api.ApiMessageConstants.OWNER_USER_NOT_FOUND;

@Service
@RequiredArgsConstructor
public class HouseService {

    private final HouseRepository houseRepository;
    private final UserServiceHelper userServiceHelper;


    public List<HouseDto> getAllHouses() {
        return houseRepository.findAll().stream()
                .map(house -> HouseDto.builder()
                        .id(house.getId())
                        .address(house.getAddress())
                        .ownerUserId(house.getOwnerUser().getId())
                        .build())
                .toList();

    }

    public HouseDto getHouseById(final UUID id) {
        final House house = getById(id);
        return HouseDto.builder()
                .id(house.getId())
                .address(house.getAddress())
                .ownerUserId(house.getOwnerUser().getId())
                .build();
    }

    public UUID saveOrUpdate(final HouseDto houseDto) {
        if (isNull(houseDto.getOwnerUserId())) {
            throw new RuntimeException(OWNER_USER_NOT_FOUND);
        }
        return houseRepository.save(House.builder()
                        .id(houseDto.getId())
                        .address(houseDto.getAddress())
                        .ownerUser(userServiceHelper.getById(houseDto.getOwnerUserId()))
                        .build())
                .getId();
    }

    public void deleteHouseById(final UUID id) {
        houseRepository.delete(getById(id));
    }

    public House getById(final UUID id) {
        return houseRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(HOUSE_NOT_FOUND));
    }
}
