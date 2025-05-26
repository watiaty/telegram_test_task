package by.waitaty.telegram.service.impl;

import by.waitaty.telegram.dto.response.TelegramUserResponseDto;
import by.waitaty.telegram.mapper.TelegramUserMapper;
import by.waitaty.telegram.model.TelegramUser;
import by.waitaty.telegram.repository.UserRepository;
import by.waitaty.telegram.service.IUserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements IUserService {

    private final UserRepository userRepository;
    private final TelegramUserMapper telegramUserMapper;

    @Override
    public TelegramUserResponseDto updateOrSave(TelegramUser user) {
        log.info("Сохранение пользователя {}", user.getUsername());

        user = userRepository.save(user);

        return telegramUserMapper.toResponseDto(user);
    }

}
