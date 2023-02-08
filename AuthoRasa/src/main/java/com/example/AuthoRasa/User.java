package com.example.AuthoRasa;
import jakarta.persistence.*;

@Entity
@Table(name = "UserInfo", uniqueConstraints = {@UniqueConstraint(columnNames = {"email"})})
public class User {
    @Id
    private Long id;

    @Column(name = "userName")
    private String userName;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "gender")
    private String gender;

    @Column(name = "height")
    private int height;

    @Column(name = "weight")
    private double weight;

    @Column(name = "age")
    private int age;

    @Column(name = "activationRate")
    private double activationRate;

    @Column(name = "calories")
    private int calories;

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public double getActivationRate() {
        return activationRate;
    }

    public void setActivationRate(double activationRate) {
        this.activationRate = activationRate;
    }

    public int getCalories() {
        return calories;
    }

    public void setCalories(int calories) {
        this.calories = calories;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}