package com.netcracker.dao.Impl;

import com.netcracker.dao.CustomerDao;
import com.netcracker.util.DBManager;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;

public class CustomerDaoImpl implements CustomerDao {


    @Override
    public Object[] LogIn (String login, String pass) throws SQLException {
        Object[] arr = new Object[3];

        try {
            DBManager dbManager = DBManager.getInstance();
            Connection connection = dbManager.getConnection();

            CallableStatement getMaxObjectId = connection.prepareCall("{call LogIn(?,?,?,?,?,?,?)}");
            getMaxObjectId.setString(1, "Login");
            getMaxObjectId.setString(2, login);
            getMaxObjectId.setString(3, "Password");
            getMaxObjectId.setString(4, pass);
            getMaxObjectId.registerOutParameter(5, Types.VARCHAR);
            getMaxObjectId.registerOutParameter(6, Types.VARCHAR);
            getMaxObjectId.registerOutParameter(7, Types.INTEGER);
            getMaxObjectId.executeQuery();
            String result = (String) getMaxObjectId.getObject(5);
            String status = (String) getMaxObjectId.getObject(6);
            int user_id = (int) getMaxObjectId.getObject(7);
            getMaxObjectId.close();

            arr[0] = result;
            arr[1] = status;
            arr[2] = user_id;

            connection.close();

        }catch(SQLException e) {
            e.printStackTrace();
        }

        return arr;
    }
}
