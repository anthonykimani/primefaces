/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ke.co.mspace.anto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import static ke.co.mspace.anto.User.database;
import static ke.co.mspace.anto.User.ps;
import util.Database;

/**
 *
 * @author developer
 */
@ManagedBean(name = "sendData")
@ViewScoped
public class SendData {
    
    @ManagedProperty(value="#{user}")
    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
    

    static Database database = new Database();
    static ResultSet result;
    static ResultSet data;
    Connection connection;
    ArrayList<User> users = new ArrayList<>();

    @PostConstruct
    public void init() {
        connection = database.getConnection();
        String query = "SELECT * FROM client_info";
        Statement stmt;
        try {
            stmt = connection.createStatement();
            data = stmt.executeQuery(query);
            while (data.next()) {
                users.add(new User(data.getString("username"), data.getString("password"), data.getInt("sms_credits"), data.getString("organizer"), data.getString("user_type"), data.getInt("user_mobile"), data.getString("user_email")));
            }
            System.out.println("number of rows :" + users.size());
            System.out.println("All users :" + users);
        } catch (SQLException ex) {
            Logger.getLogger(SendData.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public ArrayList<User> getUsers() {

        return users;
    }

    public boolean sendData(){
        boolean response = false;
        try {
            System.out.println("trying to send data..");
            String query = "INSERT INTO client_info ( username, password, sms_credits, organizer, user_type, user_mobile, user_email ) VALUES ( ?,?,?,?,?,?,? )";
            connection = database.getConnection();
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, user.getClientname());
            ps.setString(2, user.getClientname());
            ps.setInt(3, user.getSmsCredits());
            ps.setString(4, user.getOrganisation());
            ps.setString(5, user.getUserType());
            ps.setInt(6, user.getUserMobile());
            ps.setString(7, user.getUserEmail());
            response = ps.execute();
            System.out.println("data sent successfully");
            System.out.println(response);
            System.out.println(user.getClientname());
            System.out.println(user.getClientpassword());
            
        } catch (SQLException ex) {
            System.out.println("Error is " + ex.getMessage().toUpperCase());
        }
        return response;
    }

}
