package com.ragheb.controller;

import com.ragheb.bean.Employee;
import com.ragheb.service.EmployeeService;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@ManagedBean(name = "emp", eager = true)
@RequestScoped
public class EmployeeController {

    private EmployeeService service = new EmployeeService();

    public List<Employee> showAllEmployees() throws SQLException, ClassNotFoundException {
        return service.showAllEmployees();
    }

    public Employee getEmployee() throws SQLException, ClassNotFoundException {
        String idStr = service.getParameterByName("id");
        int id;
        try {
            id = Integer.valueOf(idStr);
        } catch (NumberFormatException e) {
            id = 0;
        }
        return service.getEmployeeById(id);
    }

    public String addEmployee() throws SQLException, ClassNotFoundException {
        String fullName = service.getParameterByName("fullName");
        String address = service.getParameterByName("address");
        String email = service.getParameterByName("email");
        String phone = service.getParameterByName("phone");
        String salaryStr = service.getParameterByName("salary");
        int salary = Integer.valueOf(salaryStr);

        service.addEmployee(fullName, address, email, phone, salary);
        return "home";
    }

    public void editEmployee() throws SQLException, ClassNotFoundException, IOException {
        String idStr = service.getParameterByName("editForm:id");
        int id = Integer.valueOf(idStr);
        String fullName = service.getParameterByName("fullName");
        String address = service.getParameterByName("address");
        String email = service.getParameterByName("email");
        String phone = service.getParameterByName("phone");
        String salaryStr = service.getParameterByName("salary");
        int salary = Integer.valueOf(salaryStr);

        service.editEmployee(id, fullName, address, email, phone, salary);

        FacesContext.getCurrentInstance().getExternalContext().redirect("home.xhtml"); //Redirect to home.xhtml
    }

    public String deleteEmployee(String idStr) throws SQLException, ClassNotFoundException {
        int id = Integer.valueOf(idStr);

        service.deleteEmployee(id);
        return "home";
    }
}
