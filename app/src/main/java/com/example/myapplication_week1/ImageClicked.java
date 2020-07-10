package com.example.myapplication_week1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Button;

public class ImageClicked extends AppCompatActivity implements View.OnClickListener{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.image_clicked_layout);

        Intent intent = getIntent();
        ImageView image = (ImageView)findViewById(R.id.image);
        Button button = (Button)findViewById(R.id.back);
        button.setOnClickListener(this);

        int img = Integer.parseInt(intent.getStringExtra("image"));
        image.setImageResource(img);

    }
    @Override
    public void onClick(View v){
        Intent mainintent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(mainintent);
        finishAffinity();

    }
}
