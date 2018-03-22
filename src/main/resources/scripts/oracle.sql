drop table Param_data;
drop table Params;
drop table Objects;
drop table Type;

drop SEQUENCE objects_seq;
drop SEQUENCE type_seq;
drop SEQUENCE params_seq;
drop SEQUENCE param_data_seq;

drop TRIGGER objects_trigger;
drop TRIGGER type_trigger;
drop TRIGGER params_trigger;
drop TRIGGER param_data_trigger;

create table Objects(
  object_id NUMBER,
  parent_id NUMBER,
  object_name VARCHAR(20),
  CONSTRAINT object_pk PRIMARY KEY(object_id)
);

create table Type(
  type_id NUMBER,
  type_name VARCHAR(20),
  CONSTRAINT type_pk PRIMARY KEY(type_id)
);

create table Params(
  param_id NUMBER,
  object_id NUMBER,
  param_name VARCHAR(20),
  type_id NUMBER,
  CONSTRAINT param_pk PRIMARY KEY(param_id),
  CONSTRAINT type_fk FOREIGN KEY (type_id)
  REFERENCES Type,
  CONSTRAINT object_fk FOREIGN KEY (object_id)
  REFERENCES Objects
);

create table Param_data(
  param_data_id NUMBER,
  param_data_content VARCHAR(40),
  param_id NUMBER,
  CONSTRAINT param_data_pk PRIMARY KEY(param_data_id),
  CONSTRAINT param2_fk FOREIGN KEY (param_id)
  REFERENCES Params
);

CREATE SEQUENCE objects_seq start with 1 INCREMENT BY 1 NOMAXVALUE ;
CREATE SEQUENCE type_seq start with 1 INCREMENT BY 1 NOMAXVALUE ;
CREATE SEQUENCE params_seq start with 1 INCREMENT BY 1 NOMAXVALUE ;
CREATE SEQUENCE param_data_seq start with 1 INCREMENT BY 1 NOMAXVALUE ;

CREATE or REPLACE TRIGGER objects_trigger
BEFORE INSERT ON Objects
for EACH ROW
  BEGIN
    select objects_seq.nextval
    INTO :new.object_id
    from dual;
  END;

CREATE or REPLACE TRIGGER type_trigger
BEFORE INSERT ON Type
for EACH ROW
  BEGIN
    select type_seq.nextval
    INTO :new.type_id
    from dual;
  END;

CREATE or REPLACE TRIGGER params_trigger
BEFORE INSERT ON Params
for EACH ROW
  BEGIN
    select params_seq.nextval
    INTO :new.param_id
    from dual;
  END;

CREATE or REPLACE TRIGGER param_data_trigger
BEFORE INSERT ON Param_data
for EACH ROW
  BEGIN
    select param_data_seq.nextval
    INTO :new.param_data_id
    from dual;
  END;


CREATE OR REPLACE PROCEDURE GetMaxMinObjectId(objectname IN VARCHAR2, maxobjectid OUT NUMBER, minobjectid OUT NUMBER)AS
k NUMBER;
BEGIN
  SET TRANSACTION ISOLATION LEVEL READ COMMITTED;

  SELECT count(object_id) into k
  from Objects
  where Objects.object_name = objectname;

  IF k != 0 THEN
    SELECT max(object_id) into maxobjectid
    from Objects
    where Objects.object_name = objectname;

    SELECT min(object_id) into minobjectid
    from Objects
    where Objects.object_name = objectname;
  ELSE
    maxobjectid := 0;
    minobjectid := 0;
  END IF;
END GetMaxMinObjectId;


CREATE OR REPLACE PROCEDURE GetCountOfObjects(objectname IN VARCHAR2, objectcount OUT NUMBER)AS
  BEGIN
    SET TRANSACTION ISOLATION LEVEL READ COMMITTED;

    SELECT count(object_id) into objectcount
    from Objects
    where Objects.object_name = objectname;
  END GetCountOfObjects;


