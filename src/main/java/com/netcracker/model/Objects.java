package com.netcracker.model;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Objects {
    private int object_id;
    private int parent_id;
    private String object_name;

    public Objects(){
    }

    public Objects(int parent_id, String object_name){
        this.parent_id = parent_id;
        this.object_name = object_name;
    }

    public Objects(ResultSet rs) throws SQLException {
        this.object_id = rs.getInt("object_id");
        this.parent_id = rs.getInt("parent_id");
        this.object_name = rs.getString("object_name");
    }

    public int getObject_id() {
        return object_id;
    }

    public void setObject_id(int object_id) {
        this.object_id = object_id;
    }

    public int getParent_id() {
        return parent_id;
    }

    public void setParent_id(int parent_id) {
        this.parent_id = parent_id;
    }

    public String getObject_name() {
        return object_name;
    }

    public void setObject_name(String object_name) {
        this.object_name = object_name;
    }

    @Override
    public String toString() {
        return "Objects{" +
                "object_id=" + object_id +
                ", parent_id=" + parent_id +
                ", object_name='" + object_name + '\'' +
                '}';
    }
}
