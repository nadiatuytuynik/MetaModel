package com.netcracker.service.Impl;

import com.netcracker.dao.CustomerDao;
import com.netcracker.dao.Impl.CustomerDaoImpl;
import com.netcracker.dao.ObjectsDao;
import com.netcracker.dao.Impl.ObjectsDaoImpl;
import com.netcracker.model.Objects;
import com.netcracker.model.ParamData;
import com.netcracker.model.Params;
import com.netcracker.service.CustomerService;
import com.netcracker.service.ObjectsService;
import com.netcracker.service.ParamDataService;
import com.netcracker.service.ParamsService;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerServiceImpl implements CustomerService {

    private ObjectsService objectsService;
    private ParamsService paramsService;
    private ObjectsDao objectsDao;
    private CustomerDao customerDao;
    private ParamDataService paramDataService;

    public CustomerServiceImpl() {
        this.objectsService = new ObjectsServiceImpl();
        this.paramsService = new ParamsServiceImpl();
        this.objectsDao = new ObjectsDaoImpl();
        this.customerDao = new CustomerDaoImpl();
        this.paramDataService = new ParamDataServiceImpl();
    }

    @Override
    public String createCustomer(String objectName, ArrayList<String> listAttributes, List<String> enteredValues) throws SQLException {
        String result = "failed create";

        if (objectName.equals("Customer")) {//если метод будет универсален(т.е. будет отвечать за создание любого обьекта), то эта логика уместна
            Objects object = new Objects(0, objectName);
            this.objectsService.create(object);
        }

        Integer[] maxMinObjectId = this.objectsService.GetMaxMinObjectIdByName(objectName);
        Integer objectId = maxMinObjectId[0];
        for (String aList2 : listAttributes) {
            Params params1 = new Params(aList2, objectId, 1);
            this.paramsService.create(params1);
        }

        List<Integer> paramIds = this.objectsDao.GetParamId(listAttributes, objectId);

        if (paramIds.size() == enteredValues.size()) {
            for (int i = 0; i < enteredValues.size(); i++) {
                ParamData paramData = new ParamData(enteredValues.get(i), paramIds.get(i));
                this.paramDataService.create(paramData);
            }
            result = "success";
        }

        return result;
    }


    @Override
    public Object[] identifyCustomer(String login, String pass) throws SQLException {
        Object[] identifiedCustomer= new Object[3];

        String userStatus =  "";
        String page = "";
        Object[] arr = this.customerDao.LogIn(login, pass);
        String result = (String)arr[0];
        String status = (String)arr[1];
        int user_id = (int)arr[2];

        if(result.equals("none")){
            userStatus = "not_a_customer";
            page = "index.jsp";
            user_id = 0;

            }else {
                if (status.equals("user")) {
                    userStatus = "authorization";
                    System.out.println(status);
                    page = "index.jsp";
                } else {
                    if (status.equals("admin")) {
                        userStatus = "authorization1";
                        System.out.println(status);
                        page = "AdminPage.jsp";
                }else{
                        if(status.equals("employee")){
                            userStatus = "authorization2";
                            System.out.println(status);
                            page = "AdminPage.jsp";
                        }
                    }
                }
            }

        identifiedCustomer[0] = userStatus;
        identifiedCustomer[1] = page;
        identifiedCustomer[2] = user_id;

        return identifiedCustomer;
    }
}
