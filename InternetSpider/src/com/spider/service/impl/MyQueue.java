package com.spider.service.impl;

import java.util.HashSet;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ConcurrentLinkedQueue;


public class MyQueue extends ConcurrentLinkedQueue {
    private static Queue<String> unVisQueue = new MyQueue();
    private static Set<String> visQueue = new HashSet<String>();
	public MyQueue() {
		super();
	}
	public static Queue getUnVisQueue(){
	    return unVisQueue;
	}
	public static Set<String> getVisQueue(){
	    return visQueue;
	}
}
