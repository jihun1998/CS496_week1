package com.example.myapplication_week1;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Modify extends Activity implements View.OnClickListener{

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.md_click);

        Button okaybt=(Button)findViewById(R.id.okay2);
        okaybt.setOnClickListener(new View.OnClickListener(){

            public void onClick(View view) {
                EditText editname=(EditText)findViewById(R.id.md_name);
                EditText editnum=(EditText)findViewById(R.id.md_number);

                String uname=editname.getText().toString();
                String unum=editnum.getText().toString();

                Intent intent=getIntent();
                int idx=intent.getIntExtra("index",0);

                if(!uname.equals("")) {
                    System.out.println("AAAAAAAAAAAAAAaa");
                    MainActivity.list.get(idx).setName(uname);
                }
                if(!unum.equals("")) {
                    System.out.println("BBBBBBBBBBBBB");
                    MainActivity.list.get(idx).setNumber(unum);
                }

                JSONObject obj=new JSONObject();
                JSONArray arr=new JSONArray();
                try {
                    for(int i=0; i<MainActivity.list.size(); i++){
                        JSONObject tmp=new JSONObject();
                        tmp.put("name",MainActivity.list.get(i).getName());
                        tmp.put("number",MainActivity.list.get(i).getNumber());
                        arr.put(tmp);
                    }
                    obj.put("Phonebook", arr);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                String str=obj.toString();
                SharedPreferences.Editor edit=getSharedPreferences("contact",MODE_PRIVATE).edit();
                edit.putString("phone",str);
                edit.commit();

                intent=new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                finishAffinity();
            }
        });
    }
    @Override
    public void onClick(View view) {

    }
}
