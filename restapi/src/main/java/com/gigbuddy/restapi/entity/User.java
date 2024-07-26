package com.gigbuddy.restapi.entity;

import jakarta.persistence.*;

@Entity
@Table(name="members")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @Column(name="first_name")
    private String firstName;

    @Column(name="last_name")
    private String lastName;

    @Column(name="user_name")
    private String userName;

    @Column(name="email")
    private String email;

//    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
//    private LoginCredentials loginCredentials;

    // constructors - no args and args
    public User(){
    }

    public User(String firstName, String lastName, String userName, String email){
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
        this.email = email;
    }

    // getters/setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

//    public LoginCredentials getLoginCredentials() {
//        return loginCredentials;
//    }

//    public void setLoginCredentials(LoginCredentials loginCredentials) {
//        this.loginCredentials = loginCredentials;
//    }

    // define to string object
    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
