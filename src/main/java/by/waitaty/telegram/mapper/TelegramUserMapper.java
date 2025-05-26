package by.waitaty.telegram.mapper;

import by.waitaty.telegram.dto.response.TelegramUserResponseDto;
import by.waitaty.telegram.model.TelegramUser;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface TelegramUserMapper {

    @Mapping(target = "firstName", source = "first_name")
    @Mapping(target = "lastName", source = "last_name")
    @Mapping(target = "photoUrl", source = "photo_url")
    TelegramUserResponseDto toResponseDto(TelegramUser user);

}
