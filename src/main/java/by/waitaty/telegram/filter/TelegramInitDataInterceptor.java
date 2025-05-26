package by.waitaty.telegram.filter;

import by.waitaty.telegram.config.properties.TelegramProperties;
import by.waitaty.telegram.exception.InvalidInitDataException;
import by.waitaty.telegram.exception.ParseException;
import by.waitaty.telegram.model.TelegramUser;
import by.waitaty.telegram.util.TelegramAuth;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Slf4j
@Component
@RequiredArgsConstructor
public class TelegramInitDataInterceptor implements HandlerInterceptor {
    private final TelegramProperties telegramProperties;
    private final ObjectMapper objectMapper;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
        Object handler) throws Exception {
        String initData = request.getParameter("initData");

        if (initData == null || initData.isBlank()) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Missing initData");
            return false;
        }

        try {
            String userJson = TelegramAuth.getValidatedData(initData, telegramProperties.getToken(),
                telegramProperties.getTokenFreshTime());

            TelegramUser user = parseUserJson(userJson);

            log.info("Авторизован пользователь с ником {}", user.getUsername());

            request.setAttribute("telegramUser", user);

            return true;
        } catch (InvalidInitDataException e) {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, e.getMessage());
            return false;
        }
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

