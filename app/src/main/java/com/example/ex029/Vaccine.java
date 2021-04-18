package com.example.ex029;

public class Vaccine {
    private int status;
    private String where;
    private String when;
    public Vaccine(int status, String where, String when){
        this.status=status;
        this.where=where;
        this.when=when;
    }
    public Vaccine(){
    }
    public void setStatus(int status){
        this.status=status;
    }
    public int getStatus(){
        return this.status;
    }
    public void setWhere(String where){
        this.where=where;
    }
    public String getWhere(){
        return this.where;
    }
    public void setWhen(String when){
        this.when=when;
    }
    public String getWhen(){
        return this.when;
    }
    public String toStringVaccine(){
        return "Number:"+status+", "+where+", "+when;
    }
}
