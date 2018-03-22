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


@WebServlet(name="ListEmployeesServlet", urlPatterns = "/ListEmployeesServlet")
public class ListEmployeesServlet extends HttpServlet {

    private ObjectsService objectsService;
    public ListEmployeesServlet() {
        this.objectsService = new ObjectsServiceImpl();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        try {
            ArrayList<Integer> objIds = objectsService.GetObjectIdsByName("Employee");
            ArrayList<ArrayList<ArrayList<String>>> dataContNames = objectsService.GetParamDataByObjectIds(objIds);

            ArrayList<String> objIdStr = new ArrayList<>();
            for (int i = 0; i <objIds.size(); i++) {
                int id = objIds.get(i);
                String idStr = Integer.toString(id);
                objIdStr.add(idStr);
            }

            ArrayList<ArrayList<String>> dataNames = new ArrayList<>();
            ArrayList<ArrayList<String>> dataCon = new ArrayList<>();
            for (int i = 0; i <dataContNames.size() ; i++) {
                ArrayList<String> dataN = dataContNames.get(i).get(0);
                ArrayList<String> dataC = new ArrayList<>();
                dataC.add(objIdStr.get(i));
                dataC.addAll(dataContNames.get(i).get(1));
                dataNames.add(dataN);
                dataCon.add(dataC);
            }

            req.setAttribute("dataNames", dataNames.get(0));
            req.setAttribute("dataCon", dataCon);

            req.setAttribute("employees", 1);

        }catch(SQLException e){
            e.printStackTrace();
        }

        req.getRequestDispatcher("AdminPage.jsp").forward(req, resp);
    }
}