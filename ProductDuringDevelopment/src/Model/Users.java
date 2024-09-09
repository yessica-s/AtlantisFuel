/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;
import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author yessi
 */
public class Users implements Serializable{
    /*allows the data in this class to be serialised. 
    since the model classes control the storage of data,
    the classes responsible for the serialised data are these
    */
    
    public Users(ArrayList[] users) {
        this.users = users;
    }
    
    private ArrayList[] users;
    private String username;
    private String password;
    private String firstname;
    private String surname; 
    private String email;
    private int phone;

    public Users(String username, String password, String firstname, String surname,
            String email, int phone){
        this.username = username;
        this.password = password;
        this.firstname = firstname;
        this.surname = surname;
        this.email = email;
        this.phone = phone; 
    }
    
    public Users() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }  

    public ArrayList[] getUsers() {
        return users;
    }

    public void setUsers(ArrayList[] users) {
        this.users = users;
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

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public long getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }
    
}
