package com.example.fixedproject;

public class Type {
    private int tid;
    private String tname;
    public Type(String tname, int tid){ this.tname = tname; this.tid = tid; }
    public String getTname(){
        return tname;
    }
    public void setTname(String tname){ this.tname = tname; }
    public int getTid(){
        return tid;
    }
    public void setTid(int tid){
        this.tid=tid;
    }
}

