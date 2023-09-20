package ru.usikov.crudtesttask.entities;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.UuidGenerator;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "house")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class House {

    @Id
    @GeneratedValue
    @UuidGenerator
    private UUID id;

    private String address;

    @OneToOne
    @JoinColumn(name = "owner_user_id")
    private User ownerUser;

    @OneToMany(mappedBy = "house")
    private List<User> residents;




}
