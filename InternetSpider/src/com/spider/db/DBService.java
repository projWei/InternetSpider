package com.spider.db;

import com.spider.tools.TimeHelper;
import org.apache.log4j.Logger;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.Collection;
import java.util.Properties;


/**
 * Created by qwe on 2015/5/24.
 */
public class DBService implements IDBService{
    private static String user;
    private static String password;
    private static String driver;
    private static String url;
    private static String dbname;
    private static Properties properties;
    private static String sql_select;
    private static Connection conn;
    private static Logger LOG = Logger.getLogger(DBService.class);
    static {
        properties = new Properties();
        try {
            properties.load(new FileInputStream(new File("InternetSpider/src/com/spider/db/db.properties")));
            user = properties.getProperty("user");
            password = properties.getProperty("password");
            driver = properties.getProperty("driver");
            url = properties.getProperty("url");
            dbname = properties.getProperty("dbname");
            sql_select = properties.getProperty("sql_select");
        } catch (IOException e) {
            LOG.error(e);
        }
    }


    public void closeConnection(ResultSet rst, PreparedStatement pst, Statement st, Connection conn) throws Exception {
        if(rst != null){
            rst.close();
        }
        if(pst != null){
            pst.close();
        }
        if(st != null){
            st.close();
        }
        if(conn != null){
            conn.close();
        }
        LOG.info("数据库已关闭");
    }


    public Connection getConnection() throws SQLException, ClassNotFoundException {

        if(conn != null && !conn.isClosed()){
            return conn;
        }
        else{
            Class.forName(driver);
            Connection conn0 = DriverManager.getConnection(url, user, password);
            conn = conn0;
            LOG.info("数据库已经连接:"+conn0);
            return conn0;
        }
    }
    //插入一条url
    public void save(String url) throws SQLException, ClassNotFoundException {
        StringBuilder sb = new StringBuilder();
        //insert into URLS (url,time) values('www.baidu.com','2015-13123');
        sb.append("insert into ").append(dbname).append(" (url,time)").append(" values(\'").append(url).append("\',\'").append(TimeHelper.getDate()).append("\')");
        String sql = sb.toString();
        Connection conn = getConnection();
        Statement state = conn.createStatement();
        state.execute(sql);
        LOG.info("向数据库里插入数据："+url);

    }
    //插入多条url
    public void saveAll(Collection<String> collection) throws SQLException, ClassNotFoundException {
        Statement state = getConnection().createStatement();
        StringBuilder sb = new StringBuilder();
        for (String value:collection){
            //insert into URLS (url,time) values('www.baidu.com','2015-13123');
            sb.append("insert into ").append(dbname).append(" (url,time)").append(" values(\'").append(url).append("\',\'").append(TimeHelper.getDate()).append("\')");
            String sql = sb.toString();
            sb.setLength(0);
            state.addBatch(sql);
        }
        state.executeBatch();
        LOG.info("向数据库里插入大量数据："+collection);
    }
    //查询？
    public void qury(){

    }

    public static void main(String[] args) throws Exception{
        for (int i = 0; i < 1000; i++) {
            System.out.println( DBFactory.getDBService().getConnection());

        }
    }
}
