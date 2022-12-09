/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ke.co.mspace.anto;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import util.Database;

/**
 *
 * @author developer
 */
@ManagedBean
@SessionScoped
public class User {

    static Database database = new Database();
    static PreparedStatement ps = null;
    static ResultSet result = null;

    private int id;
    private String username;
    private String password;
    private String clientname;
    private String clientpassword;
    private String smsCredits;
    private String organisation;
    private String userType;
    private int userMobile;
    private String userEmail;

    public User() {
    }

    public User(String username, String password, String clientname, String clientpassword, String smsCredits, String organisation, String userType, String userEmail, int userMobile) {
        this.username = username;
        this.password = password;
        this.clientname = clientname;
        this.clientpassword = clientpassword;
        this.smsCredits = smsCredits;
        this.organisation = organisation;
        this.userType = userType;
        this.userEmail = userEmail;
        this.userMobile = userMobile;
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

    public String getClientname() {
        return clientname;
    }

    public void setClientname(String clientname) {
        this.clientname = clientname;
    }

    public String getClientpassword() {
        return clientpassword;
    }

    public void setClientpassword(String clientpassword) {
        this.clientpassword = clientpassword;
    }

    public String getSmsCredits() {
        return smsCredits;
    }

    public void setSmsCredits(String smsCredits) {
        this.smsCredits = smsCredits;
    }

    public String getOrganisation() {
        return organisation;
    }

    public void setOrganisation(String organisation) {
        this.organisation = organisation;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public int getUserMobile() {
        return userMobile;
    }

    public void setUserMobile(int userMobile) {
        this.userMobile = userMobile;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final User other = (User) obj;
        if (this.id != other.id) {
            return false;
        }
        if (!Objects.equals(this.username, other.username)) {
            return false;
        }
        if (!Objects.equals(this.password, other.password)) {
            return false;
        }
        return true;
    }

    public String login() throws ClassNotFoundException, SQLException {

        System.out.println("username is " + username);
        String query = "SELECT * FROM user_acc";
        String dbUsername = null;
        String dbPassword = null;

        try {
            ps = database.Database().prepareStatement(query);
            result = ps.executeQuery();
            while (result.next()) {
                dbUsername = result.getString("username");
                dbPassword = result.getString("password");
            }
            System.out.println(dbUsername);
            System.out.println(dbPassword);
        } catch (SQLException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            database.Database().close();
            System.out.println("connection closed");
        }
        if (username.equals(dbUsername) && password.equals(dbPassword)) {
            System.out.println("user logged in");
            return "home.xhtml";
        } else {
            System.out.println("user not logged in ");
            GrowlView growl = new GrowlView();
            growl.showError();
            return "index.xhtml";
        }
    }

    public String sendData() throws SQLException {
        System.out.println("trying to send data..");
        String query = "INSERT INTO client_info ( username, password, sms_credits, organizer, user_type, user_mobile, user_email ) VALUES ( ?,?,?,?,?,?,? )";
        try {

            ps = database.Database().prepareStatement(query);
            ps.setString(1, clientname);
            ps.setString(2, clientpassword);
            ps.setString(3, smsCredits);
            ps.setString(4, organisation);
            ps.setString(5, userType);
            ps.setInt(6, userMobile);
            ps.setString(7, userEmail);
            ps.execute();
            System.out.println(clientname);

            System.out.println("data sent successfully");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        }

        return "data sent successfully";
    }
    
//     public void showError() {
//        addMessage(FacesMessage.SEVERITY_ERROR, "Error Message", "Message Content");
//    }


}
