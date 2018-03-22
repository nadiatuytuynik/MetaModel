package com.netcracker.dao.Impl;

import com.netcracker.dao.AbstractDao;
import com.netcracker.util.DBManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class AbstractDaoImpl<T> implements AbstractDao<T, Long> {
    DBManager dbManager = DBManager.getInstance();

        @Override
        public T create(T entity) {
            try(Connection connection = this.dbManager.getConnection();
                PreparedStatement ps = this.createPreparedStatement(entity, connection);
                ResultSet rs = ps.getGeneratedKeys()){

                if (rs.next()){
                    Long generatedID = rs.getLong(1);
                    return this.findOne(generatedID);
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        public T findOne(Long ID) {
            try(Connection connection = this.dbManager.getConnection();
                 PreparedStatement ps = this.findOnePreparedStatement(ID, connection);
                 ResultSet rs = ps.executeQuery()){

                if (rs.next()) {
                    return this.getEntity(rs);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return null;
        }

        private PreparedStatement createPreparedStatement(T entity, Connection connection) throws SQLException {
            String query = this.getCreateQuery();
            String[] generatedColumn = this.getGeneratedColumn();
            PreparedStatement ps = connection.prepareStatement(query, generatedColumn);
            this.fillCreateStatement(ps,entity);

            ps.executeUpdate();
            return ps;
        }

        private PreparedStatement findOnePreparedStatement(Long id, Connection connection) throws SQLException {
            String query = this.getFindOneQuery();
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setLong(1, id);

            return ps;
        }

        protected abstract String[] getGeneratedColumn();

        protected abstract void fillCreateStatement(PreparedStatement ps, T entity);

        protected abstract String getCreateQuery();

        protected abstract T getEntity(ResultSet rs);

        protected abstract String getFindOneQuery();


}


