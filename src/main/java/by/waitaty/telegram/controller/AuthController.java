package by.waitaty.telegram.controller;

import by.waitaty.telegram.model.TelegramUser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class AuthController {

    @GetMapping("/api/auth")
    public TelegramUser auth(@RequestParam String initData) throws JsonProcessingException {
        initData = "user=%7B%22id%22%3A338390788%2C%22first_name%22%3A%22without%20me.%22%2C%22last_name%22%3A%22%22%2C%22username%22%3A%22br0onei%22%2C%22language_code%22%3A%22en%22%2C%22allows_write_to_pm%22%3Atrue%2C%22photo_url%22%3A%22https%3A%5C%2F%5C%2Ft.me%5C%2Fi%5C%2Fuserpic%5C%2F320%5C%2FvvUYi09vkVl49wFAI0tXUP0c8MCOTubatRKhBe1Ar8g.svg%22%7D&chat_instance=4856202069888645&chat_type=sender&auth_date=1748211307&signature=mUhpWKbBffXveyoFLrAZhNcNav_HWeVezAgK6klfi4OGFISlJZQZh6nT_eMZGuJgYMOaeE9opFQLagZqcIhwAQ&hash=99440a47179c3bc9571cc3b018dd65c57ea5f3bf093eb0097ff7e8b66365613e";
        log.info("success");
        Map<String, String> parsed = parseInitData(initData);

        String userJson = parsed.get("user");

        ObjectMapper mapper = new ObjectMapper();
        TelegramUser user = mapper.readValue(userJson, TelegramUser.class);

        log.info("Авторизован пользователь с ником {}", user.getUsername());
        return user;
    }

    public static Map<String, String> parseInitData(String initData) {
        Map<String, String> map = new HashMap<>();

        String decoded = URLDecoder.decode(initData, StandardCharsets.UTF_8);
        String[] pairs = decoded.split("&");

        for (String pair : pairs) {
            String[] keyValue = pair.split("=", 2);
            if (keyValue.length == 2) {
                map.put(keyValue[0], keyValue[1]);
            }
        }

        return map;
    }

}
