package com.spider.db;

import java.sql.*;
import java.util.Collection;

/**
 * Created by qwe on 2015/5/24.
 */
public interface IDBService {
    void save(String url) throws Exception;
    void saveAll(Collection<String> url) throws Exception;
    Connection getConnection() throws  Exception;
}
