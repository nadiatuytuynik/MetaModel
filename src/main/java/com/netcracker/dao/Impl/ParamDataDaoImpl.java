package com.netcracker.dao.Impl;

import com.netcracker.dao.Impl.AbstractDaoImpl;
import com.netcracker.dao.ParamDataDao;
import com.netcracker.model.ParamData;
import com.netcracker.util.DBManager;

import java.sql.*;

public class ParamDataDaoImpl extends AbstractDaoImpl<ParamData> implements ParamDataDao {

    @Override
    protected void fillCreateStatement(PreparedStatement ps, ParamData entity) {
        try {
            ps.setString(1, entity.getParam_data_content());
            ps.setInt(2, entity.getParam_id());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected ParamData getEntity(ResultSet rs) {
        try {
            return new ParamData(rs);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected String[] getGeneratedColumn() {
        return new String[]{"param_data_id"};
    }

    @Override
    protected String getFindOneQuery() {
        return this.dbManager.getQuery("find.paramData");
    }

    @Override
    protected String getCreateQuery() {
        return this.dbManager.getQuery("create.paramData");
    }


    @Override
    public String GetParamDataContByParamId (int paramId) throws SQLException {
        String dataContent = "";
        try {
            DBManager dbManager = DBManager.getInstance();
            Connection connection = dbManager.getConnection();
            CallableStatement getParamDataByParamId2 = connection.prepareCall("{call GetParamDataByParamId(?,?)}");
            getParamDataByParamId2.setInt(1, paramId);
            getParamDataByParamId2.registerOutParameter(2, Types.VARCHAR);
            getParamDataByParamId2.executeQuery();
            String data = (String) getParamDataByParamId2.getObject(2);
            getParamDataByParamId2.close();

            connection.close();
            dataContent = data;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dataContent;
    }

    @Override
    public void UpdateParamDataByParamId (int paramId, String paramData) throws SQLException {
        try {
            DBManager dbManager = DBManager.getInstance();
            Connection connection = dbManager.getConnection();
            CallableStatement getMaxMinObjectId = connection.prepareCall("{call UpdateParamData(?,?)}");
            getMaxMinObjectId.setInt(1, paramId);
            getMaxMinObjectId.setString(2, paramData);
            getMaxMinObjectId.executeQuery();
            getMaxMinObjectId.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int GetParamIdByParamNameAndObjId(int objId, String paramName) throws SQLException {
        int paramId = 0;
        try {
            DBManager dbManager = DBManager.getInstance();
            Connection connection = dbManager.getConnection();
            CallableStatement getParamId = connection.prepareCall("{call GetParamId(?,?,?)}");
            getParamId.setInt(1, objId);
            getParamId.setString(2, paramName);
            getParamId.registerOutParameter(3, Types.INTEGER);
            getParamId.executeQuery();
            int returnedParamId = (int) getParamId.getObject(3);
            getParamId.close();
            connection.close();
            paramId = returnedParamId;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return paramId;
    }


}
