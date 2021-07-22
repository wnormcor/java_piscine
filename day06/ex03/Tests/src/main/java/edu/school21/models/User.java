package edu.school21.models;

import java.util.Objects;

public class User {
    private final long identifier;
    private final String login;
    private final String password;
    private boolean isAuthenticated;

    public User(long identifier, String login, String password, boolean authenticationStatus) {
        this.identifier = identifier;
        this.login = login;
        this.password = password;
        this.isAuthenticated = authenticationStatus;
    }

    public void setAuthenticated(boolean authenticated) {
        this.isAuthenticated = authenticated;
    }

    public long getIdentifier() {
        return identifier;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public boolean isAuthenticated() {
        return isAuthenticated;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return identifier == user.identifier && isAuthenticated == user.isAuthenticated && Objects.equals(login, user.login) && Objects.equals(password, user.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(identifier, login, password, isAuthenticated);
    }
}
