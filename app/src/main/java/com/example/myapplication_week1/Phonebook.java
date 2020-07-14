package com.example.myapplication_week1;

import java.io.Serializable;
import java.util.ArrayList;

public class Phonebook implements Serializable {
    private String name;
    private String number;
    private
    static ArrayList<Phonebook> pblist=new ArrayList<Phonebook>();

    public Phonebook(){}
    public Phonebook(String name, String number){
        this.name=name;
        this.number=number;
    }

    public void setName(String name){ this.name=name; }
    public void setNumber(String number){
        this.number=number;
    }
    public String getName(){
        return name;
    }
    public String getNumber(){
        return number;
    }
    public ArrayList<Phonebook> getList(){ return pblist; }

}