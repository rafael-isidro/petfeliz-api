package com.dev.api.petfeliz.entity.user;

import com.dev.api.petfeliz.entity.AddressEntity;

public record RegistrationDTO(String email, String password, String name, String gender, String phone, AddressEntity address, UserRole role) {
}
