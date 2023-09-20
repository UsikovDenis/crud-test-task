package ru.usikov.crudtesttask.api.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.usikov.crudtesttask.api.dto.HouseDto;
import ru.usikov.crudtesttask.service.HouseService;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping("/house")
public class HouseController {

    private final HouseService houseService;
    
    @GetMapping
    public List<HouseDto> getAllHouses() {
        return houseService.getAllHouses();
    }

    @GetMapping("/{id}")
    public HouseDto getHouseById(@PathVariable final UUID id) {
        return houseService.getHouseById(id);
    }

    @PostMapping
    public UUID create(@RequestBody final HouseDto houseDto){
        houseDto.setId(null);
        return houseService.saveOrUpdate(houseDto);
    }

    @PutMapping("/{id}")
    public UUID update(@PathVariable final UUID id, @RequestBody final HouseDto houseDto){
        houseDto.setId(id);
        return houseService.saveOrUpdate(houseDto);
    }

    @DeleteMapping("{id}")
    public void deleteHouseById(@PathVariable final UUID id) {
        houseService.deleteHouseById(id);
    }


}
