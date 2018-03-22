package com.netcracker.controller.admin;


import com.netcracker.service.Impl.ObjectsServiceImpl;
import com.netcracker.service.Impl.ParamDataServiceImpl;
import com.netcracker.service.Impl.ParamsServiceImpl;
import com.netcracker.service.Impl.TypeServiceImpl;
import com.netcracker.service.ObjectsService;
import com.netcracker.service.ParamDataService;
import com.netcracker.service.ParamsService;
import com.netcracker.service.TypeService;
import com.netcracker.util.DBManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;

@WebServlet(name="NewEntityServlet", urlPatterns = "/NewEntityServlet")
public class NewEntityServlet extends HttpServlet {

    private ObjectsService objectsService;
    private TypeService typeService;
    private ParamsService paramsService;
    private ParamDataService paramDataService;
    public NewEntityServlet() {
        this.objectsService = new ObjectsServiceImpl();
        this.paramsService = new ParamsServiceImpl();
        this.typeService = new TypeServiceImpl();
        this.paramDataService = new ParamDataServiceImpl();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();

        String object_id = req.getParameter("Object_id");

        try {
            if(object_id != null) {
                int objId = Integer.parseInt(object_id);

                session.setAttribute("objId",objId);
                String objName = objectsService.GetObjectNameById(objId);
                ArrayList<ArrayList<String>> paramIdsNames = paramsService.GetParamIdsNamesByObjectId(objId);

                ArrayList<String> paramIds = paramIdsNames.get(1);
                ArrayList<String> paramNames = paramIdsNames.get(0);

                System.out.println(objName);

                session.setAttribute("objName", objName);
                session.setAttribute("paramIds", paramIds);
                session.setAttribute("paramNames", paramNames);

                req.setAttribute("newEntity", 1);
            }else{
                ArrayList<String> paramIds = (ArrayList<String>) session.getAttribute("paramIds");

                ArrayList<Integer> paramIdsInt = typeService.ChangeArrTypeStrToInt(paramIds);
                String attr = req.getParameter("Attributes");
                int attribute = Integer.parseInt(attr);

                int paramId = paramIdsInt.get(attribute-1);

                int objectId = (int)session.getAttribute("objId");

                String content = req.getParameter("Attribute_content");
                String result = paramDataService.CreateOrUpdateParamData(objectId, paramId, content);
                if(result!=null){
                    req.setAttribute("entityResult", 1);
                }else{
                    req.setAttribute("entityError", 1);
                }
            }
        }catch(SQLException e) {
            e.printStackTrace();
        }

        req.getRequestDispatcher("AdminPage.jsp").forward(req, resp);
    }
}
