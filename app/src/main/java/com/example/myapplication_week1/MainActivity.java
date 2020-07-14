package com.example.myapplication_week1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;

import android.content.Intent;
//import android.database.Cursor;
//import android.graphics.Bitmap;
//import android.graphics.BitmapFactory;
import android.content.SharedPreferences;
import android.graphics.ImageFormat;
import android.net.Uri;
import android.os.Bundle;
//import android.provider.ContactsContract;
import android.os.Vibrator;
import android.provider.MediaStore;
//import android.util.Base64;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TabHost;
import android.widget.Button;
import android.view.ViewGroup;

//import java.io.ByteArrayOutputStream;
import java.util.*;
//import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TabWidget;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.CountDownTimer;
import android.os.Message;
import android.os.Handler;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Collections;
import java.util.logging.LogRecord;


class Ladder extends View{
    public Ladder(Context context){
        super(context);
    }
    public Ladder(Context context, AttributeSet att){
        super(context,att);
    }
    public Ladder(Context context, AttributeSet att, int a){
        super(context,att, a);
    }
    int w=100;
    int h=80;
    int px=0;
    int py=0;
    int dx=dpToPx(47)/12;
    int dy=dpToPx(46)/12;

    int x1,y1,x2,y2;
    int bx;
    int by ;

    @Override
    public void onDraw(Canvas c){

        Paint paint = new Paint();
        paint.setColor(Color.DKGRAY);

        c.drawRect(x1+bx,y1+by,x2+bx,y2+by, paint);

    }

    public int dpToPx(int sizeInDP){
        int pxVal = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, sizeInDP, getResources().getDisplayMetrics());
        return pxVal;
    }
}




