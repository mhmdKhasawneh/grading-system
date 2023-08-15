package com.example.atypon.spring.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.stereotype.Component;

@Getter
@Setter
@AllArgsConstructor
@ToString
@Component
public class User {
    private int id;
    private String name;
    private String email;
    private String password;
    private String role;

    public User(){}
}
