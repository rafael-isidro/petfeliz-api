package com.dev.api.petfeliz.repository;

import com.dev.api.petfeliz.entity.PetEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PetRepository extends JpaRepository<PetEntity, Long> {
    List<PetEntity> findAllByUserId(Long id);
}
