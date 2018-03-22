package com.netcracker.dao.Impl;

import com.netcracker.dao.Impl.AbstractDaoImpl;
import com.netcracker.dao.TypeDao;
import com.netcracker.model.Type;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TypeDaoImpl extends AbstractDaoImpl<Type> implements TypeDao {

    @Override
    protected void fillCreateStatement(PreparedStatement ps, Type entity) {
        try {
            ps.setString(1, entity.getType_name());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected Type getEntity(ResultSet rs) {
        try {
            return new Type(rs);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected String[] getGeneratedColumn() {
        return new String[]{"type_id"};
    }

    @Override
    protected String getFindOneQuery() {
        return this.dbManager.getQuery("find.type");
    }

    @Override
    protected String getCreateQuery() {
        return this.dbManager.getQuery("create.type");
    }

}
