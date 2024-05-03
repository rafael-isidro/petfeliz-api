package com.dev.api.petfeliz.service;

import com.dev.api.petfeliz.entity.user.UserEntity;
import com.dev.api.petfeliz.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static org.springframework.beans.BeanUtils.copyProperties;

@Service
public class UserService extends AbstractService<UserEntity, Long, UserRepository>{

    @Autowired
    private AddressService addressService;

    @Autowired
    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        super(userRepository);
    }

    @Override
    public UserEntity update(Long id, UserEntity user) {
        if (!this.existsById(id)) {
            throw new EntityNotFoundException();
        }

        UserEntity existingEntity = this.findById(id)
                .orElseThrow(EntityNotFoundException::new);

        copyProperties(user, existingEntity, "id");

        if (user.getAddress() != null) {
            existingEntity.setAddress(addressService.save(user.getAddress()));
        }

        return this.userRepository.save(existingEntity);
    }
}
