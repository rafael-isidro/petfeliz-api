package com.dev.api.petfeliz.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "petshops")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PetshopEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "phone")
    private String phone;

    @Column(name = "email")
    private String email;

    @Column(name = "opening_hours")
    private String openingHours;

    @ElementCollection
    @CollectionTable(name = "petshop_services", joinColumns = @JoinColumn(name = "petshop_id"))
    @Column(name = "service")
    private List<String> petshopService;

    @ManyToOne(optional = false)
    @JoinColumn(name = "address_id", nullable = false)
    private AddressEntity address;

    @Column(name = "average_rating")
    private Double averageRating;

}
