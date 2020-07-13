package com.example.myapplication_week1;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Collections;

public class Next extends Activity {

    @SuppressLint("StringFormatInvalid")
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        setContentView(R.layout.pb_click);

        Intent intent=getIntent();

        TextView name=(TextView)findViewById(R.id.tv_name);
        TextView number=(TextView)findViewById(R.id.tv_number);
        TextView friendly=(TextView)findViewById(R.id.tv_per);

        int total=intent.getIntExtra("total",0);
        name.setText(intent.getStringExtra("name"));
        number.setText(intent.getStringExtra("number"));
        friendly.setText(Integer.toString(intent.getIntExtra("friendly",0)*100/total)+"%");
        final int idx=intent.getIntExtra("index",0);

        JSONObject obj=new JSONObject();
        JSONArray arr=new JSONArray();
        try {
            for(int i=0; i<MainActivity.list.size(); i++){
                JSONObject tmp=new JSONObject();
                tmp.put("name",MainActivity.list.get(i).getName());
                tmp.put("number",MainActivity.list.get(i).getNumber());
                tmp.put("friendly",MainActivity.list.get(i).getFriendly());
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

        //BUTTON-DELETE
        Button delbt=(Button)findViewById(R.id.del);
        delbt.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                AlertDialog.Builder del=new AlertDialog.Builder(Next.this);
                del.setTitle("DELETE");
                del.setMessage("해당 연락처를 영구적으로 삭제하시겠습니까?");

                del.setNegativeButton("예", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        MainActivity.list.remove(idx);

                        JSONObject obj=new JSONObject();
                        JSONArray arr=new JSONArray();
                        try {
                            for(int j=0; j<MainActivity.list.size(); j++){
                                JSONObject tmp=new JSONObject();
                                tmp.put("name",MainActivity.list.get(j).getName());
                                tmp.put("number",MainActivity.list.get(j).getNumber());
                                tmp.put("friendly",MainActivity.list.get(j).getFriendly());
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

                        Toast.makeText(getBaseContext(),"삭제되었습니다.", Toast.LENGTH_SHORT).show();

                        Intent intent=new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(intent);
                        finishAffinity();
                    }
                });
                del.setPositiveButton("아니오 뚱인데요", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
                del.create().show();
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

    @Override
    public void onBackPressed(){
        Intent intent=new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
        finishAffinity();
    }
}