CREATE OR REPLACE PROCEDURE GetParamId(objectid IN NUMBER, paramname IN VARCHAR2, paramid OUT NUMBER)AS
k NUMBER;
  BEGIN
    SET TRANSACTION ISOLATION LEVEL READ COMMITTED;

    SELECT count(param_id) into k
    from Params
    where object_id = objectid
      AND param_name = paramname;

    IF (k !=0) THEN
      SELECT param_id into paramid
      from Params
      where object_id = objectid
      AND param_name = paramname;
    ELSE
      paramid :=0;
    END IF;

  END GetParamId;

CREATE OR REPLACE PROCEDURE GetMaxTypeId(typename IN VARCHAR2, paramid OUT NUMBER)AS
  k NUMBER;
  BEGIN
    SET TRANSACTION ISOLATION LEVEL READ COMMITTED;

    SELECT count(type_id) into k
    from Type
    where Type.type_name = typename;

    if(k!=0) THEN
      SELECT type_id into paramid
      from Type
      where Type.type_name = typename;
    else
      paramid := 0;
    END IF;

  END GetMaxTypeId;


CREATE OR REPLACE PROCEDURE GetParams(objectname IN VARCHAR2, paramid IN NUMBER, result OUT NUMBER, paramname OUT VARCHAR2)AS
  k NUMBER;
  BEGIN
    SET TRANSACTION ISOLATION LEVEL READ COMMITTED;

    select count(param_id) into k
      from Params
        where object_id = (select min(object_id)
                            from Objects
                            where Objects.object_name = objectname)
          and param_id = paramid;

   if(k = 1) THEN
     select param_id into result
     from Params
     where object_id = (select min(object_id)
                        from Objects
                        where Objects.object_name = objectname)
           and param_id = paramid;

     select param_name into paramname
     from Params
     where object_id = (select min(object_id)
                        from Objects
                        where Objects.object_name = objectname)
           and param_id = paramid;
   ELSE
     result := 0;
     paramname := 'none';
   END IF;

  END GetParams;

CREATE OR REPLACE PROCEDURE GetMaxMinParamId(objectname IN VARCHAR2, maxparamid OUT NUMBER, minparamid OUT NUMBER)AS
  BEGIN
    SET TRANSACTION ISOLATION LEVEL READ COMMITTED;

    SELECT max(param_id) into maxparamid
    from Params
    where object_id = (select min(object_id)
                       from Objects
                       where Objects.object_name = objectname);

    SELECT min(param_id) into minparamid
    from Params
    where object_id = (select min(object_id)
                       from Objects
                       where Objects.object_name = objectname);
  END GetMaxMinParamId;


CREATE OR REPLACE PROCEDURE LogIn(paramname IN VARCHAR2, paramdata IN VARCHAR2, paramname2 IN VARCHAR2, paramdata2 IN VARCHAR2, result OUT VARCHAR2, status OUT VARCHAR2, object_id OUT NUMBER)AS
  k NUMBER;
  objectid NUMBER;
  paramid NUMBER;
  BEGIN
    SET TRANSACTION ISOLATION LEVEL READ COMMITTED;

    SELECT count(param_id) into k
    FROM PARAM_DATA
    WHERE param_data_content = paramdata;

    IF (k = 1) THEN

      SELECT object_id into objectid
      from Objects
      where object_id = (SELECT object_id
                         from Params
                         WHERE param_id = (SELECT param_id
                                           FROM PARAM_DATA
                                           WHERE param_data_content = paramdata)
                               AND param_name = paramname);

      SELECT param_data_content into result
      from PARAM_DATA
      where param_id = (select param_id
                        from Params
                        where object_id = objectid
                              and param_name = paramname2);

      if(result = paramdata2) THEN
        result := 'successful';
        SELECT param_data_content into status
        from PARAM_DATA
        where param_id = (select param_id
                          from Params
                          where object_id = objectid
                                and param_name = 'Status');

        object_id := objectid;
      ELSE
        result := 'none';
        status := 'none';
        object_id := 0;
      END IF;

    ELSE
      result := 'none';
      status := 'none';
      object_id := 0;
    end if;

  END LogIn;

