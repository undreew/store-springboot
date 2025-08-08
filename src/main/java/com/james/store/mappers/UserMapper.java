package com.james.store.mappers;

import com.james.store.dtos.UserDto;
import com.james.store.entities.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserDto toDto(User user);
}
