package com.example.firstproject.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class Members {

    @Id
    @GeneratedValue
    private Long id;
    @Column
    private String email;
    @Column
    private String password;

    public Members(Long id, String email, String password) {
        this.id = id;
        this.email = email;
        this.password = password;
    }

    public Members() {

    }

    @Override
    public String toString() {
        return "Members{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
