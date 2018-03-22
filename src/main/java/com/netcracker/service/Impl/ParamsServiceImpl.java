package com.netcracker.service.Impl;

import com.netcracker.dao.ParamsDao;
import com.netcracker.dao.Impl.ParamsDaoImpl;
import com.netcracker.model.Params;
import com.netcracker.service.ParamsService;

import java.sql.SQLException;
import java.util.ArrayList;

public class ParamsServiceImpl implements ParamsService {

    private ParamsDao paramsDao;

    public ParamsServiceImpl(){ this.paramsDao = new ParamsDaoImpl();
    }

    @Override
    public Params create(Params params) {
        return this.paramsDao.create(params);
    }
    // assert not null (user) -> throw exception

    @Override
    public Integer[] GetMaxMinParamIdByObjectId(int objId) throws SQLException {
        return paramsDao.GetMaxMinParamIdByObjectId(objId);
    }

    @Override
    public Object[] GetParamIdNameByObjectId(int objId, int paramId) throws SQLException {
        return paramsDao.GetParamIdByObjectId(objId,paramId);
    }

    @Override
    public ArrayList<ArrayList<String>> GetParamIdsNamesByObjectId(int objId) throws SQLException {
        Integer[] maxMinParamIds = GetMaxMinParamIdByObjectId(objId);
        int maxParamId = maxMinParamIds[0];
        int minParamId = maxMinParamIds[1];
        ArrayList<ArrayList<String>> paramIdNames = new ArrayList<>();
        ArrayList<String> paramIds = new ArrayList<>();
        ArrayList<String> paramNames = new ArrayList<>();
        for (int i = minParamId; i <=maxParamId ; i++) {
            Object[] returnedParamIdName = GetParamIdNameByObjectId(objId, i);
            int paramId = (int)returnedParamIdName[0];
            String paramName = (String)returnedParamIdName[1];
            if(paramId!=0) {
                String paramIdStr = Integer.toString(paramId);
                paramIds.add(paramIdStr);
                paramNames.add(paramName);
            }
        }
        paramIdNames.add(paramNames);
        paramIdNames.add(paramIds);

        return paramIdNames;
    }

    @Override
    public String CreateParam(String attribute, String object_id, String type) throws SQLException {
        String result = "successful";
        if((attribute!=null) && (object_id!=null) && (type!=null)){
            int objectId = Integer.parseInt(object_id);
            int typeId = Integer.parseInt(type);

            Params params1 = new Params(attribute,objectId,typeId);
            create(params1);
        }else{
            result = null;
        }
        return result;
    }



}
