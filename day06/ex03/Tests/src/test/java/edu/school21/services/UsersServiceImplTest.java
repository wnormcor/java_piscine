package edu.school21.services;

import edu.school21.exceptions.EntityNotFoundException;
import edu.school21.models.User;
import edu.school21.repositories.UsersRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class UsersServiceImplTest {
    private static final String CORRECT_LOGIN = "CORRECT_LOGIN";
    private static final String INCORRECT_LOGIN = "INCORRECT_LOGIN";
    private static final String CORRECT_PASS = "CORRECT_PASS";
    private static final String INCORRECT_PASS = "INCORRECT_PASS";
    private User user;

    private final UsersRepository usersRepository = mock(UsersRepository.class);
    private final UsersServiceImpl usersService = new UsersServiceImpl(usersRepository);

    @BeforeEach
    public void init() {
        user = new User(1,
                CORRECT_LOGIN,
                CORRECT_PASS,
                false);
        when(usersRepository.findByLogin(CORRECT_LOGIN))
                .thenReturn(user);
        doNothing().when(usersRepository).update(user);
    }

    @Test
    public void correctLoginAndPasswordTest() {
        assertTrue(usersService.authenticate(CORRECT_LOGIN, CORRECT_PASS));
        verify(usersRepository).findByLogin(CORRECT_LOGIN);
        verify(usersRepository).update(user);
    }

    @Test
    public void incorrectLoginAndCorrectPassword() {
        assertThrows(EntityNotFoundException.class,
                () -> usersService.authenticate(INCORRECT_LOGIN, CORRECT_PASS));
        verify(usersRepository).findByLogin(INCORRECT_LOGIN);
        verify(usersRepository, never()).update(any());
    }

    @Test
    public void correctLoginAndIncorrectPassword() {
        assertFalse(usersService.authenticate(CORRECT_LOGIN, INCORRECT_PASS));
        verify(usersRepository).findByLogin(CORRECT_LOGIN);
        verify(usersRepository, never()).update(any());
    }
}
