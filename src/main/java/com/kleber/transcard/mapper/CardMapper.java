package com.kleber.transcard.mapper;

import com.kleber.transcard.dto.response.CardDTO;
import com.kleber.transcard.dto.request.CreateCardDTO;
import com.kleber.transcard.dto.response.UserSummaryDTO;
import com.kleber.transcard.entity.Card;
import com.kleber.transcard.entity.User;
import com.kleber.transcard.repository.projection.CardWithUserProjection;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.Named;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface CardMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "status", ignore = true)
    @Mapping(target = "user", ignore = true)
    Card toEntity(CreateCardDTO dto);

    @Mapping(target = "user", source = "user", qualifiedByName = "toUserSummary")
    CardDTO toDTO(Card card);

    @Named("toUserSummary")
    default UserSummaryDTO toUserSummary(User user) {
        if (user == null) return null;

        UserSummaryDTO dto = new UserSummaryDTO();
        dto.setId(user.getId());
        dto.setName(user.getName());
        dto.setEmail(user.getEmail());
        return dto;
    }

    UserSummaryDTO toSummary(User user);


    @Mapping(source = "id", target = "id")
    @Mapping(source = "cardNumber", target = "cardNumber")
    @Mapping(source = "cardName", target = "cardName")
    @Mapping(source = "status", target = "status")
    @Mapping(source = "typeCard", target = "cardType")
    @Mapping(source = "userId", target = "user.id")
    @Mapping(source = "userName", target = "user.name")
    @Mapping(source = "userEmail", target = "user.email")
    CardDTO toDto(CardWithUserProjection projection);
}
