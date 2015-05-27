package com.spider.parser;

import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class URLMatcher{
//    public static final String regex = "(http://|ftp://|https://|www){0,1}[^\u4e00-\u9fa5\\s]*?\\.(com|net|cn|me|tw|fr)[^\u4e00-\u9fa5\\s]*";
//    ("(http://|ftp://|https://|www){0,1}[^\u4e00-\u9fa5\\s]*?\\.(com|net|cn|me|tw|fr)[^\u4e00-\u9fa5\\s]
    public static final String regex2 = "http://[^\u4e00-\u9fa5\\s]*\"";
    public static Pattern pattern;
    public static Matcher matcher;
    public URLMatcher() {
    }
    public static Set<String> matcherURL(String content,Set<String> set){
        pattern = Pattern.compile(regex2);
        Matcher matcher = pattern.matcher(content);
        while(matcher.find()){
            String s = matcher.group();
            s = dealURL(s);
            set.add(s);
        }
        return set;
    }
    private static String dealURL(String s) {
        int index = 0;
        if(s.contains("http://")){
            index = s.indexOf("http://");
            s = s.substring(index);
            if(s.contains("\"")){
                index = s.indexOf("\"");
                s = s.substring(0,index);
                if(s.contains("'")){
                    index = s.indexOf("'");
                    s = s.substring(0,index);
                }
            }
            return s;
        }
        return null;
    }
}