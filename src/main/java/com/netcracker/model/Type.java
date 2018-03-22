package com.netcracker.model;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Type {
    private int type_id;
    private String type_name;

    public Type() {
    }

    public Type(String type_name) {
        this.type_name = type_name;
    }

    public Type(ResultSet rs) throws SQLException{
        this.type_id = rs.getInt("type_id");
        this.type_name = rs.getString("type_name");
    }

    public int getType_id() {
        return type_id;
    }

    public void setType_id(int type_id) {
        this.type_id = type_id;
    }

    public String getType_name() {
        return type_name;
    }

    public void setType_name(String type_name) {
        this.type_name = type_name;
    }

    @Override
    public String toString() {
        return "Type{" +
                "type_id=" + type_id +
                ", type_name='" + type_name + '\'' +
                '}';
    }
}
