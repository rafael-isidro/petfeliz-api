package com.dev.api.petfeliz.service;

import com.dev.api.petfeliz.entity.PetEntity;
import com.dev.api.petfeliz.repository.PetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PetService extends AbstractService<PetEntity, Long, PetRepository>{

    @Autowired
    private PetRepository petRepository;

    public PetService(PetRepository repository) {
        super(repository);
    }

    public List<PetEntity> findAllPetsByUserId(Long userId) {
        return petRepository.findAllByUserId(userId);
    }
}