CREATE OR REPLACE PROCEDURE GetMaxMinParamIdByObjectId(objectid IN VARCHAR2, maxparamid OUT NUMBER, minparamid OUT NUMBER)AS
 k NUMBER;
  BEGIN
    SET TRANSACTION ISOLATION LEVEL READ COMMITTED;

    SELECT count(param_id) into k
    from Params
    where object_id = objectid;

    IF(k!=0) THEN
      SELECT max(param_id) into maxparamid
      from Params
      where object_id = objectid;

      SELECT min(param_id) into minparamid
      from Params
      where object_id = objectid;

      ELSE
        maxparamid:=0;
        minparamid:=0;
    END IF;

  END GetMaxMinParamIdByObjectId;


CREATE OR REPLACE PROCEDURE GetObjectIdByName(objectname IN VARCHAR2, objectid IN NUMBER, result OUT NUMBER)AS
k NUMBER;
  BEGIN
    SET TRANSACTION ISOLATION LEVEL READ COMMITTED;

    SELECT count(object_id) into k
      FROM Objects
        WHERE object_id = objectid
          and object_name = objectname;

    if(k!=0) THEN
      SELECT object_id into result
      FROM Objects
      WHERE object_id = objectid
            and object_name = objectname;
      ELSE
      result := 0;
    END IF;

  END GetObjectIdByName;


CREATE OR REPLACE PROCEDURE GetParamIdByObjectId(objectid IN NUMBER, paramid IN NUMBER, paramid_result OUT NUMBER, paramname OUT VARCHAR2)AS
  k NUMBER;
  BEGIN
    SET TRANSACTION ISOLATION LEVEL READ COMMITTED;

    select count(param_id) into k
    from Params
    where object_id = objectid
          and param_id = paramid;

    if(k != 0) THEN
      select param_id into paramid_result
      from Params
      where object_id = objectid
            and param_id = paramid;

      select param_name into paramname
      from Params
      where object_id = objectid
            and param_id = paramid;
    ELSE
      paramid_result := 0;
      paramname := 'none';
    END IF;

  END GetParamIdByObjectId;


CREATE OR REPLACE PROCEDURE GetParamDataByParamId(paramid IN NUMBER, paramdata OUT VARCHAR2)AS
k NUMBER;
  BEGIN
    SET TRANSACTION ISOLATION LEVEL READ COMMITTED;

    select count(param_data_content) INTO k
      FROM PARAM_DATA
        WHERE param_id = paramid;

    if(k!=0) THEN
      select param_data_content INTO paramdata
      FROM PARAM_DATA
      WHERE param_id = paramid;
      ELSE
      paramdata := 'none';
    END IF;
  END GetParamDataByParamId;

CREATE OR REPLACE PROCEDURE UpdateParamData(paramDataId IN NUMBER, paramData IN VARCHAR)AS
  BEGIN
    SET TRANSACTION ISOLATION LEVEL READ COMMITTED;

    UPDATE PARAM_DATA
      SET param_data_content = paramData
    WHERE param_id = paramDataId;

  END UpdateParamData;


