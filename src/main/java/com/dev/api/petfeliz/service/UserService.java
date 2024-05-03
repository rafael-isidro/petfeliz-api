package com.dev.api.petfeliz.service;

import com.dev.api.petfeliz.entity.UserEntity;
import com.dev.api.petfeliz.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final AddressService addressService;

    @Autowired
    public UserService(UserRepository userRepository, AddressService addressService) {
        this.userRepository = userRepository;
        this.addressService = addressService;
    }

    public List<UserEntity> findAll() {
        return userRepository.findAll();
    }

    public Optional<UserEntity> findById(Long id) {
        return userRepository.findById(id);
    }

    public UserEntity createUser(UserEntity user) {
        if (user.getAddress() != null) {
            user.setAddress(addressService.save(user.getAddress()));
        }
        return userRepository.save(user);
    }

    public UserEntity update(Long id, UserEntity newUser) {
        return userRepository.findById(id).map(user -> {
            user.setEmail(newUser.getEmail());
            user.setPassword(newUser.getPassword());
            user.setName(newUser.getName());
            user.setGender(newUser.getGender());
            user.setPhone(newUser.getPhone());

            if (newUser.getAddress() != null) {
                if (newUser.getAddress().getId() == null) {
                    user.setAddress(addressService.save(newUser.getAddress()));
                } else {
                    user.setAddress(newUser.getAddress());
                }
            }

            user.setPets(newUser.getPets());
            return userRepository.save(user);
        }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User with ID " + id + " not found"));
    }

    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }
}
