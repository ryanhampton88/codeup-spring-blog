package com.codeup.codeupspringblog.models;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table
public class User {

//    CREATING COLUMN FIELDS
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "username", nullable = false)
    private String username;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

// CREATING RELATIONSHIPS
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private List<Post> posts;


// CREATING CONSTRUCTORS
    public User(){}

    public User(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }

// GETTERS AND SETTERS
    public void setId (Long id) {
        this.id = id;
    }

    public Long getId () {
        return id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername () {
        return username;
    }

    public void setEmail (String email) {
        this.email = email;
    }

    public String getEmail () {
        return email;
    }

    public void setPassword (String password) {
        this.password = password;
    }

    public String getPassword () {
        return password;
    }


}
