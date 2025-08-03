package com.james.store.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@Builder
//@NoArgsConstructor
@AllArgsConstructor
@Table(name = "profiles")
@ToString
public class Profile {
    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "bio")
    private String bio;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "date_of_birth")
    private LocalDate dateOfBirth;

    @Column(name = "loyalty_points")
    private Integer loyaltyPoints;

    @OneToOne
    @JoinColumn(name = "id")
    @MapsId // this allows to set the column as PK and FK
    @ToString.Exclude
    private User user;
}
