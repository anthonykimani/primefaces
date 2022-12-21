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
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import util.Database;

/**
 *
 * @author developer
 */
@ManagedBean(name="login")
public class Login {
    
    @ManagedProperty(value="#{user}")
    private User user ;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
    
    static Database database = new Database();
//    User user = new User();
    static ResultSet result;
//    static ResultSet data;
//    String username = user.getUser();
//    String password  = user.getPassword();
    Connection connection;

    public String login() {
        String dbUsername = "";
        String dbPassword = "";
        
        try {
            connection = database.getConnection();
            String query = "SELECT username, password  FROM user_acc";

            Statement stmt;

            stmt = connection.createStatement();
            result = stmt.executeQuery(query);
            while (result.next()) {
                dbUsername = result.getString("username");
                dbPassword = result.getString("password");
            }

        } catch (SQLException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        }

        if (user.getUsername().equals(dbUsername)
                && user.getPassword().equals(dbPassword)) {
            System.out.println("user logged in");
            return "home.xhtml";
        } else {
            System.out.println("user not logged in ");
            GrowlView growl = new GrowlView();
            growl.showError("Authentication Failed", "User not Found");
            System.out.println("step by step");
//            FacesContext.getCurrentInstance().
//                addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "done", "done"));
            return "index.xhtml";
        }
    }
}
