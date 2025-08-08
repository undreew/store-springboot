package com.james.store.mappers;

import com.james.store.dtos.UserDto;
import com.james.store.entities.User;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import com.james.store.dtos.RegisterUserRequest;
import com.james.store.dtos.UpdateUserRequest;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserDto toDto(User user);
    User toEntity(RegisterUserRequest request);
    void update(UpdateUserRequest request, @MappingTarget User user);
}
