package edu.school21.chat.app;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import edu.school21.chat.models.Chatroom;
import edu.school21.chat.models.Message;
import edu.school21.chat.models.User;
import edu.school21.chat.repositories.MessagesRepository;
import edu.school21.chat.repositories.UsersRepository;
import edu.school21.chat.repositories.impl.MessagesRepositoryJdbcImpl;
import edu.school21.chat.repositories.impl.UsersRepositoryJdbcImpl;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

public class Program {
    public static void main(String[] args) throws SQLException {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl("jdbc:postgresql://localhost:5432/postgres");
        config.setSchema("new_chat");
        config.setUsername("postgres");
        config.setPassword("postgres");
        config.setDriverClassName("org.postgresql.Driver");
        DataSource dataSource = new HikariDataSource(config);

        UsersRepository usersRepository = new UsersRepositoryJdbcImpl(dataSource);

        List<User> users = usersRepository.findAll(0, 2);
        users.forEach(System.out::println);
    }
}
