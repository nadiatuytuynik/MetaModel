package com.netcracker.service.Impl;

import com.netcracker.dao.TypeDao;
import com.netcracker.dao.Impl.TypeDaoImpl;
import com.netcracker.model.Type;
import com.netcracker.service.TypeService;

import java.sql.SQLException;
import java.util.ArrayList;

public class TypeServiceImpl implements TypeService {

    private TypeDao typeDao;

    public TypeServiceImpl(){ this.typeDao = new TypeDaoImpl();
    }

    @Override
    public Type create(Type type) {
        return this.typeDao.create(type);
    }
    // assert not null (user) -> throw exception

    @Override
    public ArrayList<Integer> ChangeArrTypeStrToInt(ArrayList<String> arr) throws SQLException {
        ArrayList<Integer> intList = new ArrayList<>();
        for (int i = 0; i <arr.size() ; i++) {
            int newArr = Integer.parseInt(arr.get(i));
            intList.add(newArr);
        }
        return intList;
    }


}
