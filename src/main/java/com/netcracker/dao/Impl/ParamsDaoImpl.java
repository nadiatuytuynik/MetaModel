package com.netcracker.dao.Impl;

import com.netcracker.dao.Impl.AbstractDaoImpl;
import com.netcracker.dao.ParamsDao;
import com.netcracker.model.Params;
import com.netcracker.util.DBManager;

import java.sql.*;

public class ParamsDaoImpl extends AbstractDaoImpl<Params> implements ParamsDao {

    @Override
    protected void fillCreateStatement(PreparedStatement ps, Params entity) {
        try {
            ps.setString(1, entity.getParam_name());
            ps.setInt(2, entity.getObject_id());
            ps.setInt(3, entity.getType_id());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected Params getEntity(ResultSet rs) {
        try {
            return new Params(rs);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected String[] getGeneratedColumn() {
        return new String[]{"param_id"};
    }

    @Override
    protected String getFindOneQuery() {
        return this.dbManager.getQuery("find.params");
    }

    @Override
    protected String getCreateQuery() {
        return this.dbManager.getQuery("create.params");
    }

    @Override
    public Integer[] GetMaxMinParamIdByObjectId(int objId) {
        Integer[] maxMinParamIds = new Integer[2];
        try {
            DBManager dbManager = DBManager.getInstance();
            Connection connection = dbManager.getConnection();
            CallableStatement getMaxMinParamIdByObjectId = connection.prepareCall("{call GetMaxMinParamIdByObjectId(?,?,?)}");
            getMaxMinParamIdByObjectId.setInt(1, objId);
            getMaxMinParamIdByObjectId.registerOutParameter(2, Types.INTEGER);
            getMaxMinParamIdByObjectId.registerOutParameter(3, Types.INTEGER);
            getMaxMinParamIdByObjectId.executeQuery();
            int maxParamId = (int) getMaxMinParamIdByObjectId.getObject(2);
            int minParamId = (int) getMaxMinParamIdByObjectId.getObject(3);
            getMaxMinParamIdByObjectId.close();

            if(maxParamId!=0) {
                maxMinParamIds[0] = maxParamId;
                maxMinParamIds[1] = minParamId;
            }else{
                maxParamId = 0;
                minParamId = 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return maxMinParamIds;
    }

    @Override
    public Object[] GetParamIdByObjectId(int objId, int paramId) {
        Object[] obj = new Object[2];
        try {
            DBManager dbManager = DBManager.getInstance();
            Connection connection = dbManager.getConnection();
            CallableStatement getParamIdByObjectId = connection.prepareCall("{call GetParamIdByObjectId(?,?,?,?)}");
            getParamIdByObjectId.setInt(1, objId);
            getParamIdByObjectId.setInt(2, paramId);
            getParamIdByObjectId.registerOutParameter(3, Types.INTEGER);
            getParamIdByObjectId.registerOutParameter(4, Types.VARCHAR);
            getParamIdByObjectId.executeQuery();
            int returnedParamId = (int) getParamIdByObjectId.getObject(3);
            String paramName = (String) getParamIdByObjectId.getObject(4);
            getParamIdByObjectId.close();

            if(returnedParamId!=0) {
                obj[0] = returnedParamId;
                obj[1] = paramName;
            }else{
                obj[0] = 0;
                obj[1] = null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return obj;
    }


    @Override
    public Object[] GetParamIdNameByObjectName(String objName, int testParamId) {
        Object[] paramIdName = new Object[2];
        try {
            DBManager dbManager = DBManager.getInstance();
            Connection connection = dbManager.getConnection();
            CallableStatement getParams = connection.prepareCall("{call GetParams(?,?,?,?)}");
            getParams.setString(1, objName);
            getParams.setInt(2, testParamId);
            getParams.registerOutParameter(3, Types.INTEGER);
            getParams.registerOutParameter(4, Types.VARCHAR);
            getParams.executeQuery();
            int paramId = (int) getParams.getObject(3);
            String paramName = (String) getParams.getObject(4);
            getParams.close();

            if(paramId!=0) {
                paramIdName[0] = paramId;
                paramIdName[1] = paramName;
            }else{
                paramIdName[0] = 0;
                paramIdName[1] = null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return paramIdName;
    }



}
