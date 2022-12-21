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
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import static ke.co.mspace.anto.User.database;
import static ke.co.mspace.anto.User.ps;
import org.primefaces.event.RowEditEvent;
import util.Database;

/**
 *
 * @author developer
 */
@ManagedBean(name = "sendData")
@ViewScoped
public class SendData {

    @ManagedProperty(value = "#{user}")
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
    int user_id;

    @PostConstruct
    public void init() {
        users.removeAll(users);
        connection = database.getConnection();
        String query = "SELECT * FROM client_info";
        Statement stmt;
        try {
            stmt = connection.createStatement();
            data = stmt.executeQuery(query);
            while (data.next()) {
                System.out.println(data.getInt("user_id"));
                users.add(new User(data.getString("username"), data.getInt("user_id"), data.getString("password"), data.getInt("sms_credits"), data.getString("organizer"), data.getString("user_type"), data.getInt("user_mobile"), data.getString("user_email")));
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

    public ArrayList<User> sendData() {
        boolean response = false;
        try {
            System.out.println("trying to send data..");
            String query = "INSERT INTO client_info ( username, password, sms_credits, organizer, user_type, user_mobile, user_email ) VALUES ( ?,?,?,?,?,?,? )";
            connection = database.getConnection();
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, user.getClientname());
            ps.setString(2, user.getClientpassword());
            ps.setInt(3, user.getSmsCredits());
            ps.setString(4, user.getOrganisation());
            ps.setString(5, user.getUserType());
            ps.setInt(6, user.getUserMobile());
            ps.setString(7, user.getUserEmail());
            response = ps.execute();
            System.out.println("data sent successfully");
            System.out.println(response);
            init();
            GrowlView growl = new GrowlView();
            growl.showInfo("User Created", "User created successfully");

            System.out.println(user.getClientname());
            System.out.println(user.getClientpassword());
            

        } catch (SQLException ex) {
            System.out.println("Error is " + ex.getMessage().toUpperCase());
//            GrowlView growl = new GrowlView();
//            growl.showInfo("Failed", "User not Created");
        }
        clearInput();
        return users;
    }

    public String delete(String rowIndex) {
        boolean response = false;

        try {
            System.out.println(rowIndex);

            String query = "DELETE FROM client_info WHERE username = ?";
            connection = database.getConnection();
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, rowIndex);
            response = ps.execute();

            
            init();
            GrowlView growl = new GrowlView();
            growl.showError("Deleted", "User deleted successfully");
            System.out.println(response);

        } catch (SQLException ex) {
            Logger.getLogger(SendData.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rowIndex;
    }

    public int setUserId(int id) {
        System.out.println(id);
        user_id = id;
        return user_id;
    }

    public ArrayList<User> edit() {
//        init();
//        try {
//            System.out.println("trying to send data..");
////            String quer = "UPDATE client_info SET username = ?, password = ?, sms_credits = ?, organizer = ?, user_type = ?, user_mobile = ?, user_email = ?  WHERE username = ?";
//            String query = "UPDATE client_info SET username = ?, password = ?, sms_credits = ?, organizer = ?, user_type = ?, user_mobile = ?, user_email = ? " + " WHERE user_id = ?";
////            String que = "UPDATE client_info SET ( username, password, sms_credits, organizer, user_type, user_mobile, user_email ) VALUES ( ?,?,?,?,?,?,? ) WHERE username = ?";
//            connection = database.getConnection();
//            PreparedStatement ps = connection.prepareStatement(query);
//            ps.setString(1, user.getClientname());
//            ps.setString(2, user.getClientpassword());
//            ps.setInt(3, user.getSmsCredits());
//            ps.setString(4, user.getOrganisation());
//            ps.setString(5, user.getUserType());
//            ps.setInt(6, user.getUserMobile());
//            ps.setString(7, user.getUserEmail());
//            ps.setInt(8, user_id);
        System.out.println(user_id);
//            ps.executeUpdate();
//            GrowlView growl = new GrowlView();
//            growl.showInfo("User Created", "User created successfully");
//            init();
//
//        } catch (SQLException ex) {
//            System.out.println("Error is " + ex.getMessage().toUpperCase());
//            GrowlView growl = new GrowlView();
//            growl.showInfo("Failed", "User not Created");
//        }
        return users;
    }

    public void onRowEdit(RowEditEvent event) {
        try {
            User user = (User) event.getObject();

            user.setClientname(user.getClientname());
            user.setClientpassword(user.getClientpassword());
            String query = "UPDATE client_info SET username = ?, password = ?, sms_credits = ?, organizer = ?, user_type = ?, user_mobile = ?, user_email = ? " + " WHERE user_id = ?";
            connection = database.getConnection();
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, user.getClientname());
            ps.setString(2, user.getClientpassword());
            ps.setInt(3, user.getSmsCredits());
            ps.setString(4, user.getOrganisation());
            ps.setString(5, user.getUserType());
            ps.setInt(6, user.getUserMobile());
            ps.setString(7, user.getUserEmail());
            ps.setInt(8, user.getId());
            ps.executeUpdate();
            user.getClientname();
            init();

            System.out.println(user.getClientname());
            System.out.println(user.getClientpassword());
            System.out.println(user.getSmsCredits());
            System.out.println(user.getId());

            FacesMessage msg = new FacesMessage("User Edited", String.valueOf(user.getClientname()));
            FacesContext.getCurrentInstance().addMessage(null, msg);
        } catch (SQLException ex) {
            Logger.getLogger(SendData.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void onRowCancel(RowEditEvent event) {
        FacesMessage msg = new FacesMessage("Edit Cancelled", String.valueOf(user.getClientname()));
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public void clearInput() {
        user.setClientname("");
        user.setClientpassword("");
        user.setOrganisation("");
        user.setPassword("");
        user.setSmsCredits(0);
        user.setUserEmail("");
        user.setUserMobile(0);
        user.setUserType("");
    }

}
