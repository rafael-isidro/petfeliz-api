package com.dev.api.petfeliz.repository;

import com.dev.api.petfeliz.entity.PetshopEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PetshopRepository extends JpaRepository<PetshopEntity, Long> {
}
