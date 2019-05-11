package com.example.sem_4_lab_3;

public class Student {
    int id;
    String name;
    String date;

    public Student(String name, String date){
        this.name = name;
        this.date = date;
    }

    public Student(){}

    public Student(int _id, String _name, String _date){
        this.id = _id;
        this.name = _name;
        this.date = _date;
    }

    public int getId(){
        return id;
    }
    public void setId(int id){
        this.id = id;
    }

    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name = name;
    }

    public String getDate(){
        return date;
    }
    public void setDate(String date){
        this.date = date;
    }
}
