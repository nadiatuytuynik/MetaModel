package com.netcracker.controller.admin;

import com.netcracker.service.Impl.ObjectsServiceImpl;
import com.netcracker.service.ObjectsService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

@WebServlet(name="ListProjectsServlet", urlPatterns = "/ListProjectsServlet")
public class ListProjectsServlet extends HttpServlet {

    private ObjectsService objectsService;
    public ListProjectsServlet() {
        this.objectsService = new ObjectsServiceImpl();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();

        try {
            ArrayList<Integer> objIds = objectsService.GetObjectIdsByName("Project");
            ArrayList<ArrayList<ArrayList<String>>> dataContNames = objectsService.GetParamDataByObjectIds(objIds);

            if(dataContNames.size() !=0) {
                ArrayList<ArrayList<String>> dataNames = new ArrayList<>();
                ArrayList<ArrayList<String>> dataConts = new ArrayList<>();
                for (int i = 0; i < dataContNames.size(); i++) {
                    ArrayList<String> dataN = dataContNames.get(i).get(0);
                    ArrayList<String> dataC = dataContNames.get(i).get(1);
                    dataNames.add(dataN);
                    dataConts.add(dataC);
                }

                req.setAttribute("dataNames", dataNames.get(0));
                req.setAttribute("dataConts", dataConts);
                req.setAttribute("projects",1);
            }

        }catch(SQLException e){
            e.printStackTrace();
        }

        req.getRequestDispatcher("AdminPage.jsp").forward(req, resp);
    }
}

