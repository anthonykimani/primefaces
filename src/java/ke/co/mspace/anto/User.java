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
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
//import static javax.faces.component.html.HtmlDataTable.PropertyKeys.summary;
import javax.faces.context.FacesContext;
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
    static ResultSet data = null;

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


    public String login() throws ClassNotFoundException, SQLException {

        System.out.println("username is " + username);
        String query = "SELECT * FROM user_acc";
        String dbUsername = null;
        String dbPassword = null;

        try {
            ps = database.getConnection().prepareStatement(query);
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
            database.getConnection().close();
            System.out.println("connection closed");
        }
        if (username.equals(dbUsername) && password.equals(dbPassword)) {
            System.out.println("user logged in");
            return "home.xhtml";
        } else {
            System.out.println("user not logged in ");
            GrowlView growl = new GrowlView();
            growl.showError();
            System.out.println("step by step");
//            FacesContext.getCurrentInstance().
//                addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "done", "done"));
            return "index.xhtml";
        }
    }

    
    
//    public ResultSet findUsername() throws SQLException{
//        String personName = null;
//        String query = "SELECT * FROM client_info";
//        try {
//            ps = database.Database().prepareStatement(query);
//             data = ps.executeQuery();
//            while(result.next()){
//                personName = result.getString("username");
//            }
//        } catch (ClassNotFoundException ex) {
//            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        System.out.println("query done");
//        System.out.println(data);
//        System.out.println(personName);
//        return result;
//    }
//    
}
