package com.spider.parser;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TestPattern {
    public static void main(String[] args)
    {
        String s = "<a href=\"www.baidu.com?sjdflsle\">";
        String reg = "www.baidu.com.*";
        Pattern p = Pattern.compile(reg);
        Matcher m = p.matcher(s);
        while(m.find()){
            String d = m.group();
            System.out.println(d);
        }
        
        
    }
}
