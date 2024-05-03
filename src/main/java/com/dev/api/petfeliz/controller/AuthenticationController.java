package com.dev.api.petfeliz.controller;

import com.dev.api.petfeliz.entity.AddressEntity;
import com.dev.api.petfeliz.entity.user.AuthenticationDTO;
import com.dev.api.petfeliz.entity.user.LoginResponseDTO;
import com.dev.api.petfeliz.entity.user.RegistrationDTO;
import com.dev.api.petfeliz.entity.user.UserEntity;
import com.dev.api.petfeliz.infra.security.TokenService;
import com.dev.api.petfeliz.repository.AddressRepository;
import com.dev.api.petfeliz.repository.UserRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository repository;

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid AuthenticationDTO data) {
        var emailPassword = new UsernamePasswordAuthenticationToken(data.email(), data.password());
        var authentication = this.authenticationManager.authenticate(emailPassword);

        var token = tokenService.generateToken( (UserEntity) authentication.getPrincipal());
        return ResponseEntity.ok(new LoginResponseDTO(token));
    }

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody @Valid RegistrationDTO data) {
        if (this.repository.findByEmail(data.email()) != null) return ResponseEntity.badRequest().build();

        String encryptedPassword = new BCryptPasswordEncoder().encode(data.password());

        AddressEntity userAddress = new AddressEntity(data.address().getStreet(), data.address().getCity(), data.address().getBuildingNumber(), data.address().getDistrict(), data.address().getState(), data.address().getCountry(), data.address().getPostalCode(), data.address().getLatitude(), data.address().getLongitude());
        addressRepository.save(userAddress);
        UserEntity newUser = new UserEntity(data.email(), encryptedPassword, data.name(), userAddress, data.phone(), data.gender(), data.role());

        this.repository.save(newUser);
        return ResponseEntity.ok().build();
    }
}
