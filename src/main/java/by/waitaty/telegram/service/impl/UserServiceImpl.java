package by.waitaty.telegram.service.impl;

import by.waitaty.telegram.config.properties.TelegramProperties;
import by.waitaty.telegram.dto.response.TelegramUserResponseDto;
import by.waitaty.telegram.exception.ParseException;
import by.waitaty.telegram.mapper.TelegramUserMapper;
import by.waitaty.telegram.model.TelegramUser;
import by.waitaty.telegram.repository.UserRepository;
import by.waitaty.telegram.service.IUserService;
import by.waitaty.telegram.util.TelegramAuth;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements IUserService {
    private final ObjectMapper objectMapper;
    private final UserRepository userRepository;
    private final TelegramUserMapper telegramUserMapper;
    private final TelegramProperties telegramProperties;

    @Override
    public TelegramUserResponseDto updateOrSave(String initData) {
        String userJson = TelegramAuth.getValidatedData(initData, telegramProperties.getToken(),
            telegramProperties.getTokenFreshTime());

        TelegramUser user = parseUserJson(userJson);

        log.info("Авторизован пользователь с ником {}", user.getUsername());

        user = userRepository.save(user);

        return telegramUserMapper.toResponseDto(user);
    }

    private TelegramUser parseUserJson(String userJson) {
        try {
            objectMapper.setPropertyNamingStrategy(PropertyNamingStrategies.SNAKE_CASE);
            return objectMapper.readValue(userJson, TelegramUser.class);
        } catch (JsonProcessingException e) {
            throw new ParseException("Ошибка при обработке данных пользователя");
        }
    }

}
