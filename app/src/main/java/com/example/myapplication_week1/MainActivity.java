package com.example.myapplication_week1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.provider.MediaStore;
import android.util.Base64;
import android.view.View;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.Button;
import android.view.ViewGroup;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity{

    ImageView image1, image2,image3,image4, image5, image6, image7, image8, image9, image10, image11, image12, image13, image14, image15, image16, image17, image18, image19, image20, image21;
    GridView gridView;
    ImageAdapter imageAdapter;
    private final int GET_GALLERY_IMAGE=200;
    Button button;
    //ImageView image;
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

        /*image = (ImageView)findViewById(R.id.mainimage);
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
        */

        spec = host.newTabSpec("tab3");
        spec.setIndicator(null, ResourcesCompat.getDrawable(getResources(), R.drawable.tab_icon3, null));
        spec.setContent(R.id.tab_content3);
        host.addTab(spec);

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

    /*
    @Override
    public void onClick(View v) {
        Intent intent = new Intent(getApplicationContext(), ImageClicked.class);
        if(v==image1){
            intent.putExtra("image", Integer.toString(R.drawable.image1));
        }
        else if(v==image2){
            intent.putExtra("image", Integer.toString(R.drawable.image2));
        }
        else if(v==image3){
            intent.putExtra("image", Integer.toString(R.drawable.image3));
        }
        else if(v==image4){
            intent.putExtra("image", Integer.toString(R.drawable.image4));
        }
        else if(v==image5){
            intent.putExtra("image", Integer.toString(R.drawable.image5));
        }
        else if(v==image6){
            intent.putExtra("image", Integer.toString(R.drawable.image6));
        }
        else if(v==image7){
            intent.putExtra("image", Integer.toString(R.drawable.image7));
        }
        else if(v==image8){
            intent.putExtra("image", Integer.toString(R.drawable.image8));
        }
        else if(v==image9){
            intent.putExtra("image", Integer.toString(R.drawable.image9));
        }
        else if(v==image10){
            intent.putExtra("image", Integer.toString(R.drawable.image10));
        }
        else if(v==image11){
            intent.putExtra("image", Integer.toString(R.drawable.image11));
        }
        else if(v==image12){
            intent.putExtra("image", Integer.toString(R.drawable.image12));
        }
        else if(v==image13){
            intent.putExtra("image", Integer.toString(R.drawable.image13));
        }
        else if(v==image14){
            intent.putExtra("image", Integer.toString(R.drawable.image14));
        }
        else if(v==image15){
            intent.putExtra("image", Integer.toString(R.drawable.image15));
        }
        else if(v==image16){
            intent.putExtra("image", Integer.toString(R.drawable.image16));
        }
        else if(v==image17){
            intent.putExtra("image", Integer.toString(R.drawable.image17));
        }
        else if(v==image18){
            intent.putExtra("image", Integer.toString(R.drawable.image18));
        }
        else if(v==image19){
            intent.putExtra("image", Integer.toString(R.drawable.image19));
        }
        else if(v==image20){
            intent.putExtra("image", Integer.toString(R.drawable.image20));
        }
        else if(v==image21){
            intent.putExtra("image", Integer.toString(R.drawable.image21));
        }
        startActivity(intent);
    }
    */

    public static void main(String[] args){
        System.out.println("Hello world");
    }
}