package com.example.fixedproject;

public class Type {
    private long tid;
    private String tname;
    public Type(String tname, long tid){ this.tname = tname; this.tid = tid; }
    public String getTname(){
        return tname;
    }
    public void setTname(String tname){ this.tname = tname; }
    public long getTid(){
        return tid;
    }
    public void setTid(long tid){
        this.tid=tid;
    }
}

