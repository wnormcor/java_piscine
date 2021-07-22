package edu.school21.chat.app;

import edu.school21.chat.models.Chatroom;
import edu.school21.chat.models.Message;
import edu.school21.chat.models.User;
import edu.school21.chat.repositories.DataSource;
import edu.school21.chat.repositories.MessagesRepository;
import edu.school21.chat.repositories.MessagesRepositoryJdbcImpl;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Optional;
import java.util.Scanner;

public class Program {

    public static void main(String[] args) throws SQLException, FileNotFoundException {

        try {
            DataSource dataSource = new DataSource();
            MessagesRepository mess = new MessagesRepositoryJdbcImpl(dataSource);
            String createSql = null;

            FileInputStream fileInput = new FileInputStream("./src/main/resources/schema.sql");
            FileInputStream fileInputData = new FileInputStream("./src/main/resources/data.sql");
            Scanner sc = new Scanner(fileInput).useDelimiter(";");
            Scanner scData = new Scanner(fileInputData).useDelimiter(";");

            Connection connection = dataSource.getConnection();
            Statement stmt = connection.createStatement();

            while (sc.hasNext()) {
                createSql = sc.next().trim();
                createSql += ";";
                stmt.executeUpdate(createSql);
            }

            while (scData.hasNext()) {
                createSql = scData.next().trim();
                createSql += ";";
                stmt.executeUpdate(createSql);
            }
            stmt.close();
            connection.close();

            User creator = new User(1L, "user", "user", new ArrayList(), new ArrayList());
            User author = creator;
            Chatroom room = new Chatroom(1L, "room", creator, new ArrayList());
            Message message = new Message(null, author, room, "Hello!", LocalDateTime.now());
            MessagesRepository messagesRepository = new MessagesRepositoryJdbcImpl(dataSource);
            messagesRepository.save(message);
            System.out.println("Set id:");
            System.out.println(message.getMessageId()); // ex. id == 11
            System.out.println("Enter id for message:\n-> ");

            Scanner scanner = new Scanner(System.in);

            Long id = scanner.nextLong();

            Optional<Message> m = messagesRepository.findById(id);
            try {
                System.out.println(m.get().toString());
            } catch (Exception e) {
                System.err.println("null optional returned");
            }
        }
        catch (Exception e) {

            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(-1);
        }
    }
}