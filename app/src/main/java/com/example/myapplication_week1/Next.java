package com.example.myapplication_week1;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class Next extends Activity {

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        setContentView(R.layout.pb_click);

        Intent intent=getIntent();

        TextView name=(TextView)findViewById(R.id.tv_name);
        TextView number=(TextView)findViewById(R.id.tv_number);

        name.setText(intent.getStringExtra("name"));
        number.setText(intent.getStringExtra("number"));
    }
}
