package com.example.myapplication_week1;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Next extends Activity {

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        setContentView(R.layout.pb_click);

        Intent intent=getIntent();

        TextView name=(TextView)findViewById(R.id.tv_name);
        TextView number=(TextView)findViewById(R.id.tv_number);

        name.setText(intent.getStringExtra("name"));
        number.setText(intent.getStringExtra("number"));
        final int idx=intent.getIntExtra("index",0);

        //BUTTON-DELETE
        Button delbt=(Button)findViewById(R.id.del);
        delbt.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                MainActivity.list.remove(idx);

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

                Intent intent=new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                finishAffinity();
            }
        });

        //BUTTON-MODIFY
        final Button chbt=(Button)findViewById(R.id.ch);
        chbt.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(), Modify.class);
                intent.putExtra("index", idx);
                startActivity(intent);
            }
        });
    }
}
