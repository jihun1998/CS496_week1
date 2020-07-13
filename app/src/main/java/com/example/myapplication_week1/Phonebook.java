package com.example.myapplication_week1;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Phonebook {
    private String name;
    private String number;
    ArrayList<Phonebook> pblist=new ArrayList<Phonebook>();

    public Phonebook(){}
    public Phonebook(String name, String number){
        this.name=name;
        this.number=number;
        pblist.add(this);
    }
    public Phonebook(String json){
        jsonParsing(json);
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

    private void jsonParsing(String json){
        try{
            JSONObject jobj=new JSONObject(json);
            JSONArray jarray=jobj.getJSONArray("Phonebook");

            for(int i=0; i<jarray.length(); i++){
                JSONObject pobj=jarray.getJSONObject(i);

                Phonebook pb=new Phonebook();
                pb.setName(pobj.getString("name"));
                pb.setNumber(pobj.getString("number"));

                pblist.add(pb);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
