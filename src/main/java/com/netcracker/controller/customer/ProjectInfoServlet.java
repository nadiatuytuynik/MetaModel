package com.netcracker.controller.customer;

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


@WebServlet(name="ProjectInfoServlet", urlPatterns = "/ProjectInfoServlet")
public class ProjectInfoServlet extends HttpServlet {

    private ObjectsService objectsService;
    public ProjectInfoServlet() {
        this.objectsService = new ObjectsServiceImpl();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();

        String projectNumber = req.getParameter("Project_number");
        String phone = req.getParameter("Phone");
        int userId = (int)session.getAttribute("userId");

        try {

            ArrayList<ArrayList<String>> projectInfo = objectsService.GetObjectInfoByContent(phone,userId, projectNumber);
            ArrayList<String> paramNames = projectInfo.get(0);
            ArrayList<String> paramContents = projectInfo.get(1);
            int objId = objectsService.GetObjectIdByParamData(phone, userId, projectNumber);

            ArrayList<ArrayList<ArrayList<String>>> sprintsInfo = objectsService.GetObjectsInfoByParentObjId(objId);

            ArrayList<ArrayList<String>> sprintsNames = new ArrayList<>();
            for (int i = 0; i < sprintsInfo.size(); i++) {
                ArrayList<String> sprintsN = sprintsInfo.get(i).get(0);
                sprintsNames.add(sprintsN);
            }

            ArrayList<ArrayList<String>> sprintsContents = new ArrayList<>();
            for (int i = 0; i < sprintsInfo.size(); i++) {
                ArrayList<String> sprintsC = sprintsInfo.get(i).get(1);
                sprintsContents.add(sprintsC);
            }

            System.out.println("sprintsNames = " + sprintsNames);
            System.out.println("sprintsContents = " + sprintsContents);

            ArrayList<Integer> sprintIds = objectsService.GetObjectIdsByParentId(objId);

            ArrayList<ArrayList<ArrayList<ArrayList<String>>>> tasksInfo = objectsService.GetObjectsInfoByParentObjIds(sprintIds);

            System.out.println(tasksInfo);

            ArrayList<ArrayList<String>> taskNames = new ArrayList<>();
            for (int i = 0; i <tasksInfo.size() ; i++) {
                for (int j = 0; j <tasksInfo.get(i).size() ; j++) {
                    ArrayList<String> taskN = tasksInfo.get(i).get(j).get(0);
                    taskNames.add(taskN);
                }
            }

            ArrayList<ArrayList<String>> taskContents = new ArrayList<>();
            for (int i = 0; i <tasksInfo.size() ; i++) {
                for (int j = 0; j <tasksInfo.get(i).size() ; j++) {
                    ArrayList<String> taskC = tasksInfo.get(i).get(j).get(1);
                    taskContents.add(taskC);
                }
            }

            System.out.println("taskNames = " + taskNames);
            System.out.println("taskContents = " + taskContents);

            req.setAttribute("paramNames", paramNames);
            req.setAttribute("paramContents", paramContents);

            req.setAttribute("sprintsNames", sprintsNames);
            req.setAttribute("sprintsContents", sprintsContents);

            req.setAttribute("taskNames", taskNames);
            req.setAttribute("taskContents", taskContents);

            req.setAttribute("info", 1);

        } catch (SQLException e) {
            e.printStackTrace();
        }

        req.getRequestDispatcher("index.jsp").forward(req, resp);
    }
}
