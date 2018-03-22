package com.netcracker.service;

import com.netcracker.model.ParamData;

import java.sql.SQLException;

public interface ParamDataService {

    ParamData create(ParamData paramData);

    String GetParamDataByParamId(int paramId) throws SQLException;

    String CreateOrUpdateParamData(int objId, int paramId, String dataForUpdate) throws SQLException;

    String UpdateParamDataByObjectIdAndBool(int objId, String objName, String paramNameForUpd, String paramDataForUpd, String checkbox) throws SQLException;

    Object[] GetParamDataManyToManyObjects(String objName1, int objId2, String checkbox, String paramId1, String paramId2) throws SQLException;

}

