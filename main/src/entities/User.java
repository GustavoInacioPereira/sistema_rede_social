package entities;

import java.util.ArrayList;
import java.util.List;

import entities.enums.Status;

public class User {
    private Integer idUser;
    private String name, email, password;
    private Status STATUS;
    private List<Posts> posts = new ArrayList<Posts>();
    
    public User(Integer idUser, String name, String email, String password, Status STATUS, List<Posts> posts) {
        this.idUser = idUser;
        this.name = name;
        this.email = email;
        this.password = password;
        this.STATUS = STATUS;
        this.posts = posts;
    }
    public User(Integer idUser, String name, String email, String password, Status STATUS) {
        this.idUser = idUser;
        this.name = name;
        this.email = email;
        this.password = password;
        this.STATUS = STATUS;

    }

    public Integer getIdUser() {
        return idUser;
    }
    public String getName() {
        return name;
    }
    public String getEmail() {
        return email;
    }
    public String getPassword() {
        return password;
    }
    public List<Posts> getPosts() {
        return posts;
    }
    public Status getSTATUS() {
        return STATUS;
    }

    @Override
    public String toString() {
        return idUser + ";" + name + ";" + email + ";" + password + ";" + STATUS;
        
    }
}

