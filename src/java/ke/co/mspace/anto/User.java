/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ke.co.mspace.anto;

import java.io.Serializable;
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
@ManagedBean(name="user")
@SessionScoped
public class User implements Serializable{

    static Database database = new Database();
    static PreparedStatement ps = null;
    static ResultSet result = null;
    static ResultSet data = null;

    private int id;
    private String username;
    private String password;
    private String clientname;
    private String clientpassword;
    private int smsCredits;
    private String organisation;
    private String userType;
    private int userMobile;
    private String userEmail;

    public User() {
        
    }

    public User( String clientname, int user_id, String clientpassword, int smsCredits, String organisation, String userType, int userMobile, String userEmail) {
        this.id = user_id;
        this.clientname = clientname;
        this.clientpassword = clientpassword;
        this.smsCredits = smsCredits;
        this.organisation = organisation;
        this.userType = userType;
        this.userMobile = userMobile;
        this.userEmail = userEmail;
    }
    
    public User(String username, int user_id, String password, String clientname, String clientpassword, int smsCredits, String organisation, String userType, String userEmail, int userMobile) {
        this.username = username;
        this.id = user_id;
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

    public int getSmsCredits() {
        return smsCredits;
    }

    public void setSmsCredits(int smsCredits) {
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
        Login loginUser = new Login();
        return loginUser.login();
    }

    
    
    public ResultSet findUsername() throws SQLException{
        String personName = null;
        String query = "SELECT * FROM client_info";
        ps = database.getConnection().prepareStatement(query);
        data = ps.executeQuery();
        while(result.next()){
            personName = result.getString("username");
        }
        System.out.println("query done");
        System.out.println(data);
        System.out.println(personName);
        return result;
    }
    
}
