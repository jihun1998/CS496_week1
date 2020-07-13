package com.example.myapplication_week1;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;

public class Add extends Activity implements View.OnClickListener{
    final ArrayList<Phonebook> list=new ArrayList<Phonebook>();
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_click);

        Button okaybt=(Button)findViewById(R.id.okay);
        okaybt.setOnClickListener(new View.OnClickListener(){

            public void onClick(View view) {
                EditText editname=(EditText)findViewById(R.id.add_name);
                EditText editnum=(EditText)findViewById(R.id.add_number);

                String uname=editname.getText().toString();
                String unum=editnum.getText().toString();

                Phonebook pb=new Phonebook(uname,unum);
                list.add(pb);

                finish();
            }
        });
    }

    public ArrayList<Phonebook> getTmpList(){
        return list;
    }

    @Override
    public void onClick(View view) {

    }
}
