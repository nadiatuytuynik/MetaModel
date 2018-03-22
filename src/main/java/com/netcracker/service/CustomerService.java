package com.netcracker.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface CustomerService {

    String createCustomer(String objectName, ArrayList<String> listAttributes, List<String> enteredValues) throws SQLException;

    Object[] identifyCustomer(String login, String pass) throws SQLException;

}
