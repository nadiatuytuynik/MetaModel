package com.netcracker.controller.employee;

import com.netcracker.service.Impl.ParamDataServiceImpl;
import com.netcracker.service.ParamDataService;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;


@WebServlet(name = "TasksServlet", urlPatterns = "/TasksServlet")
public class TasksServlet extends HttpServlet {

    private ParamDataService paramDataService;
    public TasksServlet() {
        this.paramDataService = new ParamDataServiceImpl();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();

        int employeeId = (int)session.getAttribute("userId");
        String ManyToManyTable = "Task_Employee";
        String objName1 = "Task";
        String paramId1 = "Employee_Id";
        String paramId2 = "Task_Id";
        String checkBox1 = req.getParameter("1");
        int taskId = 0;

        int rows = (int)session.getAttribute("countOfRows");

        try {
            Object[] result = paramDataService.GetParamDataManyToManyObjects(ManyToManyTable, employeeId, checkBox1, paramId1, paramId2);
            ArrayList<Integer> taskIds = (ArrayList<Integer>) result[0];
            ArrayList<ArrayList<ArrayList<String>>>  dataContNames = ( ArrayList<ArrayList<ArrayList<String>>>)result[1];

            ArrayList<Integer> tasksUpdate = new ArrayList<>();
            for (int i = 1; i <=rows ; i++) {
                String str = Integer.toString(i);
                String checkBox = req.getParameter(str);
                if(checkBox!=null){
                    int taskU = taskIds.get(i-1);
                    tasksUpdate.add(taskU);
                }
            }

            for (int i = 0; i <tasksUpdate.size() ; i++) {
                String status = paramDataService.UpdateParamDataByObjectIdAndBool(tasksUpdate.get(i), objName1, "Status", "done", checkBox1);
                System.out.println(status);
                if (status != null) {
                    req.setAttribute("status", 1);
                }
            }
            ArrayList<ArrayList<String>> dataNames = new ArrayList<>();
            ArrayList<ArrayList<String>> dataContents = new ArrayList<>();
            for (ArrayList<ArrayList<String>> dataContName : dataContNames) {
                ArrayList<String> dataN = dataContName.get(0);
                ArrayList<String> dataC = dataContName.get(1);
                dataNames.add(dataN);
                dataContents.add(dataC);
            }

            ArrayList<String> dataName = dataNames.get(0);
            System.out.println(dataNames);
            System.out.println(dataContents);

            req.setAttribute("taskIds", taskIds);
            req.setAttribute("dataNames", dataName);
            req.setAttribute("dataContents", dataContents);

            req.setAttribute("tasksInfo", 1);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        req.getRequestDispatcher("AdminPage.jsp").forward(req, resp);
    }
}