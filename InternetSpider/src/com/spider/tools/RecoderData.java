package com.spider.tools;

import com.spider.db.DBFactory;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Set;

public class RecoderData {
    private File file;
    private Set<String> set;
    public RecoderData(Set<String> set) {
        this.set = set;
    }
    public void recode() throws IOException{
        file =new File(TempMap.fileName);
        if(file.exists()){
            //文件大小小于3K就可以继续写
            //否则创建新的文件
            if(file.length() <= 3*1024){
                recoder1(TempMap.fileName);
            } else{
                recoder0("D:\\IDEA\\workspace\\InternetSpider\\InternetSpider\\data\\TEXT\\URL_"+ TimeHelper.getDate()+".txt");
            }
        }
        else{
            //如果文件不存在就创建过一个新的文件
            recoder0(TempMap.fileName);
        }
    }
    //添加新内容
    private void recoder1(String str)throws IOException{
        StringBuffer strbuf = new StringBuffer();
        BufferedWriter os = new BufferedWriter(new FileWriter(file,true));
        String dburl;
        for(String url : set){
            dburl = url;
            url = strbuf.append("date:\t").append(TimeHelper.getDate()+"\t").append(url).toString();
            os.append(url);
            os.newLine();
            //存入数据库
            try {
                DBFactory.getDBService().save(dburl);
            } catch (Exception e) {
                e.printStackTrace();
            }
            strbuf.setLength(0);
        }
        os.flush();
        os.close();
    }
    //创建新的文件
    private void recoder0(String str) throws IOException{
        StringBuffer strbuf = new StringBuffer();
        BufferedWriter os = new BufferedWriter(new FileWriter(new File(str)));
        String dburl;
        for(String url : set){
            dburl = url;
            url = strbuf.append("date:\t").append(TimeHelper.getDate()+"\t").append(url).toString();
            os.write(url);
            os.newLine();
            ///存入数据库
            try {
                DBFactory.getDBService().save(dburl);
            } catch (Exception e) {
                e.printStackTrace();
            }
            strbuf.setLength(0);
        }
        os.flush();
        os.close();
    }
    public  File getFile(){
        return file;
    }
}
