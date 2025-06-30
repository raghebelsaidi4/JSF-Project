package com.ragheb.controller;

import com.ragheb.service.EmployeeService;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import java.sql.SQLException;

@ManagedBean(name = "login", eager = true)
@RequestScoped
public class LoginController {

    private EmployeeService service = new EmployeeService();

    public String login() throws SQLException, ClassNotFoundException {
        FacesContext context = FacesContext.getCurrentInstance();
        String username = service.getParameterByName("username");
        String password = service.getParameterByName("password");
        boolean valid = service.checkLogin(username, password);
        if (valid) return "home";
        else {
            context.addMessage("loginForm",
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Username or password not valid, please try again!", ""));
            return null;
        }
    }

    public String logout() {
        HttpSession session = service.getSession();
        session.invalidate();
        return "login";
    }
}
