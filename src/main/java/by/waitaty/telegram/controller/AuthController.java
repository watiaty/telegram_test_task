package by.waitaty.telegram.controller;

import by.waitaty.telegram.dto.response.TelegramUserResponseDto;
import by.waitaty.telegram.service.IUserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class AuthController {
    private final IUserService userService;

    @GetMapping("/api/auth")
    public TelegramUserResponseDto auth(@RequestParam String initData) {

        return userService.updateOrSave(initData);
    }

}