CREATE OR REPLACE PROCEDURE GetObjectIdByContent(phoneNumber IN VARCHAR2, objectId IN NUMBER, paramContent IN VARCHAR2, projId OUT NUMBER)AS
  k NUMBER;
  k2 NUMBER;
  parentId NUMBER;
  customerId NUMBER;
  BEGIN
    SET TRANSACTION ISOLATION LEVEL READ COMMITTED;

    select count(object_id) into k
    from Objects
    where object_id = (select object_id
                        from Params
                        where param_id =  (SELECT param_id
                                           FROM Param_data
                                           where param_data_content = paramContent)
                        and param_name = 'Project_number')
    AND parent_id = objectId;

    select count(object_id) INTO k2
    from OBJECTS
    where object_id = (select object_id
                       from Params
                       where param_id = (SELECT param_id
                                         from Param_data
                                         where param_data_content = phoneNumber));

     if(k!=0) THEN
       if(k2!=0) then
         select parent_id into parentId
         from Objects
         where object_id = (select object_id
                            from Params
                            where param_id =  (SELECT param_id
                                               FROM Param_data
                                               where param_data_content = paramContent)
                            and param_name = 'Project_number')
         AND parent_id = objectId;

         select object_id INTO customerId
         from OBJECTS
         where object_id = (select object_id
                            from Params
                            where param_id = (SELECT param_id
                                              from Param_data
                                              where param_data_content = phoneNumber));

         if(parentId = customerId) THEN
           select object_id into projId
           from Objects
           where object_id = (select object_id
                              from Params
                              where param_id =  (SELECT param_id
                                                 FROM Param_data
                                                 where param_data_content = paramContent)
                                    and param_name = 'Project_number')
                 AND parent_id = objectId;
         else
            projId :=0;
         END IF;

        ELSE
          projId :=0;
        end if;
     ELSE
       projId :=0;
     END IF;

  END GetObjectIdByContent;


CREATE OR REPLACE PROCEDURE GetMaxMinObjectIdByParentId(parentId IN NUMBER, maxObjectId OUT NUMBER, minObjectId OUT NUMBER)AS
  k NUMBER;
  BEGIN
    SET TRANSACTION ISOLATION LEVEL READ COMMITTED;

    select count(object_id) INTO k
    from Objects
    where parent_id = parentId;

    IF(k!=0) THEN
      select max(object_id) INTO maxObjectId
      from Objects
      where parent_id = parentId;

      select min(object_id) INTO minObjectId
      from Objects
      where parent_id = parentId;
    ELSE
      maxObjectId := 0;
      minObjectId := 0;
    END IF;

  END GetMaxMinObjectIdByParentId;


CREATE OR REPLACE PROCEDURE GetObjectIdByParentId(parentId IN NUMBER, objectId IN NUMBER, result OUT NUMBER)AS
  k NUMBER;
  object_Id NUMBER;
  BEGIN
    SET TRANSACTION ISOLATION LEVEL READ COMMITTED;

    select count(object_id) INTO k
    from Objects
    where parent_id = parentId;

    IF(k!=0) THEN
      select object_id INTO object_Id
      from Objects
      where parent_id = parentId;

      if(object_Id = objectId) THEN
        result := object_Id;
      else
        result :=0;
      END IF;
    ELSE
      result :=0;
    END IF;

  END GetObjectIdByParentId;


CREATE OR REPLACE PROCEDURE GetObjectNameById(objectId IN NUMBER, objectName OUT VARCHAR2)AS
  k NUMBER;
  BEGIN
    SET TRANSACTION ISOLATION LEVEL READ COMMITTED;

    SELECT count(object_name) INTO k
    FROM OBJECTS
    WHERE object_id = objectId;

    if(k!=0) THEN
      SELECT object_name INTO objectName
      FROM OBJECTS
      WHERE object_id = objectId;
    ELSE
      objectName := 'none';
    END IF;

  END GetObjectNameById;


drop PROCEDURE GetMaxMinObjectId;
drop PROCEDURE GetCountOfObjects;
drop PROCEDURE GetParamId;
drop PROCEDURE GetMaxTypeId;
drop PROCEDURE GetMaxMinParamId;
drop PROCEDURE GetParams;
drop PROCEDURE LogIn;
drop PROCEDURE GetMaxMinParamIdByObjectId;
drop PROCEDURE GetObjectIdByName;
drop PROCEDURE GetParamIdByObjectId;
drop PROCEDURE GetParamDataByParamId;
drop PROCEDURE UpdateParamData;
drop PROCEDURE GetObjectIdByContent;
drop PROCEDURE GetMaxMinObjectIdByParentId;
drop PROCEDURE GetObjectIdByParentId;
drop PROCEDURE GetObjectNameById;

