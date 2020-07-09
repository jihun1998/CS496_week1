package com.example.myapplication_week1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TabHost;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    ImageView image1, image2,image3,image4, image5, image6, image7, image8, image9, image10, image11, image12, image13, image14, image15, image16, image17, image18, image19, image20, image21;
    ImageView image;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TabHost host=(TabHost)findViewById(R.id.host);
        host.setup();

        TabHost.TabSpec spec = host.newTabSpec("tab1");
        spec.setIndicator(null, ResourcesCompat.getDrawable(getResources(), R.drawable.tab_icon1, null));
        spec.setContent(R.id.tab_content1);
        host.addTab(spec);

        spec = host.newTabSpec("tab2");
        spec.setIndicator(null, ResourcesCompat.getDrawable(getResources(), R.drawable.tab_icon2, null));
        spec.setContent(R.id.tab_content2);
        host.addTab(spec);

        image = (ImageView)findViewById(R.id.mainimage);
        image1 = (ImageView)findViewById(R.id.image1);
        image1.setOnClickListener(this);
        image2 = (ImageView)findViewById(R.id.image2);
        image2.setOnClickListener(this);
        image3 = (ImageView)findViewById(R.id.image3);
        image3.setOnClickListener(this);
        image4 = (ImageView)findViewById(R.id.image4);
        image4.setOnClickListener(this);
        image5 = (ImageView)findViewById(R.id.image5);
        image5.setOnClickListener(this);
        image6 = (ImageView)findViewById(R.id.image6);
        image6.setOnClickListener(this);
        image7 = (ImageView)findViewById(R.id.image7);
        image7.setOnClickListener(this);
        image8 = (ImageView)findViewById(R.id.image8);
        image8.setOnClickListener(this);
        image9 = (ImageView)findViewById(R.id.image9);
        image9.setOnClickListener(this);
        image10 = (ImageView)findViewById(R.id.image10);
        image10.setOnClickListener(this);
        image11 = (ImageView)findViewById(R.id.image11);
        image11.setOnClickListener(this);
        image12 = (ImageView)findViewById(R.id.image12);
        image12.setOnClickListener(this);
        image13 = (ImageView)findViewById(R.id.image13);
        image13.setOnClickListener(this);
        image14 = (ImageView)findViewById(R.id.image14);
        image14.setOnClickListener(this);
        image15 = (ImageView)findViewById(R.id.image15);
        image15.setOnClickListener(this);
        image16 = (ImageView)findViewById(R.id.image16);
        image16.setOnClickListener(this);
        image17 = (ImageView)findViewById(R.id.image17);
        image17.setOnClickListener(this);
        image18 = (ImageView)findViewById(R.id.image18);
        image18.setOnClickListener(this);
        image19 = (ImageView)findViewById(R.id.image19);
        image19.setOnClickListener(this);
        image20 = (ImageView)findViewById(R.id.image20);
        image20.setOnClickListener(this);
        image21 = (ImageView)findViewById(R.id.image21);
        image21.setOnClickListener(this);


        spec = host.newTabSpec("tab3");
        spec.setIndicator(null, ResourcesCompat.getDrawable(getResources(), R.drawable.tab_icon3, null));
        spec.setContent(R.id.tab_content3);
        host.addTab(spec);

    }

    @Override
    public void onClick(View v) {
        if(v==image1){
           image.setImageResource(R.drawable.image1);
        }
        else if(v==image2){
            image.setImageResource(R.drawable.image2);
        }
        else if(v==image3){
            image.setImageResource(R.drawable.image3);
        }
        else if(v==image4){
            image.setImageResource(R.drawable.image4);
        }
        else if(v==image5){
            image.setImageResource(R.drawable.image5);
        }
        else if(v==image6){
            image.setImageResource(R.drawable.image6);
        }
        else if(v==image7){
            image.setImageResource(R.drawable.image7);
        }
        else if(v==image8){
            image.setImageResource(R.drawable.image8);
        }
        else if(v==image9){
            image.setImageResource(R.drawable.image9);
        }
        else if(v==image10){
            image.setImageResource(R.drawable.image10);
        }
        else if(v==image11){
            image.setImageResource(R.drawable.image11);
        }
        else if(v==image12){
            image.setImageResource(R.drawable.image12);
        }
        else if(v==image13){
            image.setImageResource(R.drawable.image13);
        }
        else if(v==image14){
            image.setImageResource(R.drawable.image14);
        }
        else if(v==image15){
            image.setImageResource(R.drawable.image15);
        }
        else if(v==image16){
            image.setImageResource(R.drawable.image16);
        }
        else if(v==image17){
            image.setImageResource(R.drawable.image17);
        }
        else if(v==image18){
            image.setImageResource(R.drawable.image18);
        }
        else if(v==image19){
            image.setImageResource(R.drawable.image19);
        }
        else if(v==image20){
            image.setImageResource(R.drawable.image20);
        }
        else if(v==image21){
            image.setImageResource(R.drawable.image21);
        }
    }


    public static void main(String[] args){
        System.out.println("Hello world");
    }
}