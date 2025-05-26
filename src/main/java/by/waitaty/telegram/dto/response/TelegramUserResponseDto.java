package by.waitaty.telegram.dto.response;

public record TelegramUserResponseDto(
    Long id,
    String username,
    String firstName,
    String lastName,
    String photoUrl
) { }
