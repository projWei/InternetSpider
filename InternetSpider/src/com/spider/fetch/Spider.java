package com.spider.fetch;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.Queue;
import java.util.Random;
import java.util.Set;

import com.spider.filter.FilterURL;
import com.spider.net.StringToURL;
import com.spider.parser.Parser;
import com.spider.service.impl.MyQueue;
import com.spider.tools.AutoIntegerIncrement;
import com.spider.tools.RecoderData;
import com.spider.tools.StaticBoolean;
import com.spider.tools.TimeHelper;
import org.apache.log4j.Logger;

public class Spider implements Runnable {
    private Logger LOG = Logger.getLogger(Spider.class);
    public Queue<String> queue = MyQueue.getUnVisQueue();
    boolean flag = false;
    private ThreadLocal tl = new ThreadLocal();

    private String currentURL;
    public Spider(){
    }
    public void run() {
        if(tl.get()==null){
            tl.set(false);
            flag = (Boolean) tl.get();
        }
        else{
            flag = (Boolean) tl.get();
        }
        InputStream is = null;
        while(true) {
            while (!queue.isEmpty()) {
                // 从队列中取出url，爬取网页
                try {
                    String urlStr = (String) queue.poll();
                    currentURL = urlStr;
                    if (urlStr == null)
                        break;
                    FilterURL t = new FilterURL();                // 如果是图片,則下载图片
                    boolean isPhoto = t.filterPhoto(urlStr);
                    is = getInputStream(urlStr);
                    if (isPhoto) {
                        // 把图片保存下來
                        LOG.info("保存图片" + urlStr);
                        takePhoto(is, urlStr);
                        continue;
                    }

                    //记录URL
                    boolean isOk = takeURL(is, urlStr);
                    if (!isOk) {

                    } else {
                        LOG.info("队列大小：" + queue.size());
                    }
                } catch (Exception e) {
                    LOG.error(e.getMessage());
                }
                LOG.info("抓取当前url：" + currentURL + "结束!!");
            }
        }
    }
    public void recoderURL(Set<String> set) throws IOException{
        RecoderData recoder = new RecoderData(set);
        recoder.recode();
    }
    //解析网页并把URL存入队列
    public boolean takeURL(InputStream is, String urlStr) {
        try {
            Parser parser = new Parser();
            parser.setInputStream(is);
            // 解析得到网址
            Set<String> set = parser.parse();
            is.close();
            // 把解析过的网站从未解析的网站释放
            Queue<String> unvisited = MyQueue.getUnVisQueue();
            Set<String> hadvisited = MyQueue.getVisQueue();
            hadvisited.add(urlStr);
            unvisited.poll();
            if(set.isEmpty()){
                LOG.info(currentURL+" 里没有网址");
                return false;
            }
            //爬取到的网站记录下来
            recoderURL(set);
            // 把网站过滤掉外站，并把已经爬取的除去
            FilterURL f = new FilterURL();
            f.filter(set, hadvisited);
            // 放入未爬取队列
            unvisited.addAll(set);
        } catch (Exception e) {
            // 无效url放入已读url
            MyQueue.getVisQueue().add(urlStr);
            LOG.error(e.getMessage());
            return false;
        }
        return true;
    }
    //采集图片
    public void takePhoto(InputStream is, String format) throws IOException {
        String name = format.substring(format.length() - 3);
        byte[] buf = new byte[1024 * 8];
        int num = 0;
        OutputStream os = new FileOutputStream(new File("InternetSpider/data/picture/picture"
                + AutoIntegerIncrement.getInteger() + "."+name));
        while ((num = is.read(buf)) != -1) {
            os.write(buf, 0, num);
        }
        is.close();
        os.close();
    }
    //获取输入流
    public InputStream getInputStream(String urlStr) throws IOException {
        URL url = StringToURL.changeToURL(urlStr);
        URLConnection conn = url.openConnection();
        InputStream is = conn.getInputStream();
        return is;
    }
}