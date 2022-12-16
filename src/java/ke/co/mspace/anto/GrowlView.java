/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ke.co.mspace.anto;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
//import javax.inject.Named;

/**
 *
 * @author developer
 */
@ManagedBean
@RequestScoped
public class GrowlView {

    public void addMessage(FacesMessage.Severity severity, String summary, String detail) {
        FacesContext.getCurrentInstance().
                addMessage(null, new FacesMessage(severity, summary, detail));
        System.out.println("error");
    }

    public void showError() {
        addMessage(FacesMessage.SEVERITY_ERROR, "Sign in Error", "Username or Password not found");
    }

    public void showInfo(String message, String message_info) {
        addMessage(FacesMessage.SEVERITY_INFO, message, message_info);
    }
}
