package com.example;

import de.greenrobot.daogenerator.DaoGenerator;
import de.greenrobot.daogenerator.Entity;
import de.greenrobot.daogenerator.Schema;

public class MyGreenDaoClass {

    public static void main(String[] args) throws Exception{

        int version = 1;

        String defaultJavaPackage = "com.example";

        Schema schema = new Schema(version , defaultJavaPackage);

        createTable(schema);
        //D:\TextDemo\MyApplication\anewbegin\src\main\java-gen
        new DaoGenerator().generateAll(schema , "D:\\TextDemo\\MyApplication\\anewbegin\\src\\main\\java-gen");
    }

    public static void createTable(Schema schema){

        Entity entity = schema.addEntity("User");

        entity.addIdProperty().primaryKey();
        entity.addStringProperty("userName");
        entity.addStringProperty("passWord");
        entity.addStringProperty("age");
        entity.addStringProperty("cool").notNull();

        Entity entitylive = schema.addEntity("live");

        entitylive.addIdProperty().primaryKey();
        entitylive.addStringProperty("column").notNull();
        entitylive.addStringProperty("resouse").notNull();
    }
}
