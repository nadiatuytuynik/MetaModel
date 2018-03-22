package com.netcracker.controller.customer;

import com.netcracker.service.CustomerService;
import com.netcracker.service.Impl.CustomerServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;


@WebServlet(name="AuthorizationServlet", urlPatterns = "/AuthorizationServlet")
public class AuthorizationServlet extends HttpServlet {

    private CustomerService customerService;
    public AuthorizationServlet() {
        this.customerService = new CustomerServiceImpl();
    }

    @Override
    protected  void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();

        String login = req.getParameter("Login");
        String pass = req.getParameter("Password");

        session.setAttribute("username", login);
        session.setAttribute("password", pass);

        try {
            Object[] value = customerService.identifyCustomer(login,pass);
            String userStatus = (String)value[0];
            String page = (String)value[1];
            int userId = (int)value[2];

            req.setAttribute(userStatus, 1);
            session.setAttribute("userId", userId);
            req.getRequestDispatcher(page).forward(req, resp);

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
