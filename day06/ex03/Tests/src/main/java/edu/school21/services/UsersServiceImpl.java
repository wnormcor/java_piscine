package edu.school21.services;

import edu.school21.exceptions.AlreadyAuthenticatedException;
import edu.school21.exceptions.EntityNotFoundException;
import edu.school21.models.User;
import edu.school21.repositories.UsersRepository;

import java.util.Objects;

public class UsersServiceImpl {
    private final UsersRepository usersRepository;

    public UsersServiceImpl(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    public boolean authenticate(String login, String password) {
        User user = findByLogin(login);
        if (user.isAuthenticated()) {
            throw new AlreadyAuthenticatedException();
        }
        boolean isAuthenticated = user.getPassword().equals(password);
        if (isAuthenticated) {
            user.setAuthenticated(true);
            usersRepository.update(user);
        }

        return isAuthenticated;
    }

    private User findByLogin(String login) {
        User user = usersRepository.findByLogin(login);
        if (Objects.isNull(user)) {
            throw new EntityNotFoundException();
        }
        return user;
    }
}