public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    //ImageView image1, image2,image3,image4, image5, image6, image7, image8, image9, image10, image11, image12, image13, image14, image15, image16, image17, image18, image19, image20, image21;
    GridView gridView;
    ImageAdapter imageAdapter;
    private final int GET_GALLERY_IMAGE=200;


    Button button, tab3_btn, tab3_1, tab3_2, tab3_3, tab3_4, tab3_5, name_btn ;
    Button tab3_1_dst, tab3_2_dst, tab3_3_dst, tab3_4_dst, tab3_5_dst;
    Button result_btn;
    LinearLayout ladder, names, dsts;
    //ImageView image;

    PbAdapter adapter=null;
    static ArrayList<Phonebook> list = new ArrayList<Phonebook>();
    int total=0;

    //Handler mdHandler, mlHandler, mrHandler;
    Handler mHandler;
    int ct=0;
    final int[][] path=new int[7][5];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final TabHost host=(TabHost)findViewById(R.id.host);
        host.setup();


        TabHost.TabSpec spec = host.newTabSpec("tab1");
        spec.setIndicator("CONTACT");
        spec.setContent(R.id.tab_content1);
        host.addTab(spec);

        SharedPreferences sp=getSharedPreferences("contact",MODE_PRIVATE);
        String str=sp.getString("phone",null);
        if(str!=null) jsonParsing(str);
        Collections.sort(list);

        //LISTVIEW
        final ListView listview = (ListView)findViewById(R.id.pb_listview);

        adapter = new PbAdapter(this,R.layout.pb_item, list);
        adapter.notifyDataSetChanged();
        listview.setAdapter(adapter);

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView adapterView, View view, int position, long id) {
                Intent intent=new Intent(getApplicationContext(), Next.class);

                intent.putExtra("name", list.get(position).getName());
                intent.putExtra("number", list.get(position).getNumber());
                list.get(position).setFriendly(list.get(position).getFriendly()+1);
                intent.putExtra("friendly", list.get(position).getFriendly());
                total++;
                intent.putExtra("total",total);
                intent.putExtra("index", position);
                startActivity(intent);
            }
        });

        //BUTTON-ADD
        final Button addbt=(Button)findViewById(R.id.add);
        addbt.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(), Add.class);
                startActivity(intent);
            }
        });

        spec = host.newTabSpec("tab2");
        spec.setIndicator("GALLERY");
        spec.setContent(R.id.tab_content2);
        host.addTab(spec);
        final int[] viewnum = new int[21];
        final int[] mostviewNum = {0};
        final int[] secondNum = {0};
        final int[] thirdNum = {0};
        final int[] mostviewImg = {0};
        final int[] secondImg = {0};
        final int[] thirdImg = {0};
        final ImageView mostViewd = (ImageView)findViewById(R.id.mostView);
        final ImageView second = (ImageView)findViewById(R.id.second);
        final ImageView third = (ImageView)findViewById(R.id.third);
        final SharedPreferences sp2 = getSharedPreferences("gallery",MODE_PRIVATE);


        button = (Button)findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,"image/*");
                startActivityForResult(intent, GET_GALLERY_IMAGE);
            }
        });


        gridView = (GridView)findViewById(R.id.gridView);

        imageAdapter = new ImageAdapter();
        imageAdapter.addItem(new ImageItem(R.drawable.image1));
        imageAdapter.addItem(new ImageItem(R.drawable.image2));
        imageAdapter.addItem(new ImageItem(R.drawable.image3));
        imageAdapter.addItem(new ImageItem(R.drawable.image4));
        imageAdapter.addItem(new ImageItem(R.drawable.image5));
        imageAdapter.addItem(new ImageItem(R.drawable.image6));
        imageAdapter.addItem(new ImageItem(R.drawable.image7));
        imageAdapter.addItem(new ImageItem(R.drawable.image8));
        imageAdapter.addItem(new ImageItem(R.drawable.image9));
        imageAdapter.addItem(new ImageItem(R.drawable.image10));
        imageAdapter.addItem(new ImageItem(R.drawable.image11));
        imageAdapter.addItem(new ImageItem(R.drawable.image12));
        imageAdapter.addItem(new ImageItem(R.drawable.image13));
        imageAdapter.addItem(new ImageItem(R.drawable.image14));
        imageAdapter.addItem(new ImageItem(R.drawable.image15));
        imageAdapter.addItem(new ImageItem(R.drawable.image16));
        imageAdapter.addItem(new ImageItem(R.drawable.image17));
        imageAdapter.addItem(new ImageItem(R.drawable.image18));
        imageAdapter.addItem(new ImageItem(R.drawable.image19));
        imageAdapter.addItem(new ImageItem(R.drawable.image20));
        imageAdapter.addItem(new ImageItem(R.drawable.image21));
        gridView.setAdapter(imageAdapter);

        mostviewImg[0]=sp2.getInt("most",0);
        secondImg[0]=sp2.getInt("second",0);
        thirdImg[0]=sp2.getInt("third",0);
        if(mostviewImg[0]!=0) {
            int k = getResources().getIdentifier("image" + mostviewImg[0], "drawable", getPackageName());
            mostViewd.setImageResource(k);
        }
        if(secondImg[0]!=0) {
            int k = getResources().getIdentifier("image" + secondImg[0], "drawable", getPackageName());
            second.setImageResource(k);
        }
        if(thirdImg[0]!=0) {
            int k = getResources().getIdentifier("image" + thirdImg[0], "drawable", getPackageName());
            third.setImageResource(k);
        }
        final SharedPreferences.Editor editor = sp2.edit();

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getApplicationContext(), ImageClicked.class);

                int num = sp2.getInt(Integer.toString(i),0);
                num+=1;
                editor.putInt(Integer.toString(i),num);
                editor.commit();
                for(int a=0; a<21; a++) {
                    viewnum[a] = sp2.getInt(Integer.toString(a),0);
                    if(mostviewNum[0] < viewnum[a]){
                        mostviewNum[0] = viewnum[a];
                        int temp = mostviewImg[0];
                        int temp2 = secondImg[0];
                        mostviewImg[0] = a+1;
                        if(temp==mostviewImg[0]){
                            continue;
                        }
                        if(temp2==mostviewImg[0]){
                            secondImg[0]=temp;
                            continue;
                        }

                        secondImg[0]=temp;
                        thirdImg[0]=temp2;

                    }
                    else if((a+1)!=mostviewImg[0]&&viewnum[a]>secondNum[0]){
                        secondNum[0]=viewnum[a];
                        int temp = secondImg[0];
                        secondImg[0]=a+1;
                        if(temp == secondImg[0]){
                            continue;
                        }

                        thirdImg[0]=temp;

                    }
                    else if((a+1)!=mostviewImg[0]&&(a+1)!=secondImg[0]&&viewnum[a]>thirdNum[0]){
                        thirdNum[0]=viewnum[a];
                        thirdImg[0]=a+1;

                    }
                }
                if(mostviewImg[0]!=0) {
                    int k = getResources().getIdentifier("image" + mostviewImg[0], "drawable", getPackageName());
                    mostViewd.setImageResource(k);
                }
                if(secondImg[0]!=0) {
                    int k = getResources().getIdentifier("image" + secondImg[0], "drawable", getPackageName());
                    second.setImageResource(k);
                }
                if(thirdImg[0]!=0) {
                    int k = getResources().getIdentifier("image" + thirdImg[0], "drawable", getPackageName());
                    third.setImageResource(k);
                }
                editor.putInt("most",mostviewImg[0]);
                editor.putInt("second",secondImg[0]);
                editor.putInt("third",thirdImg[0]);
                editor.commit();
                intent.putExtra("image", Integer.toString((imageAdapter.getItem(i).getImage())));
                startActivity(intent);

            }
        });
        Button reset = (Button)findViewById(R.id.reset);
        reset.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                for(int a=0; a<21; a++){
                    editor.putInt(Integer.toString(a),0);
                }
                mostviewImg[0] = 0;
                secondImg[0] = 0;
                thirdImg[0] = 0;
                editor.putInt("most",mostviewImg[0]);
                editor.putInt("second",secondImg[0]);
                editor.putInt("third",thirdImg[0]);
                editor.commit();
                mostViewd.setImageResource(0);
                second.setImageResource(0);
                third.setImageResource(0);
            }
        });

        spec = host.newTabSpec("tab3");
        spec.setIndicator("LADDER");
        spec.setContent(R.id.tab_content3);
        host.addTab(spec);

        tab3_btn = (Button)findViewById(R.id.tab3_btn);
        tab3_1 = (Button)findViewById(R.id.btn1);
        tab3_2 = (Button)findViewById(R.id.btn2);
        tab3_3 = (Button)findViewById(R.id.btn3);
        tab3_4 = (Button)findViewById(R.id.btn4);
        tab3_5 = (Button)findViewById(R.id.btn5);

        tab3_1_dst = (Button)findViewById(R.id.btn1_dst);
        tab3_2_dst = (Button)findViewById(R.id.btn2_dst);
        tab3_3_dst = (Button)findViewById(R.id.btn3_dst);
        tab3_4_dst = (Button)findViewById(R.id.btn4_dst);
        tab3_5_dst = (Button)findViewById(R.id.btn5_dst);

        final Ladder mv = (Ladder)findViewById(R.id.mv);
        final int[] check = {0};

        final int[] line = {0};
        final int[] floor = {0};
        final int[] down = {0};
        final int[] side = {0};

        final EditText dst1 = (EditText)findViewById(R.id.text1_dst);
        final EditText dst2 = (EditText)findViewById(R.id.text2_dst);
        final EditText dst3 = (EditText)findViewById(R.id.text3_dst);
        final EditText dst4 = (EditText)findViewById(R.id.text4_dst);
        final EditText dst5 = (EditText)findViewById(R.id.text5_dst);

        ladder = (LinearLayout)findViewById(R.id.ladder);
        names = (LinearLayout)findViewById(R.id.names);
        dsts = (LinearLayout)findViewById(R.id.dsts);
        mHandler = new Handler(){
            public void handleMessage(Message msg){
                if(down[0]<12){
                    mv.py +=mv.dy;
                    mv.x1= (mv.px - mv.w/2);
                    mv.x2= (mv.px + mv.w/2);
                    mv.y1= (mv.py - mv.h/2);
                    mv.y2= (mv.py + mv.h/2);
                    mv.invalidate();
                    down[0] +=1;
                    if(down[0] ==11&& floor[0] ==7){
                        mv.py +=mv.dy*10;
                        mv.x1= (mv.px - mv.w/2);
                        mv.x2= (mv.px + mv.w/2);
                        mv.y1= (mv.py - mv.h/2);
                        mv.y2= (mv.py + mv.h/2);
                        mv.invalidate();
                        if (line[0] == 0) {
                            result_btn.setClickable(true);
                            result_btn.setVisibility(View.VISIBLE);
                            result_btn.setText(dst1.getText().toString());
                            ladder.setAlpha(0.2F);
                            names.setAlpha(0.2F);
                            dsts.setAlpha(0.2F);
                            result_btn.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    ladder.setAlpha(1.0F);
                                    names.setAlpha(1.0F);
                                    dsts.setAlpha(1.0F);
                                    result_btn.setVisibility(View.INVISIBLE);
                                    result_btn.setClickable(false);
                                }
                            });
                        } else if (line[0] == 1) {
                            result_btn.setClickable(true);
                            result_btn.setVisibility(View.VISIBLE);
                            result_btn.setText(dst2.getText().toString());
                            ladder.setAlpha(0.2F);
                            names.setAlpha(0.2F);
                            dsts.setAlpha(0.2F);
                            result_btn.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    ladder.setAlpha(1.0F);
                                    names.setAlpha(1.0F);
                                    dsts.setAlpha(1.0F);
                                    result_btn.setVisibility(View.INVISIBLE);
                                    result_btn.setClickable(false);
                                }
                            });
                        } else if (line[0] == 2) {
                            result_btn.setClickable(true);
                            result_btn.setVisibility(View.VISIBLE);
                            result_btn.setText(dst3.getText().toString());
                            ladder.setAlpha(0.2F);
                            names.setAlpha(0.2F);
                            dsts.setAlpha(0.2F);
                            result_btn.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    ladder.setAlpha(1.0F);
                                    names.setAlpha(1.0F);
                                    dsts.setAlpha(1.0F);
                                    result_btn.setVisibility(View.INVISIBLE);
                                    result_btn.setClickable(false);
                                }
                            });
                        } else if (line[0] == 3) {
                            result_btn.setClickable(true);
                            result_btn.setVisibility(View.VISIBLE);
                            result_btn.setText(dst4.getText().toString());
                            ladder.setAlpha(0.2F);
                            names.setAlpha(0.2F);
                            dsts.setAlpha(0.2F);
                            result_btn.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    ladder.setAlpha(1.0F);
                                    names.setAlpha(1.0F);
                                    dsts.setAlpha(1.0F);
                                    result_btn.setVisibility(View.INVISIBLE);
                                    result_btn.setClickable(false);
                                }
                            });
                        } else if (line[0] == 4) {
                            result_btn.setClickable(true);
                            result_btn.setVisibility(View.VISIBLE);
                            result_btn.setText(dst5.getText().toString());
                            ladder.setAlpha(0.2F);
                            names.setAlpha(0.2F);
                            dsts.setAlpha(0.2F);
                            result_btn.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    ladder.setAlpha(1.0F);
                                    names.setAlpha(1.0F);
                                    dsts.setAlpha(1.0F);
                                    result_btn.setVisibility(View.INVISIBLE);
                                    result_btn.setClickable(false);
                                }
                            });
                        }
                        Vibrator vibrator = (Vibrator)getSystemService(Context.VIBRATOR_SERVICE);
                        vibrator.vibrate(500);

                        mHandler.removeMessages(10);
                    }
                    mHandler.sendEmptyMessageDelayed(10,30);
                }
                else if(floor[0]==7){
                    mv.py +=mv.dy*30;
                    mv.x1= (mv.px - mv.w/2);
                    mv.x2= (mv.px + mv.w/2);
                    mv.y1= (mv.py - mv.h/2);
                    mv.y2= (mv.py + mv.h/2);
                    mv.invalidate();
                    Vibrator vibrator = (Vibrator)getSystemService(Context.VIBRATOR_SERVICE);
                    vibrator.vibrate(500);
                    mHandler.removeMessages(10);
                }

                else if(path[floor[0]][line[0]]==1&& side[0]<24){
                    mv.px +=mv.dx;
                    mv.x1= (mv.px - mv.w/2);
                    mv.x2= (mv.px + mv.w/2);
                    mv.y1= (mv.py - mv.h/2);
                    mv.y2= (mv.py + mv.h/2);
                    mv.invalidate();
                    side[0] +=1;
                    if(side[0] ==24){
                        line[0] +=1;
                    }
                    mHandler.sendEmptyMessageDelayed(10,30);
                }
                else if(line[0] >0&&path[floor[0]][line[0] -1]==1&& side[0]<24){
                    mv.px -=mv.dx;
                    mv.x1= (mv.px - mv.w/2);
                    mv.x2= (mv.px + mv.w/2);
                    mv.y1= (mv.py - mv.h/2);
                    mv.y2= (mv.py + mv.h/2);
                    mv.invalidate();
                    side[0] +=1;
                    if(side[0] ==24){
                        line[0] -=1;
                    }
                    mHandler.sendEmptyMessageDelayed(10,30);
                }
                else{
                    down[0] =0;
                    side[0] =0;
                    floor[0] +=1;
                    mHandler.sendEmptyMessageDelayed(10,30);
                }

            }
        };
        result_btn = (Button)findViewById(R.id.result);

        name_btn = (Button)findViewById(R.id.btn_name);
        tab3_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText num = (EditText)findViewById(R.id.num);
                int count = Integer.parseInt(num.getText().toString());
                name_btn.setVisibility(View.VISIBLE);
                for(int a=1; a<=5; a++){
                    int k = getResources().getIdentifier("line"+a, "id", getPackageName());
                    LinearLayout line=findViewById(k);
                    line.setVisibility(View.INVISIBLE);
                }
                for(int a=1; a<=7; a++){
                    for(int b=1; b<=4; b++){
                        int k = getResources().getIdentifier("line"+b+"_"+a, "id",getPackageName());
                        ImageView line = findViewById(k);
                        line.setVisibility(View.INVISIBLE);
                    }
                }
                EditText name1 = (EditText)findViewById(R.id.btn1_name);
                EditText name2 = (EditText)findViewById(R.id.btn2_name);
                EditText name3 = (EditText)findViewById(R.id.btn3_name);
                EditText name4 = (EditText)findViewById(R.id.btn4_name);
                EditText name5 = (EditText)findViewById(R.id.btn5_name);



                if(count==2){

                    tab3_1.setVisibility(View.VISIBLE);
                    name1.setVisibility(View.VISIBLE);
                    tab3_2.setVisibility(View.VISIBLE);
                    name2.setVisibility(View.VISIBLE);
                    tab3_3.setVisibility(View.GONE);
                    name3.setVisibility(View.GONE);
                    tab3_4.setVisibility(View.GONE);
                    name4.setVisibility(View.GONE);
                    tab3_5.setVisibility(View.GONE);
                    name5.setVisibility(View.GONE);

                    tab3_1_dst.setVisibility(View.VISIBLE);
                    dst1.setVisibility(View.VISIBLE);
                    tab3_2_dst.setVisibility(View.VISIBLE);
                    dst2.setVisibility(View.VISIBLE);
                    tab3_3_dst.setVisibility(View.GONE);
                    dst3.setVisibility(View.GONE);
                    tab3_4_dst.setVisibility(View.GONE);
                    dst4.setVisibility(View.GONE);
                    tab3_5_dst.setVisibility(View.GONE);
                    dst5.setVisibility(View.GONE);


                    name_btn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {

                            for(int a=0; a<7; a++){
                                for(int b=0; b<5; b++){
                                    path[a][b]=0;
                                }
                            }
                            for(int a=1; a<=2; a++){
                                int k = getResources().getIdentifier("line"+a, "id", getPackageName());
                                LinearLayout line=findViewById(k);
                                line.setVisibility(View.VISIBLE);
                            }
                            boolean check = true;
                            for(int a=1; a<=7; a++){
                                for(int b=1; b<=1; b++){
                                    int k = getResources().getIdentifier("line"+b+"_"+a, "id",getPackageName());
                                    ImageView line = findViewById(k);
                                    line.setVisibility(View.INVISIBLE);
                                    path[a-1][b-1]=0;
                                    if(b==1&&Math.random()<0.5){
                                        line = findViewById(k);
                                        line.setVisibility(View.VISIBLE);
                                        check = false;
                                        path[a-1][b-1]=1;
                                        continue;
                                    }
                                    else if(Math.random()<0.5&&check){
                                        line = findViewById(k);
                                        line.setVisibility(View.VISIBLE);
                                        check = false;
                                        path[a-1][b-1]=1;
                                        continue;
                                    }
                                    check = true;
                                }
                            }
                            EditText name1 = (EditText)findViewById(R.id.btn1_name);
                            EditText name2 = (EditText)findViewById(R.id.btn2_name);
                            final EditText dst1 = (EditText)findViewById(R.id.text1_dst);
                            final EditText dst2 = (EditText)findViewById(R.id.text2_dst);

                            tab3_1.setText(name1.getText().toString());
                            tab3_2.setText(name2.getText().toString());
                            tab3_1_dst.setText(dst1.getText().toString());
                            tab3_2_dst.setText(dst2.getText().toString());
                            name1.setVisibility(View.GONE);
                            name2.setVisibility(View.GONE);
                            dst1.setVisibility(View.GONE);
                            dst2.setVisibility(View.GONE);
                            name_btn.setVisibility(View.GONE);
                            tab3_1_dst.setClickable(false);
                            tab3_2_dst.setClickable(false);


                            tab3_1.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {

                                    line[0] = 0;
                                    floor[0] = 0;
                                    down[0] = 0;
                                    side[0] = 0;
                                    mv.bx = dpToPx(40);
                                    mv.by = 0;
                                    mv.px = 0;
                                    mv.py = 0;
                                    mHandler.sendEmptyMessageDelayed(10,200);


                                }
                            });
                            tab3_2.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {

                                    line[0] = 1;
                                    floor[0] = 0;
                                    down[0] = 0;
                                    side[0] = 0;
                                    mv.bx = dpToPx(130);
                                    mv.by = 0;
                                    mv.px = 0;
                                    mv.py = 0;
                                    mHandler.sendEmptyMessageDelayed(10,200);

                                }
                            });
                        }
                    });
                }
                else if(count==3){

                    tab3_1.setVisibility(View.VISIBLE);
                    name1.setVisibility(View.VISIBLE);
                    tab3_2.setVisibility(View.VISIBLE);
                    name2.setVisibility(View.VISIBLE);
                    tab3_3.setVisibility(View.VISIBLE);
                    name3.setVisibility(View.VISIBLE);
                    tab3_4.setVisibility(View.GONE);
                    name4.setVisibility(View.GONE);
                    tab3_5.setVisibility(View.GONE);
                    name5.setVisibility(View.GONE);

                    tab3_1_dst.setVisibility(View.VISIBLE);
                    dst1.setVisibility(View.VISIBLE);
                    tab3_2_dst.setVisibility(View.VISIBLE);
                    dst2.setVisibility(View.VISIBLE);
                    tab3_3_dst.setVisibility(View.VISIBLE);
                    dst3.setVisibility(View.VISIBLE);
                    tab3_4_dst.setVisibility(View.GONE);
                    dst4.setVisibility(View.GONE);
                    tab3_5_dst.setVisibility(View.GONE);
                    dst5.setVisibility(View.GONE);


                    name_btn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            for(int a=0; a<7; a++){
                                for(int b=0; b<5; b++){
                                    path[a][b]=0;
                                }
                            }
                            for(int a=1; a<=3; a++){
                                int k = getResources().getIdentifier("line"+a, "id", getPackageName());
                                LinearLayout line=findViewById(k);
                                line.setVisibility(View.VISIBLE);
                            }
                            boolean check = true;
                            for(int a=1; a<=7; a++){
                                for(int b=1; b<=2; b++){
                                    int k = getResources().getIdentifier("line"+b+"_"+a, "id",getPackageName());
                                    ImageView line = findViewById(k);
                                    line.setVisibility(View.INVISIBLE);
                                    path[a-1][b-1]=0;
                                    if(b==1&&Math.random()<0.5){
                                        line = findViewById(k);
                                        line.setVisibility(View.VISIBLE);
                                        check = false;
                                        path[a-1][b-1]=1;
                                        continue;
                                    }
                                    else if(Math.random()<0.5&&check){
                                        line = findViewById(k);
                                        line.setVisibility(View.VISIBLE);
                                        check = false;
                                        path[a-1][b-1]=1;
                                        continue;
                                    }
                                    check = true;
                                }
                            }
                            EditText name1 = (EditText)findViewById(R.id.btn1_name);
                            EditText name2 = (EditText)findViewById(R.id.btn2_name);
                            EditText name3 = (EditText)findViewById(R.id.btn3_name);
                            final EditText dst1 = (EditText)findViewById(R.id.text1_dst);
                            final EditText dst2 = (EditText)findViewById(R.id.text2_dst);
                            final EditText dst3 = (EditText)findViewById(R.id.text3_dst);

                            tab3_1.setText(name1.getText().toString());
                            tab3_2.setText(name2.getText().toString());
                            tab3_3.setText(name3.getText().toString());
                            tab3_1_dst.setText(dst1.getText().toString());
                            tab3_2_dst.setText(dst2.getText().toString());
                            tab3_3_dst.setText(dst3.getText().toString());

                            name1.setVisibility(View.GONE);
                            name2.setVisibility(View.GONE);
                            name3.setVisibility(View.GONE);

                            dst1.setVisibility(View.GONE);
                            dst2.setVisibility(View.GONE);
                            dst3.setVisibility(View.GONE);
                            name_btn.setVisibility(View.GONE);
                            tab3_1_dst.setClickable(false);
                            tab3_2_dst.setClickable(false);
                            tab3_3_dst.setClickable(false);

                            tab3_1.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {

                                    line[0] = 0;
                                    floor[0] = 0;
                                    down[0] = 0;
                                    side[0] = 0;
                                    mv.bx = dpToPx(40);
                                    mv.by = 0;
                                    mv.px = 0;
                                    mv.py = 0;
                                    mHandler.sendEmptyMessageDelayed(10,200);


                                }
                            });
                            tab3_2.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {

                                    line[0] = 1;
                                    floor[0] = 0;
                                    down[0] = 0;
                                    side[0] = 0;
                                    mv.bx = dpToPx(130);
                                    mv.by = 0;
                                    mv.px = 0;
                                    mv.py = 0;
                                    mHandler.sendEmptyMessageDelayed(10,200);


                                }
                            });
                            tab3_3.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {

                                    line[0] = 2;
                                    floor[0] = 0;
                                    down[0] = 0;
                                    side[0] = 0;
                                    mv.bx = dpToPx(220);
                                    mv.by = 0;
                                    mv.px = 0;
                                    mv.py = 0;
                                    mHandler.sendEmptyMessageDelayed(10,200);


                                }
                            });
                        }
                    });
                }
                else if(count==4){

                    tab3_1.setVisibility(View.VISIBLE);
                    name1.setVisibility(View.VISIBLE);
                    tab3_2.setVisibility(View.VISIBLE);
                    name2.setVisibility(View.VISIBLE);
                    tab3_3.setVisibility(View.VISIBLE);
                    name3.setVisibility(View.VISIBLE);
                    tab3_4.setVisibility(View.VISIBLE);
                    name4.setVisibility(View.VISIBLE);
                    tab3_5.setVisibility(View.GONE);
                    name5.setVisibility(View.GONE);

                    tab3_1_dst.setVisibility(View.VISIBLE);
                    dst1.setVisibility(View.VISIBLE);
                    tab3_2_dst.setVisibility(View.VISIBLE);
                    dst2.setVisibility(View.VISIBLE);
                    tab3_3_dst.setVisibility(View.VISIBLE);
                    dst3.setVisibility(View.VISIBLE);
                    tab3_4_dst.setVisibility(View.VISIBLE);
                    dst4.setVisibility(View.VISIBLE);
                    tab3_5_dst.setVisibility(View.GONE);
                    dst5.setVisibility(View.GONE);


                    name_btn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            for(int a=0; a<7; a++){
                                for(int b=0; b<5; b++){
                                    path[a][b]=0;
                                }
                            }
                            for(int a=1; a<=4; a++){
                                int k = getResources().getIdentifier("line"+a, "id", getPackageName());
                                LinearLayout line=findViewById(k);
                                line.setVisibility(View.VISIBLE);
                            }
                            boolean check = true;
                            for(int a=1; a<=7; a++){
                                for(int b=1; b<=3; b++){
                                    int k = getResources().getIdentifier("line"+b+"_"+a, "id",getPackageName());
                                    ImageView line = findViewById(k);
                                    line.setVisibility(View.INVISIBLE);
                                    path[a-1][b-1]=0;
                                    if(b==1&&Math.random()<0.5){
                                        line = findViewById(k);
                                        line.setVisibility(View.VISIBLE);
                                        check = false;
                                        path[a-1][b-1]=1;
                                        continue;
                                    }
                                    else if(Math.random()<0.5&&check){
                                        line = findViewById(k);
                                        line.setVisibility(View.VISIBLE);
                                        check = false;
                                        path[a-1][b-1]=1;
                                        continue;
                                    }
                                    check = true;
                                }
                            }
                            EditText name1 = (EditText)findViewById(R.id.btn1_name);
                            EditText name2 = (EditText)findViewById(R.id.btn2_name);
                            EditText name3 = (EditText)findViewById(R.id.btn3_name);
                            EditText name4 = (EditText)findViewById(R.id.btn4_name);

                            final EditText dst1 = (EditText)findViewById(R.id.text1_dst);
                            final EditText dst2 = (EditText)findViewById(R.id.text2_dst);
                            final EditText dst3 = (EditText)findViewById(R.id.text3_dst);
                            final EditText dst4 = (EditText)findViewById(R.id.text4_dst);

                            tab3_1.setText(name1.getText().toString());
                            tab3_2.setText(name2.getText().toString());
                            tab3_3.setText(name3.getText().toString());
                            tab3_4.setText(name4.getText().toString());

                            tab3_1_dst.setText(dst1.getText().toString());
                            tab3_2_dst.setText(dst2.getText().toString());
                            tab3_3_dst.setText(dst3.getText().toString());
                            tab3_4_dst.setText(dst4.getText().toString());

                            name1.setVisibility(View.GONE);
                            name2.setVisibility(View.GONE);
                            name3.setVisibility(View.GONE);
                            name4.setVisibility(View.GONE);

                            dst1.setVisibility(View.GONE);
                            dst2.setVisibility(View.GONE);
                            dst3.setVisibility(View.GONE);
                            dst4.setVisibility(View.GONE);

                            name_btn.setVisibility(View.GONE);

                            tab3_1_dst.setClickable(false);
                            tab3_2_dst.setClickable(false);
                            tab3_3_dst.setClickable(false);
                            tab3_4_dst.setClickable(false);

                            tab3_1.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {

                                    line[0] = 0;
                                    floor[0] = 0;
                                    down[0] = 0;
                                    side[0] = 0;
                                    mv.bx = dpToPx(40);
                                    mv.by = 0;
                                    mv.px = 0;
                                    mv.py = 0;
                                    mHandler.sendEmptyMessageDelayed(10,200);


                                }
                            });
                            tab3_2.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {

                                    line[0] = 1;
                                    floor[0] = 0;
                                    down[0] = 0;
                                    side[0] = 0;
                                    mv.bx = dpToPx(130);
                                    mv.by = 0;
                                    mv.px = 0;
                                    mv.py = 0;
                                    mHandler.sendEmptyMessageDelayed(10,200);


                                }
                            });
                            tab3_3.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {

                                    line[0] = 2;
                                    floor[0] = 0;
                                    down[0] = 0;
                                    side[0] = 0;
                                    mv.bx = dpToPx(220);
                                    mv.by = 0;
                                    mv.px = 0;
                                    mv.py = 0;
                                    mHandler.sendEmptyMessageDelayed(10,200);

                                }
                            });
                            tab3_4.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {

                                    line[0] = 3;
                                    floor[0] = 0;
                                    down[0] = 0;
                                    side[0] = 0;
                                    mv.bx = dpToPx(310);
                                    mv.by = 0;
                                    mv.px = 0;
                                    mv.py = 0;
                                    mHandler.sendEmptyMessageDelayed(10,200);

                                }
                            });
                        }
                    });
                }
                else if(count==5){

                    tab3_1.setVisibility(View.VISIBLE);
                    name1.setVisibility(View.VISIBLE);
                    tab3_2.setVisibility(View.VISIBLE);
                    name2.setVisibility(View.VISIBLE);
                    tab3_3.setVisibility(View.VISIBLE);
                    name3.setVisibility(View.VISIBLE);
                    tab3_4.setVisibility(View.VISIBLE);
                    name4.setVisibility(View.VISIBLE);
                    tab3_5.setVisibility(View.VISIBLE);
                    name5.setVisibility(View.VISIBLE);

                    tab3_1_dst.setVisibility(View.VISIBLE);
                    dst1.setVisibility(View.VISIBLE);
                    tab3_2_dst.setVisibility(View.VISIBLE);
                    dst2.setVisibility(View.VISIBLE);
                    tab3_3_dst.setVisibility(View.VISIBLE);
                    dst3.setVisibility(View.VISIBLE);
                    tab3_4_dst.setVisibility(View.VISIBLE);
                    dst4.setVisibility(View.VISIBLE);
                    tab3_5_dst.setVisibility(View.VISIBLE);
                    dst5.setVisibility(View.VISIBLE);


                    name_btn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            for(int a=0; a<7; a++){
                                for(int b=0; b<5; b++){
                                    path[a][b]=0;
                                }
                            }
                            for(int a=1; a<=5; a++){
                                int k = getResources().getIdentifier("line"+a, "id", getPackageName());
                                LinearLayout line=findViewById(k);
                                line.setVisibility(View.VISIBLE);
                            }
                            boolean check = true;
                            for(int a=1; a<=7; a++){
                                for(int b=1; b<=4; b++){
                                    int k = getResources().getIdentifier("line"+b+"_"+a, "id",getPackageName());
                                    ImageView line = findViewById(k);
                                    path[a-1][b-1]=0;
                                    line.setVisibility(View.INVISIBLE);
                                    if(b==1&&Math.random()<0.5){
                                        line = findViewById(k);
                                        line.setVisibility(View.VISIBLE);
                                        check = false;
                                        path[a-1][b-1]=1;
                                        continue;
                                    }
                                    else if(Math.random()<0.5&&check){
                                        line = findViewById(k);
                                        line.setVisibility(View.VISIBLE);
                                        check = false;
                                        path[a-1][b-1]=1;
                                        continue;
                                    }
                                    check = true;
                                }
                            }
                            EditText name1 = (EditText)findViewById(R.id.btn1_name);
                            EditText name2 = (EditText)findViewById(R.id.btn2_name);
                            EditText name3 = (EditText)findViewById(R.id.btn3_name);
                            EditText name4 = (EditText)findViewById(R.id.btn4_name);
                            EditText name5 = (EditText)findViewById(R.id.btn5_name);

                            final EditText dst1 = (EditText)findViewById(R.id.text1_dst);
                            final EditText dst2 = (EditText)findViewById(R.id.text2_dst);
                            final EditText dst3 = (EditText)findViewById(R.id.text3_dst);
                            final EditText dst4 = (EditText)findViewById(R.id.text4_dst);
                            final EditText dst5 = (EditText)findViewById(R.id.text5_dst);


                            tab3_1.setText(name1.getText().toString());
                            tab3_2.setText(name2.getText().toString());
                            tab3_3.setText(name3.getText().toString());
                            tab3_4.setText(name4.getText().toString());
                            tab3_5.setText(name5.getText().toString());

                            tab3_1_dst.setText(dst1.getText().toString());
                            tab3_2_dst.setText(dst2.getText().toString());
                            tab3_3_dst.setText(dst3.getText().toString());
                            tab3_4_dst.setText(dst4.getText().toString());
                            tab3_5_dst.setText(dst5.getText().toString());

                            name1.setVisibility(View.GONE);
                            name2.setVisibility(View.GONE);
                            name3.setVisibility(View.GONE);
                            name4.setVisibility(View.GONE);
                            name5.setVisibility(View.GONE);

                            dst1.setVisibility(View.GONE);
                            dst2.setVisibility(View.GONE);
                            dst3.setVisibility(View.GONE);
                            dst4.setVisibility(View.GONE);
                            dst5.setVisibility(View.GONE);

                            name_btn.setVisibility(View.GONE);

                            tab3_1_dst.setClickable(false);
                            tab3_2_dst.setClickable(false);
                            tab3_3_dst.setClickable(false);
                            tab3_4_dst.setClickable(false);
                            tab3_5_dst.setClickable(false);
                            tab3_1.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {

                                    line[0] = 0;
                                    floor[0] = 0;
                                    down[0] = 0;
                                    side[0] = 0;
                                    mv.bx = dpToPx(40);
                                    mv.by = 0;
                                    mv.px = 0;
                                    mv.py = 0;
                                    mHandler.sendEmptyMessageDelayed(10,200);

                                }
                            });
                            tab3_2.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {

                                    line[0] = 1;
                                    floor[0] = 0;
                                    down[0] = 0;
                                    side[0] = 0;
                                    mv.bx = dpToPx(130);
                                    mv.by = 0;
                                    mv.px = 0;
                                    mv.py = 0;
                                    mHandler.sendEmptyMessageDelayed(10,200);

                                }
                            });
                            tab3_3.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {

                                    line[0] = 2;
                                    floor[0] = 0;
                                    down[0] = 0;
                                    side[0] = 0;
                                    mv.bx = dpToPx(220);
                                    mv.by = 0;
                                    mv.px = 0;
                                    mv.py = 0;
                                    mHandler.sendEmptyMessageDelayed(10,200);

                                }
                            });
                            tab3_4.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {

                                    line[0] = 3;
                                    floor[0] = 0;
                                    down[0] = 0;
                                    side[0] = 0;
                                    mv.bx = dpToPx(310);
                                    mv.by = 0;
                                    mv.px = 0;
                                    mv.py = 0;
                                    mHandler.sendEmptyMessageDelayed(10,200);

                                }
                            });
                            tab3_5.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {

                                    line[0] = 4;
                                    floor[0] = 0;
                                    down[0] = 0;
                                    side[0] = 0;
                                    mv.bx = dpToPx(400);
                                    mv.by = 0;
                                    mv.px = 0;
                                    mv.py = 0;
                                    mHandler.sendEmptyMessageDelayed(10,200);


                                }
                            });
                        }
                    });
                }

            }
        });
        TabWidget tw = (TabWidget)host.findViewById(android.R.id.tabs);
        View tabView1 = tw.getChildTabViewAt(0);
        TextView tv1 = (TextView)tabView1.findViewById(android.R.id.title);
        tv1.setTextColor(getResources().getColor(R.color.white));
        tv1.setTextSize(15);

        View tabView2 = tw.getChildTabViewAt(1);
        TextView tv2 = (TextView)tabView2.findViewById(android.R.id.title);
        tv2.setTextColor(getResources().getColor(R.color.white));
        tv2.setTextSize(15);

        View tabView3 = tw.getChildTabViewAt(2);
        TextView tv3 = (TextView)tabView3.findViewById(android.R.id.title);
        tv3.setTextColor(getResources().getColor(R.color.white));
        tv3.setTextSize(15);


    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == GET_GALLERY_IMAGE && resultCode == RESULT_OK && data != null && data.getData() != null) {
            Intent intent = new Intent(getApplicationContext(), ImageGet.class);
            Uri selectedImageUri = data.getData();
            String imagePath = selectedImageUri.getPath();
            //String imagePath = getRealPathFromURI(selectedImageUri);
            /*String[] filePathColumn = { MediaStore.Images.Media.DATA };
            Cursor cursor = getContentResolver().query(selectedImageUri, filePathColumn, null, null, null);
            cursor.moveToFirst();
            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
            String picturePath = cursor.getString(columnIndex);
            cursor.close();
            Bitmap bm = BitmapFactory.decodeFile(picturePath);
            */

            intent.putExtra("image", selectedImageUri.toString());
            startActivity(intent);

        }
    }

    /*
    public String getBase64String(Bitmap bitmap){
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
        byte[] imageBytes = byteArrayOutputStream.toByteArray();
        return Base64.encodeToString(imageBytes, Base64.NO_WRAP);
    }

     */

