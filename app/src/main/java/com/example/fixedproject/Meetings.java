package com.example.fixedproject;

public class Meetings {
    private String title; //s=start e=end
    private int id;
    private int userid;
    private int sminute;
    private int shour;
    private int syear;
    private int smonth;
    private int sday;
    private int eminute;
    private int ehour;
    private int eyear;
    private int emonth;
    private int eday;
    private int typeid;


    public Meetings(String title, int sminute, int shour, int syear, int smonth, int sday, int eminute, int ehour, int eyear, int emonth, int eday,int id,int userid, int typeid){
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
    public String getTitle(){
        return title;
    }
    public void setTitle(String title){
        this.title=title;
    }
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
    public int getId(){
        return id;
    }
    public void setId(int id){ this.id=id; }
    public int getUserid(){
        return userid;
    }
    public void setUserid(int userid){ this.userid=userid; }
    public int getTypeid(){ return   typeid; }
    public void setTypeid(int typeid){ this.typeid = typeid; }

    @Override
    public String toString(){
        return title + '\'' +
                sday + smonth + syear + shour + sminute + '\'' +
                eday + emonth + eyear + ehour + eminute;
    }
}
