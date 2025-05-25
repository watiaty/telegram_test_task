package by.waitaty.telegram.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class AuthController {


    @GetMapping("/api/auth")
    public void auth(@RequestParam String initData) {
        log.info("success");
        System.out.println(initData);
//        if (!telegramAuthService.isValid(initData)) {
//            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
//        }
//        TelegramUser user = telegramAuthService.saveOrUpdate(initData);
//        return ResponseEntity.ok(mapper.toDto(user));
    }

}
