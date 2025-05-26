package by.waitaty.telegram.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "users")
public class TelegramUser {

    @Id
    private long id;

    private String first_name;

    private String last_name;

    private String username;

    private String language_code;

    private boolean allows_write_to_pm;

    private String photo_url;

    private Long loginCount = 0L;

}
