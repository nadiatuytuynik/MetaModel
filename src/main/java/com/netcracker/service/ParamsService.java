package com.netcracker.service;

import com.netcracker.model.Params;

import java.sql.SQLException;
import java.util.ArrayList;

public interface ParamsService {

    Params create(Params params);

    Integer[] GetMaxMinParamIdByObjectId(int objId) throws SQLException;

    Object[] GetParamIdNameByObjectId(int objId, int paramId) throws SQLException;

    ArrayList<ArrayList<String>> GetParamIdsNamesByObjectId(int objId) throws SQLException;

    String CreateParam(String attribute, String object_id, String type) throws SQLException;

}
