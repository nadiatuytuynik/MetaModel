package com.netcracker.controller.admin;

import com.netcracker.service.*;
import com.netcracker.service.Impl.ObjectsServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name="NewObjectServlet", urlPatterns = "/NewObjectServlet")
public class NewObjectServlet extends HttpServlet {

    private ObjectsService objectsService;
    public NewObjectServlet() {
        this.objectsService = new ObjectsServiceImpl();
    }

    @Override
    protected  void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();

        String name_of_object = req.getParameter("Object_name");
        String parent_id = req.getParameter("Parent_object_id");

        try {
            String result = objectsService.CreateObject(name_of_object,parent_id);
            if(result!=null){
                req.setAttribute("object",1);
            }else{
                req.setAttribute("objectError",1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        req.setAttribute("value",1);
        req.getRequestDispatcher("AdminPage.jsp").forward(req, resp);
    }
}
