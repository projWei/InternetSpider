package com.spider.net;

public class Resource {
	public HttpHeader http;
	public HttpsHeader https;
	public Content conttend;
	public Statu statu;
    public HttpHeader getHttp() {
        return http;
    }
    public void setHttp(HttpHeader http) {
        this.http = http;
    }
    public HttpsHeader getHttps() {
        return https;
    }
    public void setHttps(HttpsHeader https) {
        this.https = https;
    }
    public Content getConttend() {
        return conttend;
    }
    public void setConttend(Content conttend) {
        this.conttend = conttend;
    }
    public Statu getStatu() {
        return statu;
    }
    public void setStatu(Statu statu) {
        this.statu = statu;
    }
	
	
}
