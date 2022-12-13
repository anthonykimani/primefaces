/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ke.co.mspace.anto;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
//import javax.inject.Inject;

/**
 *
 * @author developer
 */
@ManagedBean
@ViewScoped
public class BasicView implements Serializable {

    private List<User> users;

//    @Inject
    User person = new User();

    @PostConstruct
    public void init() {
//        users = service.getProducts(10);
    }

//    public static ResultSet getProducts() throws SQLException {
//        User user = new User();
////        return user.findUsername();
//        
//    }

//    public void setService(ProductService service) {
//        this.service = service;
//    }
}