package com.dev.api.petfeliz.service;

import com.dev.api.petfeliz.entity.AddressEntity;
import com.dev.api.petfeliz.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AddressService extends AbstractService<AddressEntity, Long, AddressRepository> {

    public AddressService(AddressRepository repository) {
        super(repository);
    }

}
