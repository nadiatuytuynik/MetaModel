package com.netcracker.controller.customer;

import com.netcracker.service.*;
import com.netcracker.service.Impl.CustomerServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


@WebServlet(name = "RegistrationServlet", urlPatterns = "/RegistrationServlet")
public class RegistrationServlet extends HttpServlet {

    private CustomerService customerService;

    public RegistrationServlet() {
        this.customerService = new CustomerServiceImpl();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();

        List<String> enteredValues = new ArrayList<>();
        enteredValues.add(req.getParameter("Name"));
        enteredValues.add(req.getParameter("Secondname"));
        enteredValues.add(req.getParameter("Login"));
        enteredValues.add(req.getParameter("Password"));
        enteredValues.add(req.getParameter("Phone_number"));
        enteredValues.add(req.getParameter("Status"));

        ArrayList<String> listAttributes = new ArrayList<>();
        listAttributes.add("First_name");
        listAttributes.add("Second_name");
        listAttributes.add("Login");
        listAttributes.add("Password");
        listAttributes.add("Phone_number");
        listAttributes.add("Status");

        try {
            String progress = customerService.createCustomer("Customer", listAttributes, enteredValues);
            System.out.println("progress: " + progress);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        req.getRequestDispatcher("index.jsp").forward(req, resp);
    }
}
