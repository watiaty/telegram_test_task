package by.waitaty.telegram.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class TelegramUser {
    private long id;
    private String first_name;
    private String last_name;
    private String username;
    private String language_code;
    private boolean allows_write_to_pm;
    private String photo_url;
}
