package com.wildanokt.dictionarymade.model;

public class EIDictionaryModel {
    private int id;
    private String header;
    private String content;

    public EIDictionaryModel() {
    }
    public EIDictionaryModel(String header, String content) {
        this.header = header;
        this.content = content;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getHeader() {
        return header;
    }
    public void setHeader(String header) {
        this.header = header;
    }
    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }
}
