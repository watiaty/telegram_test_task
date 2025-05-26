package by.waitaty.telegram.repository;

import by.waitaty.telegram.model.TelegramUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<TelegramUser, Long> {
}
