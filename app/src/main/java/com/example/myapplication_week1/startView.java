package com.example.myapplication_week1;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TabHost;

import androidx.appcompat.app.AppCompatActivity;

public class startView  extends AppCompatActivity {
    Handler startHandler;
    LinearLayout startView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.start_layout);

        startView = (LinearLayout)findViewById(R.id.startLayout);
        /*
        final int[] startcount = {5};
        startHandler = new Handler(){
            public void handleMessage(Message msg){
                if(startcount[0] ==5){
                    startView.setAlpha(0.8F);
                    startcount[0] -=1;
                    startHandler.sendEmptyMessageDelayed(10,50);
                }
                else if(startcount[0] ==4){
                    startView.setAlpha(0.6F);
                    startcount[0] -=1;
                    startHandler.sendEmptyMessageDelayed(10,50);
                }
                else if(startcount[0] ==3){
                    startView.setAlpha(0.4F);
                    startcount[0] -=1;
                    startHandler.sendEmptyMessageDelayed(10,50);
                }
                else if(startcount[0] ==2){
                    startView.setAlpha(0.2F);
                    startcount[0] -=1;
                    startHandler.sendEmptyMessageDelayed(10,50);
                }
                else if(startcount[0] ==1){
                    startView.setVisibility(View.GONE);
                    startHandler.removeMessages(10);
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        };

        startHandler.sendEmptyMessageDelayed(10,1000);
        */
        Handler startHandler = new Handler();
        startHandler.postDelayed(new splashhandler(),870);//0.87초 고집
    }
    private class splashhandler implements Runnable{
        public void run(){
            startActivity(new Intent(getApplication(),MainActivity.class));
            startView.this.finish();
        }
    }
    @Override
    public void onBackPressed(){

    }
}
