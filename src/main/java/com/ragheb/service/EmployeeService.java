package com.ragheb.service;

import com.ragheb.bean.Employee;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeeService {

    private Connection conn;

    private void connect() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        try {
            String username = "root";
            String password = "ragheb01276323608";
            String url = "jdbc:mysql://localhost:3306/jsf_project";

            conn = DriverManager.getConnection(url, username, password);
        } catch (Exception e) {
            e.printStackTrace();
            throw new SQLException("Fail to connect with database ", e);
        }
    }

    private void cleanConnection() throws SQLException {
        conn.close();
    }

    public boolean checkLogin(String username, String password) throws SQLException, ClassNotFoundException {
        boolean b = true;

        connect();

        PreparedStatement preparedStatement = conn.prepareStatement("SELECT * FROM USERS WHERE USERNAME LIKE ? AND PASSWORD LIKE ?");
        preparedStatement.setString(1, username);
        preparedStatement.setString(2, password);

        ResultSet resultSet = preparedStatement.executeQuery();

        if (resultSet.next()) setSessionUsername(username);
        else b = false;

        cleanConnection();

        return b;
    }

    public HttpSession getSession() {
        return (HttpSession) FacesContext.getCurrentInstance()
                .getExternalContext().getSession(false);
    }

    private void setSessionUsername(String username) {
        HttpSession session = getSession();
        session.setAttribute("username", username);
    }

    public List<Employee> showAllEmployees() throws SQLException, ClassNotFoundException {
        List<Employee> listEmp = new ArrayList<Employee>();

        connect();

        PreparedStatement preparedStatement = conn.prepareStatement("SELECT * FROM EMPLOYEES");
        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            Employee employee = new Employee(resultSet.getInt(1), resultSet.getString(2),
                    resultSet.getString(3), resultSet.getString(4), resultSet.getString(5),
                    resultSet.getInt(6));
            listEmp.add(employee);
        }

        cleanConnection();

        return listEmp;
    }

    public Employee getEmployeeById(Integer id) throws SQLException, ClassNotFoundException {
        Employee employee = new Employee();

        connect();

        PreparedStatement preparedStatement = conn.prepareStatement("SELECT * FROM EMPLOYEES WHERE ID = ?");
        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();

        if (resultSet.next()) employee = new Employee(resultSet.getInt(1), resultSet.getString(2),
                resultSet.getString(3), resultSet.getString(4), resultSet.getString(5),
                resultSet.getInt(6));

        cleanConnection();

        return employee;
    }

    public Employee getEmployeeByName(String name) throws SQLException, ClassNotFoundException {
        Employee employee = new Employee();

        connect();

        PreparedStatement preparedStatement = conn.prepareStatement("SELECT * FROM EMPLOYEES WHERE FULLNAME LIKE ?");
        preparedStatement.setString(1, name);
        ResultSet resultSet = preparedStatement.executeQuery();

        if (resultSet.next()) employee = new Employee(resultSet.getInt(1), resultSet.getString(2),
                resultSet.getString(3), resultSet.getString(4), resultSet.getString(5),
                resultSet.getInt(6));

        cleanConnection();

        return employee;
    }

    public void addEmployee(String fullName, String address, String email, String phone, double salary)
            throws SQLException, ClassNotFoundException {
        connect();

        PreparedStatement preparedStatement = conn.prepareStatement("INSERT INTO EMPLOYEES" +
                "(FULLNAME, ADDRESS, EMAIL, PHONE, SALARY) " +
                "VALUES " +
                "(?, ?, ?, ?, ?)");
        preparedStatement.setNString(1, fullName);
        preparedStatement.setNString(2, address);
        preparedStatement.setString(3, email);
        preparedStatement.setString(4, phone);
        preparedStatement.setDouble(5, salary);
        preparedStatement.execute();

        cleanConnection();
    }

    public void editEmployee(Integer id, String fullName, String address, String email, String phone, double salary)
            throws SQLException, ClassNotFoundException {
        connect();

        PreparedStatement preparedStatement = conn.prepareStatement("UPDATE EMPLOYEES " +
                "SET FULLNAME = ?, ADDRESS = ?, EMAIL = ?, PHONE = ?, SALARY = ? " +
                "WHERE ID = ?");
        preparedStatement.setNString(1, fullName);
        preparedStatement.setNString(2, address);
        preparedStatement.setString(3, email);
        preparedStatement.setString(4, phone);
        preparedStatement.setDouble(5, salary);
        preparedStatement.setInt(6, id);
        preparedStatement.execute();

        cleanConnection();
    }

    public void deleteEmployee(Integer id) throws SQLException, ClassNotFoundException {
        connect();

        PreparedStatement preparedStatement = conn.prepareStatement("DELETE FROM EMPLOYEES WHERE ID = ?");
        preparedStatement.setInt(1, id);
        preparedStatement.execute();

        cleanConnection();
    }

    public HttpServletRequest getRequest() {
        return (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
    }

    public String getParameterByName(String name) {
        HttpServletRequest req = getRequest();
        return req.getParameter(name);
    }
}
