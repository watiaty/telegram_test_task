package by.waitaty.telegram.controller;

import by.waitaty.telegram.dto.response.TelegramUserResponseDto;
import by.waitaty.telegram.model.TelegramUser;
import by.waitaty.telegram.service.IUserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class AuthController {
    private final IUserService userService;

    @GetMapping("/auth")
    public TelegramUserResponseDto auth(@RequestAttribute("telegramUser") TelegramUser user) {

        return userService.updateOrSave(user);
    }

}
