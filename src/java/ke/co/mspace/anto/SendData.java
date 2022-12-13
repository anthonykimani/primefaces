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
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import static ke.co.mspace.anto.User.database;
import util.Database;

/**
 *
 * @author developer
 */
@ManagedBean(name="sendData")
@ViewScoped
public class SendData {

    static Database database = new Database();
    static PreparedStatement ps;
    static ResultSet result;
    static ResultSet data;
    Connection connection;
    ArrayList<User> users = new ArrayList<>();

    public ArrayList<User> getUsers() {
        connection = database.getConnection();
        String query = "SELECT * FROM client_info";
        Statement stmt;
        try {
            stmt = connection.createStatement();
            data = stmt.executeQuery(query);
            while(data.next()){
                User person = new User();
                person.setClientname(data.getString("username"));
                person.setClientpassword(data.getString("password"));
                person.setSmsCredits(data.getString("sms_credits"));
                person.setOrganisation(data.getString("organizer"));
                person.setUserType(data.getString("user_type"));
                person.setUserMobile(data.getInt("user_mobile"));
                person.setUserEmail(data.getString("user_email"));
                users.add(person);
                System.out.println("number of rows :" + users.size());
            }
        } catch (SQLException ex) {
            Logger.getLogger(SendData.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return users;
    }

}
