package com.spider.filter;

import java.util.Set;

public class FilterURL {

    public FilterURL() {

    }

    //把一个集合里的网站和要比较的集合有重复的网站除去
    public void filter(Set<String> needfilter,Set<String> hadvisited){
        needfilter.removeAll(hadvisited);
    }
    //过滤图片
    public boolean filterPhoto(String str){
        if(str.endsWith("jpg")||str.endsWith("gif")||str.endsWith("png")||str.endsWith("bmp")){
            return true;
        }
        return false;
    }
    //过滤视屏
    public boolean filterVidio(String str){
        if(str.endsWith("swf")||str.endsWith("mp4")){
            return true;
        }
        return false;
    }
    public boolean filterJavaScript(String str){
        if(str.endsWith(".js")){
            return true;
        }
        return false;
    }

    public int filterDeep(String str){
        //TODO
        return 0;
    }
}
