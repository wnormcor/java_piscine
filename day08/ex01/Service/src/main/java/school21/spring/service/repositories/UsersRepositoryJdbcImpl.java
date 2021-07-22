package school21.spring.service.repositories;

import school21.spring.service.models.User;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UsersRepositoryJdbcImpl implements UsersRepository {

    DataSource          dataSource;
    Connection          connection;
    PreparedStatement   preparedStatement;
    ResultSet           resultSet;

    final String selectUserId =  "SELECT * FROM public.emails WHERE userid = ";
    final String UpdateUser =  "UPDATE public.emails SET ";
    final String DeleteUser =  "DELETE FROM public.emails WHERE userid = ";
    final String selectUserEmail =  "SELECT * FROM public.emails WHERE email = ";


    public UsersRepositoryJdbcImpl(DataSource dataSource) {
        this.dataSource = dataSource;
        try {
            connection = dataSource.getConnection();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public User findById(Long id) {
        try {
            preparedStatement = connection.prepareStatement(selectUserId + id);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                User user = new User(resultSet.getLong(1), resultSet.getString(2));
                return user;
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    @Override
    public List<User> findAll() {
        List<User> list = new ArrayList<>();
        Long identifier = 0L;
        User userToAdd = null;
        while ((userToAdd = findById(++identifier)) != null) {
            list.add(userToAdd);
        }
        if (!list.isEmpty()) {
            return list;
        }
        return null;
    }

    @Override
    public void save(User entity) {
        try {
            preparedStatement = connection.prepareStatement(String.format("INSERT INTO public.emails VALUES ('%d', %s);", entity.getIdentifier(), entity.getEmail()));
            preparedStatement.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void update(User entity) {
        try {
            connection = dataSource.getConnection();
            preparedStatement = connection.prepareStatement(UpdateUser + "email = " + '\'' + entity.getEmail() + '\'' + " WHERE userid = " + entity.getIdentifier());
            preparedStatement.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void delete(Long id) {
        try {
            connection = dataSource.getConnection();
            preparedStatement = connection.prepareStatement(DeleteUser + id);
            preparedStatement.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public Optional<User> findByEmail(String email) {
        try {
            connection = dataSource.getConnection();
            preparedStatement = connection.prepareStatement(selectUserEmail + email);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                User user = new User(resultSet.getLong(1), resultSet.getString(2));
                return Optional.of(user);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return Optional.empty();
    }
}
