package by.waitaty.telegram.service;

import by.waitaty.telegram.dto.response.TelegramUserResponseDto;
import by.waitaty.telegram.model.TelegramUser;

public interface IUserService {

    TelegramUserResponseDto updateOrSave(TelegramUser user);

}
