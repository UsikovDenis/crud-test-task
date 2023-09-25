package ru.usikov.crudtesttask.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.usikov.crudtesttask.entities.House;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface HouseRepository extends JpaRepository<House, UUID> {

    Optional<House> findByOwnerUserId(UUID ownerUserId);

}
