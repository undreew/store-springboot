package com.james.store.controllers;

import java.util.Set;

import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.james.store.dtos.ChangePasswordRequest;
import com.james.store.dtos.RegisterUserRequest;
import com.james.store.dtos.UpdateUserRequest;
import com.james.store.dtos.UserDto;
import com.james.store.mappers.UserMapper;
import com.james.store.repositories.UserRepository;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;



@RestController
@AllArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @GetMapping
    public Iterable<UserDto> getAllUsers(@RequestParam(required = false, defaultValue = "", name = "sort") String sortBy) {
        if (!Set.of("name", "email").contains(sortBy)) sortBy = "name";
        return userRepository.findAll(Sort.by(sortBy)).stream().map(userMapper::toDto).toList();
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUser(@PathVariable Long id) {
        var user =  userRepository.findById(id).orElse(null);
        
        if (user == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(userMapper.toDto(user));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserDto createUser(@Valid @RequestBody RegisterUserRequest payload) {
        var user = userMapper.toEntity(payload);
        userRepository.save(user);

        var userDto = userMapper.toDto(user);
        return userDto;
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<UserDto> putMethodName(@PathVariable(name = "id") Long id, @RequestBody UpdateUserRequest payload) {
        var user = userRepository.findById(id).orElse(null);
        if (user == null) {
            return ResponseEntity.notFound().build();
        }
        userMapper.update(payload, user);
        userRepository.save(user);

        return ResponseEntity.ok(userMapper.toDto(user));
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable(name = "id") Long id) {
        var user = userRepository.findById(id).orElse(null);
        if (user == null) {
            return ResponseEntity.notFound().build();
        }
        userRepository.delete(user);
        return ResponseEntity.status(200).build();
    }

    @PostMapping("/{id}/change-password")
    public ResponseEntity<Void> postMethodName(@PathVariable(name = "id") Long id, @RequestBody ChangePasswordRequest payload) {
        var user = userRepository.findById(id).orElse(null);
        if (user == null) {
            return ResponseEntity.notFound().build();
        }

        if (!user.getPassword().equals(payload.getOldPassword())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        user.setPassword(payload.getNewPassword());
        userRepository.save(user);
        return ResponseEntity.noContent().build();
    }

}
