package com.ltweb.webjob.mappers;

import com.ltweb.webjob.dtos.UserDto;
import com.ltweb.webjob.entities.User;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class UserMapper  {

    public static UserDto toDto(User entity) {
        UserDto userDto = new UserDto();
        userDto.setId(entity.getId());
        userDto.setFirstName(entity.getFirstName());
        userDto.setLastName(entity.getLastName());
        userDto.setBirthDate(entity.getBirthDate());
        userDto.setGender(entity.getGender());
        userDto.setEmail(entity.getEmail());
        userDto.setCreatedTime(entity.getCreatedTime());
        userDto.setIsDeleted(entity.getIsDeleted());
        userDto.setRole(entity.getRole());
        userDto.setLocation(entity.getLocation());

        return userDto;
    }
    public static List<UserDto> toDtoList(List<User> entities){
        return entities.stream().map(UserMapper::toDto).collect(Collectors.toList());
    }

    public static List<UserDto> toDtoList(Iterable<User> entities) {
        List<UserDto> userDtos = new LinkedList<>();
        entities.forEach(e -> userDtos.add(toDto(e)));
        return userDtos;
    }
    public static User toEntity(UserDto userDto) {
        User entity = new User();
        entity.setId(userDto.getId());
        entity.setFirstName(userDto.getFirstName());
        entity.setLastName(userDto.getLastName());
        entity.setBirthDate(userDto.getBirthDate());
        entity.setGender(userDto.getGender());
        entity.setEmail(userDto.getEmail());
        entity.setCreatedTime(userDto.getCreatedTime());
        entity.setIsDeleted(userDto.getIsDeleted());
        entity.setRole(userDto.getRole());
        entity.setLocation(userDto.getLocation());

        return entity;
    }

}
