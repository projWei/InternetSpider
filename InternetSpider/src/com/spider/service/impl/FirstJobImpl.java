package com.spider.service.impl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Queue;
import java.util.concurrent.*;

import com.spider.fetch.Spider;
import com.spider.service.IFirstJob;
import com.spider.tools.TempMap;
import com.spider.tools.TimeHelper;

public class FirstJobImpl implements IFirstJob {
    // 文件输入流
    FileInputStream fis;
    // 缓冲流
    BufferedReader br;
    // 队列
    Queue queue = MyQueue.getUnVisQueue();
    int threadNum = 10;
    int spiderNum = 100;

    @SuppressWarnings("unchecked")
    @Override
    public void doFirstJob() throws IOException {
        fis = new FileInputStream(new File("InternetSpider\\start.txt"));
        br = new BufferedReader(new InputStreamReader(fis));
        String str;
        while ((str = br.readLine()) != null) {
            TempMap.urls.put(str, 1);
            TempMap.fileName = "D:\\IDEA\\workspace\\InternetSpider\\InternetSpider\\data\\TEXT\\URL_"+ TimeHelper.getDate()+".txt";
            MyQueue.getUnVisQueue().offer(str);
        }
        br.close();
        fis.close();
        // 用线程池抓取网页
        ExecutorService executorService = Executors.newFixedThreadPool(threadNum);
        for(int i=0;i<spiderNum;i++) {
            executorService.execute(new Spider());
        }
        executorService.shutdown();
    }
}
