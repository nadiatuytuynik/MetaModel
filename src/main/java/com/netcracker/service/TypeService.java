package com.netcracker.service;

import com.netcracker.model.Type;

import java.sql.SQLException;
import java.util.ArrayList;

public interface TypeService {

    Type create(Type type);

    ArrayList<Integer> ChangeArrTypeStrToInt(ArrayList<String> arr) throws SQLException;
}
