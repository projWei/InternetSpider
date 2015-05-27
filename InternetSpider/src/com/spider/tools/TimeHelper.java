package com.spider.tools;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeHelper {
    static SimpleDateFormat formate;
    static { 
        formate = new SimpleDateFormat("YYYY-MM-dd-HH-MM-ss");
    }
    
    public static String getDate(){
        return formate.format(new Date());
    }
    public static void main(String[] args) throws InterruptedException {
        System.out.println(getDate());
        Thread.sleep(10000);
        System.out.println(getDate());
    }
}
