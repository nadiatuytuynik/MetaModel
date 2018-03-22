package com.netcracker.service;

import com.netcracker.model.Objects;

import java.sql.SQLException;
import java.util.ArrayList;

public interface ObjectsService {

    Objects create(Objects objects);

    Integer[] GetMaxMinObjectIdByName(String objectName) throws SQLException;

    int GetObjectIdByParentId(int parentId, int testObjectId) throws SQLException;

    ArrayList<ArrayList<String>> GetObjectInfoByContent(String phone, int userId, String projectNumber) throws SQLException;

    Integer[] GetMaxMinParamIdByObjectId(int objId) throws SQLException;

    Object[] GetParamIdByObjectId(int objId, int testParamId) throws SQLException;

    int GetObjectIdByParamData(String phone, int userId, String dataContent) throws SQLException;

    String GetParamDataByParamId(int paramId) throws SQLException;

    Integer[] GetMaxMinObjectIdByParentId(int parentId) throws SQLException;

    ArrayList<ArrayList<String>> GetParamDataByObjectId(int objectId) throws SQLException;

    ArrayList<Integer> GetObjectIdsByParentId(int parentId) throws SQLException;

    ArrayList<ArrayList<ArrayList<String>>> GetObjectsInfoByParentObjId(int objId) throws SQLException;

    ArrayList<ArrayList<ArrayList<ArrayList<String>>>> GetObjectsInfoByParentObjIds(ArrayList<Integer> obIds) throws SQLException;

    Integer GetObjectIdByName(String objectName, int testObjId) throws SQLException;

    ArrayList<Integer> GetObjectIdsByName(String objectName) throws SQLException;

    ArrayList<ArrayList<ArrayList<String>>> GetParamDataByObjectIds(ArrayList<Integer> objIds) throws SQLException;

    String GetObjectNameById(int objId) throws SQLException;

    String CreateObject(String objectName, String parentId) throws SQLException;

}
