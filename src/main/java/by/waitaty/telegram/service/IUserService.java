package by.waitaty.telegram.service;

import by.waitaty.telegram.dto.response.TelegramUserResponseDto;

public interface IUserService {

    TelegramUserResponseDto updateOrSave(String initData);

}
