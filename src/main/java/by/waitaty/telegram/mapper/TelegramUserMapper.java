package by.waitaty.telegram.mapper;

import by.waitaty.telegram.dto.response.TelegramUserResponseDto;
import by.waitaty.telegram.model.TelegramUser;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TelegramUserMapper {

    TelegramUserResponseDto toResponseDto(TelegramUser user);

}
