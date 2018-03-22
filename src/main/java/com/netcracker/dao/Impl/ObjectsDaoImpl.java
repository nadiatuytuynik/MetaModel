package com.netcracker.dao.Impl;

import com.netcracker.dao.Impl.AbstractDaoImpl;
import com.netcracker.dao.ObjectsDao;
import com.netcracker.model.Objects;
import com.netcracker.util.DBManager;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ObjectsDaoImpl extends AbstractDaoImpl<Objects> implements ObjectsDao {

    @Override
    protected void fillCreateStatement(PreparedStatement ps, Objects entity) {
        try {
            ps.setInt(1, entity.getParent_id());
            ps.setString(2, entity.getObject_name());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected Objects getEntity(ResultSet rs) {
        try {
            return new Objects(rs);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected String[] getGeneratedColumn() {
        return new String[]{"object_id"};
    }

    @Override
    protected String getFindOneQuery() {
        return this.dbManager.getQuery("find.objects");
    }

    @Override
    protected String getCreateQuery() {
        return this.dbManager.getQuery("create.objects");
    }

    @Override
    public Integer[] GetMaxMinObjectId(String objectName) throws SQLException {
        Integer[] maxMinValues = new Integer[2];
        DBManager dbManager = DBManager.getInstance();
        Connection connection = null;
        CallableStatement getMaxObjectId = null;
        try {
            connection = dbManager.getConnection();

            getMaxObjectId = connection.prepareCall("{call GetMaxMinObjectId(?,?,?)}");
            getMaxObjectId.setString(1, objectName);
            getMaxObjectId.registerOutParameter(2, Types.INTEGER);
            getMaxObjectId.registerOutParameter(3, Types.INTEGER);
            getMaxObjectId.executeQuery();

            int maxobjectId = (int) getMaxObjectId.getObject(2);
            int minobjectId = (int) getMaxObjectId.getObject(3);

            maxMinValues[0] = maxobjectId;
            maxMinValues[1] = minobjectId;

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            assert getMaxObjectId != null;
            getMaxObjectId.close();
            connection.close();
        }
        return maxMinValues;
    }

    @Override
    public List<Integer> GetParamId(ArrayList<String> listAttributes, Integer objectId) throws SQLException {
        List<Integer> paramIds = new ArrayList<>();
        Connection connection = null;
        for (String listAttribute : listAttributes) {
            CallableStatement getMaxParamId;
            try {
                connection = dbManager.getConnection();
                getMaxParamId = connection.prepareCall("{call GetParamId(?,?,?)}");
                getMaxParamId.setInt(1, objectId);
                getMaxParamId.setString(2, listAttribute);
                getMaxParamId.registerOutParameter(3, Types.INTEGER);
                getMaxParamId.executeQuery();
                int maxParamId = (int) getMaxParamId.getObject(3);
                getMaxParamId.close();
                paramIds.add(maxParamId);
            } catch (SQLException e) {
                e.printStackTrace();
            }finally {
                assert connection != null;
                connection.close();
            }
        }
        return paramIds;
    }

    @Override
    public Integer GetObjectIdByParamData(String phone, int userId, String projectNumber) throws SQLException {
        int objId = 0;
        try {
            DBManager dbManager = DBManager.getInstance();
            Connection connection = dbManager.getConnection();

            CallableStatement getObjectIdByContent = connection.prepareCall("{call GetObjectIdByContent(?,?,?,?)}");
            getObjectIdByContent.setString(1, phone);
            getObjectIdByContent.setInt(2, userId);
            getObjectIdByContent.setString(3, projectNumber);
            getObjectIdByContent.registerOutParameter(4, Types.INTEGER);
            getObjectIdByContent.executeQuery();
            int projId = (int) getObjectIdByContent.getObject(4);
            getObjectIdByContent.close();
            objId = projId;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return objId;
    }

    @Override
    public Integer[] GetMaxMinObjectIdByParentId(int parentId) throws SQLException {
        Integer[] maxMinObjectIds = new Integer[2];
        try {
            DBManager dbManager = DBManager.getInstance();
            Connection connection = dbManager.getConnection();

            CallableStatement getMaxMinObjectIdByParentId = connection.prepareCall("{call GetMaxMinObjectIdByParentId(?,?,?)}");
            getMaxMinObjectIdByParentId.setInt(1, parentId);
            getMaxMinObjectIdByParentId.registerOutParameter(2, Types.INTEGER);
            getMaxMinObjectIdByParentId.registerOutParameter(3, Types.INTEGER);
            getMaxMinObjectIdByParentId.executeQuery();
            int maxObjectId = (int) getMaxMinObjectIdByParentId.getObject(2);
            int minObjectId = (int) getMaxMinObjectIdByParentId.getObject(3);
            getMaxMinObjectIdByParentId.close();
            maxMinObjectIds[0] = maxObjectId;
            maxMinObjectIds[1] = minObjectId;
            connection.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return maxMinObjectIds;
    }

    @Override
    public int GetObjectIdByParentId(int parentId, int testObjectId) throws SQLException {
        int objectId = 0;
        try {
            DBManager dbManager = DBManager.getInstance();
            Connection connection = dbManager.getConnection();
            CallableStatement getObjectIdByParentId = connection.prepareCall("{call GetObjectIdByParentId(?,?,?)}");
            getObjectIdByParentId.setInt(1, parentId);
            getObjectIdByParentId.setInt(2, testObjectId);
            getObjectIdByParentId.registerOutParameter(3, Types.INTEGER);
            getObjectIdByParentId.executeQuery();
            int objectIdValue = (int) getObjectIdByParentId.getObject(3);
            getObjectIdByParentId.close();

            objectId = objectIdValue;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return objectId;
    }

    @Override
    public int GetObjectIdByName(String objectName, int testObjectId) throws SQLException {
        int objectId = 0;
        try {
            DBManager dbManager = DBManager.getInstance();
            Connection connection = dbManager.getConnection();
            CallableStatement getObjectIdByName = connection.prepareCall("{call GetObjectIdByName(?,?,?)}");
            getObjectIdByName.setString(1, objectName);
            getObjectIdByName.setInt(2, testObjectId);
            getObjectIdByName.registerOutParameter(3, Types.INTEGER);
            getObjectIdByName.executeQuery();
            int returnedObjectId = (int) getObjectIdByName.getObject(3);
            getObjectIdByName.close();

            objectId = returnedObjectId;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return objectId;
    }

    @Override
    public String GetObjectNameById(int objId) throws SQLException {
        String objectName = "";
        try {
            DBManager dbManager = DBManager.getInstance();
            Connection connection = dbManager.getConnection();
            CallableStatement getObjectIdByName = connection.prepareCall("{call GetObjectNameById(?,?)}");
            getObjectIdByName.setInt(1, objId);
            getObjectIdByName.registerOutParameter(2, Types.VARCHAR);
            getObjectIdByName.executeQuery();
            String returnedObjectName = (String) getObjectIdByName.getObject(2);
            getObjectIdByName.close();

            objectName = returnedObjectName;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return objectName;
    }


}


