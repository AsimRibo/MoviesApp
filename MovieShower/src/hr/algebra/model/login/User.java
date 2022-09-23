/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hr.algebra.model.login;

/**
 *
 * @author asim2
 */
public class User {

    private int id;
    private String username;
    private String password;
    private boolean admin;

    public User() {
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;

    }

    public User(int id, String username, String password, boolean admin) {
        this(username, password);
        this.id = id;
        this.admin = admin;
    }

    @Override
    public String toString() {
        return username;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }
}
