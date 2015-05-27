package com.spider.parser;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

public class Parser {
    public InputStream is;
    public BufferedReader br;

    public Parser() {

    }
    public void setInputStream(InputStream is){
        this.is = is;
    }
    public Set<String> parse() throws Exception{
        br = new BufferedReader(new InputStreamReader(is));
        String str = "";
        Set<String> set = new HashSet<String>();
        StringBuilder sb = new StringBuilder();
        while((str = br.readLine()) != null) {
            sb.append(str);
        }
        str = sb.toString();
        URLMatcher.matcherURL(str,set);
        return set;
    }


    //    Test
    public static void main(String[] args) throws Exception {
        File file = new File("C:\\Users\\qwe\\Desktop\\谁动了我的奶酪.txt");
        InputStream is = new FileInputStream(file);
        Parser p = new Parser();
        p.setInputStream(is);
        Set<String> list = p.parse();
        for (String string : list) {
            System.out.println(string);
        }
    }

}
