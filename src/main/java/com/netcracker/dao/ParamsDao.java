package com.netcracker.dao;

import com.netcracker.model.Params;

import java.sql.SQLException;

public interface ParamsDao extends AbstractDao<Params,Long> {

    Integer[] GetMaxMinParamIdByObjectId (int objId) throws SQLException;

    Object[] GetParamIdByObjectId (int objId, int paramId) throws SQLException;

    Object[] GetParamIdNameByObjectName(String objName, int testParamId);
}
