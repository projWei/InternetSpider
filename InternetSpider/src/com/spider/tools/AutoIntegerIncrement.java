package com.spider.tools;

import java.util.concurrent.atomic.AtomicInteger;

public class AutoIntegerIncrement {
    private static AtomicInteger number = new AtomicInteger();
    public static int getInteger(){
        int num = number.getAndIncrement();
        return num;
    }
    public static void main(String[] args) {
       int m = getInteger();
       int n = getInteger();
       
       System.out.println(m);
       System.out.println(n);
    }
    
}
