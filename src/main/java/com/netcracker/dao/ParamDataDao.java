package com.netcracker.dao;

import com.netcracker.model.ParamData;

import java.sql.SQLException;

public interface ParamDataDao extends AbstractDao<ParamData,Long> {

    String GetParamDataContByParamId (int paramId) throws SQLException;

    void UpdateParamDataByParamId (int paramId, String paramData) throws SQLException;

    int GetParamIdByParamNameAndObjId(int objId, String paramName) throws SQLException;

}
