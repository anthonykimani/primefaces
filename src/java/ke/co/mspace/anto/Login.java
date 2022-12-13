///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package ke.co.mspace.anto;
//
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.Statement;
//import util.Database;
//
///**
// *
// * @author developer
// */
//public class Login {
//
//    static Database database = new Database();
//    static ResultSet result;
//    static ResultSet data;
//    Connection connection;
//
//    public String login() {
//
//        try {
//            connection = database.getConnection();
//            String query = "SELECT * FROM user_acc";
//            String dbUsername;
//            String dbPassword;
//            Statement stmt;
//
//            stmt = connection.createStatement();
//            data = stmt.executeQuery(query);
//            while (data.next()) {
//                dbUsername = result.getString("username");
//                dbPassword = result.getString("password");
//            }
//
//        } catch (SQLException ex) {
//            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
//        } finally {
//            database.getConnection().close();
//            System.out.println("connection closed");
//        }
//
//        if (username.equals(dbUsername)
//                && password.equals(dbPassword)) {
//            System.out.println("user logged in");
//            return "home.xhtml";
//        } else {
//            System.out.println("user not logged in ");
//            GrowlView growl = new GrowlView();
//            growl.showError();
//            System.out.println("step by step");
////            FacesContext.getCurrentInstance().
////                addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "done", "done"));
//            return "index.xhtml";
//        }
//    }
//}
