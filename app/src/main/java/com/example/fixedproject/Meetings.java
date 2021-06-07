package com.example.fixedproject;

public class Meetings {
    //title
    private String title; //s=start e=end
    //ids
    private long id;
    private long userid;
    private long typeid;
    //starts
    private int sminute;
    private int shour;
    private int syear;
    private int smonth;
    private int sday;
    //ends
    private int eminute;
    private int ehour;
    private int eyear;
    private int emonth;
    private int eday;
    public Meetings(){}
    public Meetings(String title, int sminute, int shour, int syear, int smonth, int sday, int eminute, int ehour, int eyear, int emonth, int eday, long id, long userid, long typeid){
        this.title = title;
        this.sminute = sminute;
        this.shour = shour;
        this.syear = syear;
        this.smonth = smonth;
        this.sday = sday;
        this.eminute = eminute;
        this.ehour = ehour;
        this.eyear = eyear;
        this.emonth = emonth;
        this.eday = eday;
        this.id = id;
        this.userid = userid;
        this.typeid = typeid;
    }
    //title
    public String getTitle(){
        return title;
    }
    public void setTitle(String title){
        this.title=title;
    }
    //start times
    public int getSminute(){
        return sminute;
    }
    public void setSminute(int sminute){
        this.sminute=sminute;
    }
    public int getShour(){
        return shour;
    }
    public void setShour(int shour){
        this.shour=shour;
    }
    public int getSyear(){
        return syear;
    }
    public void setSyear(int syear){
        this.syear=syear;
    }
    public int getSmonth(){ return smonth; }
    public void setSmonth(int smonth){
        this.smonth=smonth;
    }
    public int getSday(){
        return sday;
    }
    public void setSday(int sday){
        this.sday=sday;
    }
    //end times
    public int getEminute(){
        return eminute;
    }
    public void setEminute(int eminute){
        this.eminute=eminute;
    }
    public int getEhour(){
        return ehour;
    }
    public void setEhour(int ehour){
        this.ehour=ehour;
    }
    public int getEyear(){
        return eyear;
    }
    public void setEyear(int eyear){
        this.eyear=eyear;
    }
    public int getEmonth(){
        return emonth;
    }
    public void setEmonth(int emonth){
        this.emonth=emonth;
    }
    public int getEday(){
        return eday;
    }
    public void setEday(int eday){ this.eday=eday; }
    //ids
    public long getId(){
        return id;
    }
    public void setId(long id){ this.id=id; }
    public long getUserid(){
        return userid;
    }
    public void setUserid(long userid){ this.userid=userid; }
    public long getTypeid(){ return   typeid; }
    public void setTypeid(long typeid){ this.typeid = typeid; }

    @Override
    public String toString(){
        return title + '\'' +
                sday + smonth + syear + shour + sminute + '\'' +
                eday + emonth + eyear + ehour + eminute; //+ '\'' + type
    }

}
