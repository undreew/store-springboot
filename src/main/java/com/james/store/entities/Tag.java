package com.james.store.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tags")
@ToString
public class Tag {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, name = "name")
    private String name;

    @ManyToMany(mappedBy = "tags")
    @ToString.Exclude
    private Set<User> users = new HashSet<>();


    public Tag(String name) {
        this.name = name;
    }
}
