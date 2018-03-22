package com.netcracker.controller.admin;

import com.netcracker.model.Params;
import com.netcracker.service.*;
import com.netcracker.service.Impl.ParamsServiceImpl;
import com.netcracker.service.Impl.TypeServiceImpl;
import com.netcracker.util.DBManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;


@WebServlet(name="NewAttributeServlet", urlPatterns = "/NewAttributeServlet")
public class NewAttributeServlet extends HttpServlet {

    private ParamsService paramsService;
    private TypeService typeService;
    public NewAttributeServlet() {
        this.paramsService = new ParamsServiceImpl();
        this.typeService = new TypeServiceImpl();
    }

    @Override
    protected  void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();

        String object_id = req.getParameter("Object_id");
        String attribute = req.getParameter("Attribute");
        String type = req.getParameter("Type");

        try {
            String result = paramsService.CreateParam(attribute,object_id,type);
            if(result!=null){
                req.setAttribute("attribute",1);
            }else{
                req.setAttribute("attributeError",1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        req.getRequestDispatcher("AdminPage.jsp").forward(req, resp);
    }
}
