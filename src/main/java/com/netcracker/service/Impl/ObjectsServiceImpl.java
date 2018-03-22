package com.netcracker.service.Impl;

import com.netcracker.dao.*;
import com.netcracker.dao.Impl.ObjectsDaoImpl;
import com.netcracker.dao.Impl.ParamDataDaoImpl;
import com.netcracker.dao.Impl.ParamsDaoImpl;
import com.netcracker.model.Objects;
import com.netcracker.model.Params;
import com.netcracker.service.ObjectsService;

import java.sql.SQLException;
import java.util.ArrayList;

public class ObjectsServiceImpl implements ObjectsService {

    private ObjectsDao objectsDao;
    private ParamsDao paramsDao;
    private ParamDataDao paramDataDao;
    public ObjectsServiceImpl() {
        this.objectsDao = new ObjectsDaoImpl();
        this.paramsDao = new ParamsDaoImpl();
        this.paramDataDao = new ParamDataDaoImpl();
    }

    @Override
    public Objects create(Objects objects) {
        return this.objectsDao.create(objects);
    }
    // assert not null (user) -> throw exception

    @Override
    public Integer[] GetMaxMinObjectIdByName(String objectName) throws SQLException {
        return objectsDao.GetMaxMinObjectId(objectName);
    }

    @Override
    public Integer GetObjectIdByName(String objectName, int testObjId) throws SQLException {
        return objectsDao.GetObjectIdByName(objectName, testObjId);
    }

    @Override
    public String GetObjectNameById(int objId) throws SQLException {
        return objectsDao.GetObjectNameById(objId);
    }


    @Override
    public ArrayList<Integer> GetObjectIdsByName(String objectName) throws SQLException {
        Integer[] maxMinObjId = GetMaxMinObjectIdByName(objectName);
        int maxObjId = maxMinObjId[0];
        int minObjId = maxMinObjId[1];
        ArrayList<Integer> objIds = new ArrayList<>();
        for (int i = minObjId; i <=maxObjId ; i++) {
            int objId = GetObjectIdByName(objectName, i);
            if(objId!=0){
                objIds.add(objId);
            }
        }
        return objIds;
    }

    @Override
    public Integer[] GetMaxMinObjectIdByParentId(int parentId) throws SQLException {
        return  objectsDao.GetMaxMinObjectIdByParentId(parentId);
    }

    @Override
    public int GetObjectIdByParentId(int parentId, int testObjectId) throws SQLException {
        return objectsDao.GetObjectIdByParentId(parentId, testObjectId);
    }

    @Override
    public ArrayList<Integer> GetObjectIdsByParentId(int parentId) throws SQLException {
        Integer[] maxMinObjIds = GetMaxMinObjectIdByParentId(parentId);
        int maxObjId = maxMinObjIds[0];
        int minObjId = maxMinObjIds[1];

        ArrayList<Integer> objIds = new ArrayList<>();
        for (int i = minObjId; i <=maxObjId ; i++) {
            int objId = GetObjectIdByParentId(parentId, i);
            if(objId!=0){
                objIds.add(objId);
            }
        }
        return objIds;
    }


    @Override
    public Integer[] GetMaxMinParamIdByObjectId(int objId) throws SQLException {
        return paramsDao.GetMaxMinParamIdByObjectId(objId);
    }

    @Override
    public Object[] GetParamIdByObjectId(int objId, int testParamId) throws SQLException {
        return paramsDao.GetParamIdByObjectId(objId, testParamId);
    }

    @Override
    public String GetParamDataByParamId(int paramId) throws SQLException {
        return  paramDataDao.GetParamDataContByParamId(paramId);
    }

    @Override
    public int GetObjectIdByParamData(String phone, int userId, String dataContent) throws SQLException {
        return objectsDao.GetObjectIdByParamData(phone, userId, dataContent);
    }


