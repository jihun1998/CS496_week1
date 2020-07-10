package com.example.myapplication_week1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;

import android.content.Intent;
//import android.database.Cursor;
//import android.graphics.Bitmap;
//import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
//import android.provider.ContactsContract;
import android.provider.MediaStore;
//import android.util.Base64;
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
import java.util.ArrayList;
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
    int dx=10;
    int dy=0;
    int x1,y1,x2,y2;

    @Override
    public void onDraw(Canvas c){
        Paint paint = new Paint();
        paint.setColor(Color.DKGRAY);
        int bx = getWidth()/2;
        int by = getHeight()/2;
        c.drawRect(x1+bx,y1+by,x2+bx,y2+by, paint);

    }
}

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    //ImageView image1, image2,image3,image4, image5, image6, image7, image8, image9, image10, image11, image12, image13, image14, image15, image16, image17, image18, image19, image20, image21;
    GridView gridView;
    ImageAdapter imageAdapter;
    private final int GET_GALLERY_IMAGE=200;
    Button button, tab3_btn, tab3_1, tab3_2, tab3_3, tab3_4, tab3_5, name_btn ;
    Button tab3_1_dst, tab3_2_dst, tab3_3_dst, tab3_4_dst, tab3_5_dst;

    //ImageView image;

    PbAdapter adapter=null;
    ArrayList<Phonebook> list=null;

    Handler mHandler;
    int ct=0;

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

        //BUTTON
        Button addbt=(Button)findViewById(R.id.btn);
        addbt.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(), Add.class);
                startActivity(intent);
            }
        });

        //LISTVIEW
        ListView listview = (ListView)findViewById(R.id.pb_listview);
        list = new ArrayList<Phonebook>();

        String json=getJsonString();
        Phonebook pb=new Phonebook(json);
        for (int i = 0; i < pb.getList().size(); i++) {
            list.add(pb.getList().get(i));
        }

        adapter = new PbAdapter(this,R.layout.pb_item, list);
        adapter.notifyDataSetChanged();
        listview.setAdapter(adapter);

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView adapterView, View view, int position, long id) {
                Intent intent=new Intent(getApplicationContext(),Next.class);

                intent.putExtra("name", list.get(position).getName());
                intent.putExtra("number", list.get(position).getNumber());
                startActivity(intent);
            }
        });

        spec = host.newTabSpec("tab2");
        spec.setIndicator(null, ResourcesCompat.getDrawable(getResources(), R.drawable.tab_icon2, null));
        spec.setContent(R.id.tab_content2);
        host.addTab(spec);

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

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getApplicationContext(), ImageClicked.class);

                intent.putExtra("image", Integer.toString((imageAdapter.getItem(i).getImage())));
                startActivity(intent);

            }
        });

        spec = host.newTabSpec("tab3");
        spec.setIndicator(null, ResourcesCompat.getDrawable(getResources(), R.drawable.tab_icon3, null));
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


        name_btn = (Button)findViewById(R.id.btn_name);
        tab3_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText num = (EditText)findViewById(R.id.num);
                int count = Integer.parseInt(num.getText().toString());
                name_btn.setVisibility(View.VISIBLE);
                EditText name1 = (EditText)findViewById(R.id.btn1_name);
                EditText name2 = (EditText)findViewById(R.id.btn2_name);
                EditText name3 = (EditText)findViewById(R.id.btn3_name);
                EditText name4 = (EditText)findViewById(R.id.btn4_name);
                EditText name5 = (EditText)findViewById(R.id.btn5_name);

                EditText dst1 = (EditText)findViewById(R.id.text1_dst);
                EditText dst2 = (EditText)findViewById(R.id.text2_dst);
                EditText dst3 = (EditText)findViewById(R.id.text3_dst);
                EditText dst4 = (EditText)findViewById(R.id.text4_dst);
                EditText dst5 = (EditText)findViewById(R.id.text5_dst);

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
                                    if(b==1&&Math.random()<0.5){
                                        line = findViewById(k);
                                        line.setVisibility(View.VISIBLE);
                                        check = false;
                                        continue;
                                    }
                                    else if(Math.random()<0.5&&check){
                                        line = findViewById(k);
                                        line.setVisibility(View.VISIBLE);
                                        check = false;
                                        continue;
                                    }
                                    check = true;
                                }
                            }
                            EditText name1 = (EditText)findViewById(R.id.btn1_name);
                            EditText name2 = (EditText)findViewById(R.id.btn2_name);
                            EditText dst1 = (EditText)findViewById(R.id.text1_dst);
                            EditText dst2 = (EditText)findViewById(R.id.text2_dst);

                            tab3_1.setText(name1.getText().toString());
                            tab3_2.setText(name2.getText().toString());
                            tab3_1_dst.setText(dst1.getText().toString());
                            tab3_2_dst.setText(dst2.getText().toString());
                            name1.setVisibility(View.GONE);
                            name2.setVisibility(View.GONE);
                            dst1.setVisibility(View.GONE);
                            dst2.setVisibility(View.GONE);
                            name_btn.setVisibility(View.GONE);
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
                                    if(b==1&&Math.random()<0.5){
                                        line = findViewById(k);
                                        line.setVisibility(View.VISIBLE);
                                        check = false;
                                        continue;
                                    }
                                    else if(Math.random()<0.5&&check){
                                        line = findViewById(k);
                                        line.setVisibility(View.VISIBLE);
                                        check = false;
                                        continue;
                                    }
                                    check = true;
                                }
                            }
                            EditText name1 = (EditText)findViewById(R.id.btn1_name);
                            EditText name2 = (EditText)findViewById(R.id.btn2_name);
                            EditText name3 = (EditText)findViewById(R.id.btn3_name);
                            EditText dst1 = (EditText)findViewById(R.id.text1_dst);
                            EditText dst2 = (EditText)findViewById(R.id.text2_dst);
                            EditText dst3 = (EditText)findViewById(R.id.text3_dst);

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
                                    if(b==1&&Math.random()<0.5){
                                        line = findViewById(k);
                                        line.setVisibility(View.VISIBLE);
                                        check = false;
                                        continue;
                                    }
                                    else if(Math.random()<0.5&&check){
                                        line = findViewById(k);
                                        line.setVisibility(View.VISIBLE);
                                        check = false;
                                        continue;
                                    }
                                    check = true;
                                }
                            }
                            EditText name1 = (EditText)findViewById(R.id.btn1_name);
                            EditText name2 = (EditText)findViewById(R.id.btn2_name);
                            EditText name3 = (EditText)findViewById(R.id.btn3_name);
                            EditText name4 = (EditText)findViewById(R.id.btn4_name);

                            EditText dst1 = (EditText)findViewById(R.id.text1_dst);
                            EditText dst2 = (EditText)findViewById(R.id.text2_dst);
                            EditText dst3 = (EditText)findViewById(R.id.text3_dst);
                            EditText dst4 = (EditText)findViewById(R.id.text4_dst);

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
                                    line.setVisibility(View.INVISIBLE);
                                    if(b==1&&Math.random()<0.5){
                                        line = findViewById(k);
                                        line.setVisibility(View.VISIBLE);
                                        check = false;
                                        continue;
                                    }
                                    else if(Math.random()<0.5&&check){
                                        line = findViewById(k);
                                        line.setVisibility(View.VISIBLE);
                                        check = false;
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

                            EditText dst1 = (EditText)findViewById(R.id.text1_dst);
                            EditText dst2 = (EditText)findViewById(R.id.text2_dst);
                            EditText dst3 = (EditText)findViewById(R.id.text3_dst);
                            EditText dst4 = (EditText)findViewById(R.id.text4_dst);
                            EditText dst5 = (EditText)findViewById(R.id.text5_dst);


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
                        }
                    });
                }

            }
        });




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


    private String getJsonString()
    {
        String json = "";

        try {
            InputStream is = getAssets().open("db.json");
            int fileSize = is.available();

            byte[] buffer = new byte[fileSize];
            is.read(buffer);
            is.close();

            json = new String(buffer, "UTF-8");
        }
        catch (IOException ex)
        {
            ex.printStackTrace();
        }

        return json;
    }

    @Override
    public void onClick(View view) {

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