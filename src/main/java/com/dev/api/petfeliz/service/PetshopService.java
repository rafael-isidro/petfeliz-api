package com.dev.api.petfeliz.service;

import com.dev.api.petfeliz.entity.PetshopEntity;
import com.dev.api.petfeliz.entity.PetshopEntity;
import com.dev.api.petfeliz.repository.PetshopRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static org.springframework.beans.BeanUtils.copyProperties;

@Service
public class PetshopService extends AbstractService<PetshopEntity, Long, PetshopRepository> {

    @Autowired
    private AddressService addressService;

    @Autowired
    PetshopRepository petshopRepository;

    @Autowired
    public PetshopService(PetshopRepository petshopRepository) {
        super(petshopRepository);
    }

    public PetshopEntity createPetshop(PetshopEntity petshop) {
        if (petshop.getAddress() != null) {
            petshop.setAddress(addressService.save(petshop.getAddress()));
        }
        return petshopRepository.save(petshop);
    }

    @Override
    public PetshopEntity update(Long id, PetshopEntity petshop) {
        if (!this.existsById(id)) {
            throw new EntityNotFoundException();
        }

        PetshopEntity existingEntity = this.findById(id)
                .orElseThrow(EntityNotFoundException::new);

        copyProperties(petshop, existingEntity, "id");

        if (petshop.getAddress() != null) {
            existingEntity.setAddress(addressService.save(petshop.getAddress()));
        }

        return this.petshopRepository.save(existingEntity);
    }
}