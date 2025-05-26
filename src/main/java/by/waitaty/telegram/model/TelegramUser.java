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

    private String firstName;

    private String lastName;

    private String username;

    private String languageCode;

    private boolean allowsWriteToPm;

    private String photoUrl;

}
