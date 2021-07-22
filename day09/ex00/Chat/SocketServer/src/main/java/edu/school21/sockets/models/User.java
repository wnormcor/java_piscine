package edu.school21.sockets.models;

import java.util.Objects;

public class User {
    private Long id;
    private String name;
    private String pass;

    public User(Long id, String name, String pass) {
        this.id = id;
        this.name = name;
        this.pass = pass;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id) && Objects.equals(name, user.name) && Objects.equals(pass, user.pass);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, pass);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", login='" + name + '\'' +
                ", password='" + pass + '\'' +
                '}';
    }
}
