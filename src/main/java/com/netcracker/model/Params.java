package com.netcracker.model;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Params {
    private int param_id;
    private String param_name;
    private int object_id;
    private int type_id;

    public Params() {
    }

    public Params(String param_name, int object_id, int type_id) {
        this.param_name = param_name;
        this.object_id = object_id;
        this.type_id = type_id;
    }

    public Params(ResultSet rs) throws SQLException{
        this.param_id = rs.getInt("param_id");
        this.param_name = rs.getString("param_name");
        this.object_id = rs.getInt("object_id");
        this.type_id = rs.getInt("type_id");
    }

    public int getParam_id() {
        return param_id;
    }

    public void setParam_id(int param_id) {
        this.param_id = param_id;
    }

    public String getParam_name() {
        return param_name;
    }

    public void setParam_name(String param_name) {
        this.param_name = param_name;
    }

    public int getType_id() {
        return type_id;
    }

    public void setType_id(int type_id) {
        this.type_id = type_id;
    }


    public int getObject_id() {
        return object_id;
    }

    public void setObject_id(int object_id) {
        this.object_id = object_id;
    }

    @Override
    public String toString() {
        return "Params{" +
                "param_id=" + param_id +
                ", param_name='" + param_name + '\'' +
                ", object_id=" + object_id +
                ", type_id=" + type_id +
                '}';
    }
}
