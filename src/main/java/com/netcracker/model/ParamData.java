package com.netcracker.model;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ParamData {
    private int param_data_id;
    private String param_data_content;
    private int param_id;

    public ParamData() {
    }

    public ParamData(String param_data_content, int param_id) {
        this.param_data_content = param_data_content;
        this.param_id = param_id;
    }

    public ParamData(ResultSet rs) throws SQLException{
        this.param_data_id = rs.getInt("param_data_id");
        this.param_data_content = rs.getString("param_data_content");
        this.param_id = rs.getInt("param_id");
    }

    public int getParam_data_id() {
        return param_data_id;
    }

    public void setParam_data_id(int param_data_id) {
        this.param_data_id = param_data_id;
    }

    public String getParam_data_content() {
        return param_data_content;
    }

    public void setParam_data_content(String param_data_content) {
        this.param_data_content = param_data_content;
    }

    public int getParam_id() {
        return param_id;
    }

    public void setParam_id(int param_id) {
        this.param_id = param_id;
    }

    @Override
    public String toString() {
        return "ParamData{" +
                "param_data_id=" + param_data_id +
                ", param_data_content='" + param_data_content + '\'' +
                ", param_id=" + param_id +
                '}';
    }
}
