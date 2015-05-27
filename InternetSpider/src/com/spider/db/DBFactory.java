package com.spider.db;

import java.sql.*;

/**
 * Created by qwe on 2015/5/24.
 */
public class DBFactory {
    private static IDBService dbService = new DBService();
    private DBFactory(){

    }
    public static IDBService getDBService(){
        return dbService;
    }

    public static void main(String[] args) throws Exception {
        for (int i = 0; i < 1000; i++) {
            System.out.println(getDBService());
        }
    }
}
