package com.spider.main;

import java.io.IOException;

import com.spider.service.impl.FirstJobImpl;

public class Main {
    public static void main(String[] args) {
        try {
            new FirstJobImpl().doFirstJob();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