    @Override
    public ArrayList<ArrayList<String>> GetParamDataByObjectId(int objId) throws SQLException {
        Integer[] maxMinParamIdByObjectId = GetMaxMinParamIdByObjectId(objId);
        int maxParamId = maxMinParamIdByObjectId[0];
        int minParamId = maxMinParamIdByObjectId[1];
        ArrayList<Integer> paramIds = new ArrayList<>();
        ArrayList<String> paramNames = new ArrayList<>();
        for (int i = minParamId; i <=maxParamId; i++) {
            Object[] paramIdName = GetParamIdByObjectId(objId, i);
            int paramId = (int)paramIdName[0];
            String paramName = (String)paramIdName[1];
            if(paramName!=null){
                paramIds.add(paramId);
                paramNames.add(paramName);
            }
        }
        ArrayList<String> dataContents = new ArrayList<>();
        for (int i = 0; i < paramIds.size(); i++) {
            String dataContent = GetParamDataByParamId(paramIds.get(i));
            if(!dataContent.equals("none")){
                dataContents.add(dataContent);
            }
        }
        ArrayList<ArrayList<String>> dataContNames = new ArrayList<>();
        dataContNames.add(paramNames);
        dataContNames.add(dataContents);

        return dataContNames;
    }

    @Override
    public ArrayList<ArrayList<ArrayList<String>>> GetParamDataByObjectIds(ArrayList<Integer> objIds) throws SQLException {
        ArrayList<ArrayList<ArrayList<String>>> dataContNames = new ArrayList<>();
        for (int i = 0; i <objIds.size() ; i++) {
            if(objIds.get(i) != 0) {
                ArrayList<ArrayList<String>> dataCN = GetParamDataByObjectId(objIds.get(i));
                dataContNames.add(dataCN);
            }
        }
        return dataContNames;
    }


    @Override
    public ArrayList<ArrayList<String>> GetObjectInfoByContent(String phone, int userId, String dataContent) throws SQLException {
        int objId = GetObjectIdByParamData(phone, userId, dataContent);
        ArrayList<ArrayList<String>> dataContNames = GetParamDataByObjectId(objId);
        return dataContNames;
    }

    @Override
    public ArrayList<ArrayList<ArrayList<String>>> GetObjectsInfoByParentObjId(int objId) throws SQLException {
        ArrayList<Integer> obIds = GetObjectIdsByParentId(objId);
        ArrayList<ArrayList<ArrayList<String>>> dataContNamesForObjs = new ArrayList<>();
        for (int i = 0; i < obIds.size(); i++) {
            ArrayList<ArrayList<String>> inheritedDataContNames = GetParamDataByObjectId(obIds.get(i));
            dataContNamesForObjs.add(inheritedDataContNames);
        }
        return dataContNamesForObjs;
    }

    @Override
    public ArrayList<ArrayList<ArrayList<ArrayList<String>>>> GetObjectsInfoByParentObjIds(ArrayList<Integer> obIds) throws SQLException {

        ArrayList<ArrayList<Integer>> inheritedObIds = new ArrayList<>();
        for (int i = 0; i < obIds.size(); i++) {
            ArrayList<Integer> inheritedObId = GetObjectIdsByParentId(obIds.get(i));
            inheritedObIds.add(inheritedObId);
        }

        ArrayList<ArrayList<ArrayList<ArrayList<String>>>> dataContNamesForObjsList = new ArrayList<>();
        for (int i = 0; i < inheritedObIds.size(); i++) {
            ArrayList<ArrayList<ArrayList<String>>> dataContNamesForObjs = new ArrayList<>();
            for (int j = 0; j < inheritedObIds.get(i).size(); j++) {
                ArrayList<ArrayList<String>> inheritedDataContNames = GetParamDataByObjectId(inheritedObIds.get(i).get(j));
                dataContNamesForObjs.add(inheritedDataContNames);
            }
            dataContNamesForObjsList.add(dataContNamesForObjs);
        }
        return dataContNamesForObjsList;
    }


    @Override
    public String CreateObject(String objectName, String parentId) throws SQLException {
        String result = "successful";
        if((parentId != null) && (objectName!=null)){
            if((parentId.equals("none")) || (parentId.equals("0"))){
                Objects objects = new Objects(0, objectName);
                create(objects);
            }else{
                int parentIdInt = Integer.parseInt(parentId);
                Objects objects = new Objects(parentIdInt, objectName);
                create(objects);
            }
        }else{
            result = null;
        }
        return result;
    }


}