/*
    private String getRealPathFromURI(Uri contentUri) {
        int column_index=0;
        String[] proj = {MediaStore.Images.Media.DATA};
        Cursor cursor = getContentResolver().query(contentUri, proj, null, null, null);
        if(cursor.moveToFirst()){
            column_index=cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        }
        return cursor.getString(column_index);
    }
 */

    class ImageAdapter extends BaseAdapter{
        ArrayList<ImageItem> items = new ArrayList<ImageItem>();

        @Override
        public int getCount(){
            return items.size();
        }
        public void addItem(ImageItem imageItem){
            items.add(imageItem);
        }
        @Override
        public ImageItem getItem(int i){
            return items.get(i);
        }
        @Override
        public long getItemId(int i){
            return i;
        }
        @Override
        public View getView(int i, View view, ViewGroup viewGroup){
            ImageViewer imageViewer = new ImageViewer(getApplicationContext());
            imageViewer.setImage(items.get(i));
            return imageViewer;
        }

    }


    private void jsonParsing(String json){
        list.clear();
        try{
            JSONObject jobj=new JSONObject(json);
            JSONArray jarray=jobj.getJSONArray("Phonebook");

            for(int i=0; i<jarray.length(); i++){
                JSONObject pobj=jarray.getJSONObject(i);

                Phonebook pb=new Phonebook();
                pb.setName(pobj.getString("name"));
                pb.setNumber(pobj.getString("number"));
                pb.setFriendly(pobj.getInt("friendly"));

                total+=pobj.getInt("friendly");

                list.add(pb);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void onClick(View view) {

    }
    public int dpToPx(int sizeInDP){
        int pxVal = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, sizeInDP, getResources().getDisplayMetrics());
        return pxVal;
    }

/*
    public void mOnClick(View v){
        switch (v.getId()){
            case R.id.btn1:
                mHandler=new Handler(){
                    public void handleMessage(Message msg){
                        Ladder mv = (Ladder)findViewById(R.id.mv);
                        mv.px+=mv.dx;
                        mv.py+=mv.dy;

                        mv.x1=(mv.px-mv.w/2);
                        mv.x2= (mv.px + mv.w/2);
                        mv.y1= (mv.py - mv.h/2);
                        mv.y2= (mv.py + mv.h/2);
                        mv.invalidate();
                        mHandler.sendEmptyMessageDelayed(10, 500);

                    }
                };
                mHandler.sendEmptyMessageDelayed(10,0);
                break;

            case R.id.btn2:
                mHandler.removeMessages(10);
                break;
        };


    }
*/
}