package com.kleber.transcard.mapper;

import com.kleber.transcard.dto.response.UserDTO;
import com.kleber.transcard.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface UserMapper {

    User toEntity(UserDTO dto);

    @Mapping(target = "cards", source = "cards")
    UserDTO toDto(User entity);


    @Mapping(target = "cards", source = "cards")
    @Mapping(target = "role", source = "role")  // <- adiciona o role
    UserDTO loginToDto(User entity);


}
