package com.example.AuthoRasa;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "login_update")
public class LoginUpdate {
    @Id
    private Long id;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name="time_taken")
    private LocalDateTime time_taken;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public LocalDateTime getTime_taken() {
        return time_taken;
    }

    public void setTime_taken(LocalDateTime time_taken) {
        this.time_taken = time_taken;
    }
}
