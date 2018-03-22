package com.netcracker.dao;

import java.sql.SQLException;

public interface CustomerDao {

    Object[] LogIn (String login, String pass) throws SQLException;
}
