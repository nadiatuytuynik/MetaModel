package com.netcracker.service.Impl;

import com.netcracker.dao.ParamDataDao;
import com.netcracker.dao.Impl.ParamDataDaoImpl;
import com.netcracker.dao.ParamsDao;
import com.netcracker.dao.Impl.ParamsDaoImpl;
import com.netcracker.model.ParamData;
import com.netcracker.service.ObjectsService;
import com.netcracker.service.ParamDataService;
import com.netcracker.service.ParamsService;

import java.sql.SQLException;
import java.util.ArrayList;

public class ParamDataServiceImpl implements ParamDataService {

    private ParamDataDao paramDataDao;
    private ParamsService paramsService;
    private ParamsDao paramsDao;
    private ObjectsService objectsService;
    public ParamDataServiceImpl(){
        this.paramDataDao = new ParamDataDaoImpl();
        this.paramsService = new ParamsServiceImpl();
        this.paramsDao = new ParamsDaoImpl();
        this.objectsService = new ObjectsServiceImpl();
    }

    @Override
    public ParamData create(ParamData paramData) {
        return this.paramDataDao.create(paramData);
    }

    @Override
    public String GetParamDataByParamId(int paramId) throws SQLException {
        paramDataDao.GetParamDataContByParamId(paramId);
        return paramDataDao.GetParamDataContByParamId(paramId);
    }


    @Override
    public String UpdateParamDataByObjectIdAndBool(int objId, String objName, String paramNameForUpd, String paramDataForUpd, String checkbox) throws SQLException {
        String status = null;
        if(checkbox!=null) {
            Integer[] maxMinParamIds = paramsService.GetMaxMinParamIdByObjectId(objId);
            int maxParamId = maxMinParamIds[0];
            int minParamId = maxMinParamIds[1];
            ArrayList<Integer> taskParams = new ArrayList<>();
            ArrayList<String> taskParamNames = new ArrayList<>();
            for (int i = minParamId; i <= maxParamId; i++) {
                Object[] paramIdName = paramsDao.GetParamIdNameByObjectName(objName, i);
                int paramId = (int) paramIdName[0];
                String paramName = (String) paramIdName[1];
                if (paramId != 0) {
                    taskParams.add(paramId);
                    taskParamNames.add(paramName);
                }
            }

            int position = -1;
            for (int i = 0; i < taskParamNames.size(); i++) {
                if (taskParamNames.get(i).equals(paramNameForUpd)) {
                    position = i;
                }
            }

            paramDataDao.UpdateParamDataByParamId(taskParams.get(position), paramDataForUpd);
            status = "successful";

        }else{
            status = null;
        }
        return status;
    }

    @Override
    public Object[] GetParamDataManyToManyObjects(String objName1, int objId2, String checkbox, String paramId1, String paramId2) throws SQLException {
        String status = null;
        Integer[] maxMinObjectId = objectsService.GetMaxMinObjectIdByName(objName1);
        int maxObjectId = maxMinObjectId[0];
        int minObjectId = maxMinObjectId[1];
        ArrayList<Integer> objIds = new ArrayList<>();
        for (int i = minObjectId; i <= maxObjectId; i++) {
            int objId = objectsService.GetObjectIdByName(objName1,i);
            if (objId != 0) {
                objIds.add(objId);
            }
        }

        ArrayList<ArrayList<ArrayList<String>>> dataContNames = new ArrayList<>();
        for (int i = 0; i <objIds.size() ; i++) {
            ArrayList<ArrayList<String>> dataCN = objectsService.GetParamDataByObjectId(objIds.get(i));
            dataContNames.add(dataCN);
        }

        ArrayList<ArrayList<String>> dataNames = new ArrayList<>();
        for (int i = 0; i <dataContNames.size() ; i++) {
            ArrayList<String> names = dataContNames.get(i).get(0);
            dataNames.add(names);
        }

        ArrayList<ArrayList<String>> dataCont = new ArrayList<>();
        for (int i = 0; i <dataContNames.size() ; i++) {
            ArrayList<String> conts = dataContNames.get(i).get(1);
            dataCont.add(conts);
        }

        ArrayList<Integer> positions = new ArrayList<>();
        for (int i = 0; i < dataNames.size(); i++) {
            for (int j = 0; j < dataNames.get(i).size(); j++) {
                if ((dataNames.get(i).get(j)).equals(paramId1)) {
                    String empStr = dataCont.get(i).get(j);
                    int emp_id = Integer.parseInt(empStr);
                    if (emp_id == objId2) {
                        positions.add(i);
                    }
                }
            }
        }
        ArrayList<Integer> taskIds = new ArrayList<>();
        for (Integer position : positions) {
            for (int j = 0; j < dataNames.get(position).size(); j++) {
                if ((dataNames.get(position).get(j)).equals(paramId2)) {
                    int task_id = Integer.parseInt(dataCont.get(position).get(j));
                    taskIds.add(task_id);
                }
            }
        }

        ArrayList<ArrayList<ArrayList<String>>> inheretedDataContNames = new ArrayList<>();
        for (int i = 0; i < taskIds.size(); i++) {
            ArrayList<ArrayList<String>> inheretedDataCN = objectsService.GetParamDataByObjectId(taskIds.get(i));
            inheretedDataContNames.add(inheretedDataCN);
        }

        Object[] result = new Object[2];
        result[0] = taskIds;
        result[1] = inheretedDataContNames;

        return result;
    }


    @Override
    public String CreateOrUpdateParamData(int objId, int paramId, String dataForUpdate) throws SQLException {
        String result = "successful";
        Object[] returnedParamIdName = objectsService.GetParamIdByObjectId(objId,paramId);
        int returnedParamId = (int)returnedParamIdName[0];
        System.out.println(returnedParamId);
        if(returnedParamId!=0){
            String paramData = GetParamDataByParamId(returnedParamId);
            System.out.println("paramData = " + paramData);
            if(!paramData.equals("none")){
                paramDataDao.UpdateParamDataByParamId(returnedParamId, dataForUpdate);
            }else{
                ParamData paramD = new ParamData(dataForUpdate,returnedParamId);
                this.paramDataDao.create(paramD);
            }
        }else{
            result = null;
        }
        return result;
    }


}
