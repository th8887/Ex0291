
package com.example.ex029;

public class Students {
    private String n;
    private int cla;
    private int grade;
    private Vaccine v1;
    private Vaccine v2;
    public Students(String n, int cla, int grade){
        this.n=n;
        this.cla=cla;
        this.grade=grade;
    }
    public Students(){
    }
    public void setName(String n){
        this.n=n;
    }
    public String getName(){
        return this.n;
    }
    public void setClass(int cla){
        this.cla=cla;
    }
    public int getCla(){
        return this.cla;
    }
    public void setGrade(int grade){
        this.grade=grade;
    }
    public int getGrade(){
        return this.grade;
    }
    public void setV1(Vaccine v1){
        this.v1=v1;
    }
    public Vaccine getV1(){
        return this.v1;
    }
    public void setV2(Vaccine v2){
        this.v2=v2;
    }
    public Vaccine getV2(){
        return this.v2;
    }
    public String toStringStudents(){
        return n+", "+cla+", "+grade+", "+v1.toStringVaccine()+", "+v2.toStringVaccine()+". ";
    }
}
