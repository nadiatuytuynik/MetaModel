package com.netcracker.dao;

import com.netcracker.model.Objects;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface ObjectsDao extends AbstractDao<Objects,Long>{

    Integer[] GetMaxMinObjectId(String objectName) throws SQLException;

    List<Integer> GetParamId(ArrayList<String> listAttributes, Integer objectId) throws SQLException;

    Integer GetObjectIdByParamData (String phone, int userId, String projectNumber) throws SQLException;

    Integer[] GetMaxMinObjectIdByParentId (int parentId) throws SQLException;

    int GetObjectIdByParentId(int parentId, int testObjectId) throws SQLException;

    int GetObjectIdByName(String objectName, int testObjectId) throws SQLException;

    String GetObjectNameById(int objId) throws SQLException;
}

