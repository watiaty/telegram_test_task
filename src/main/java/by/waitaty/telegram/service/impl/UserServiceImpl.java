package by.waitaty.telegram.service.impl;

import by.waitaty.telegram.dto.response.TelegramUserResponseDto;
import by.waitaty.telegram.mapper.TelegramUserMapper;
import by.waitaty.telegram.model.TelegramUser;
import by.waitaty.telegram.repository.UserRepository;
import by.waitaty.telegram.service.IUserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
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

    @Override
    public TelegramUserResponseDto updateOrSave(String initData) {
        TelegramUser requestUser = parseInitData(initData);

        log.info("Авторизован пользователь с ником {}", requestUser.getUsername());

        TelegramUser user = userRepository.findById(requestUser.getId())
            .orElse(requestUser);

        user.setLoginCount(user.getLoginCount() + 1);

        userRepository.save(user);

        return telegramUserMapper.toResponseDto(user);
    }

    private TelegramUser parseInitData(String initData) {
        Map<String, String> map = new HashMap<>();

        String decoded = URLDecoder.decode(initData, StandardCharsets.UTF_8);
        String[] pairs = decoded.split("&");

        for (String pair : pairs) {
            String[] keyValue = pair.split("=", 2);
            if (keyValue.length == 2) {
                map.put(keyValue[0], keyValue[1]);
            }
        }

        String userJson = map.get("user");

        try {
            return objectMapper.readValue(userJson, TelegramUser.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

}
