package com.example.myapplication_week1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;

import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageView;
import android.widget.Button;

import java.io.IOException;

public class ImageGet extends AppCompatActivity implements View.OnClickListener{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.image_clicked_layout);

        Intent intent = getIntent();
        ImageView image = (ImageView)findViewById(R.id.image);
        Button button = (Button)findViewById(R.id.back);
        button.setOnClickListener(this);
        Uri uri = Uri.parse(intent.getStringExtra("image"));
        /*
        String imagePath = intent.getStringExtra("image");
        ExifInterface exif = null;
        try{
            exif = new ExifInterface(imagePath);
        }
        catch(IOException e){
            e.printStackTrace();
        }
        int exifDegree;
        if(exif!=null) {
            int exifOrientation = exif.getAttributeInt(ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_NORMAL);
            exifDegree = exifOrientationToDegrees(exifOrientation);
        }
        else{
            exifDegree = 0;
        }
        Bitmap bitmap = BitmapFactory.decodeFile(imagePath);

        image.setImageBitmap(rotate(bitmap, exifDegree));

         */
        image.setImageURI(uri);
        Drawable d = image.getDrawable();

        Bitmap bitmap = ((BitmapDrawable)d).getBitmap();

        image.setImageBitmap(rotate(bitmap, 90));
        //image.setImageURI(uri);

    }
    private int exifOrientationToDegrees(int exifOrientation){
        if (exifOrientation == ExifInterface.ORIENTATION_ROTATE_90){
            return 90;
        }
        else if (exifOrientation == ExifInterface.ORIENTATION_ROTATE_180){
            return 180;
        }
        else if (exifOrientation == ExifInterface.ORIENTATION_ROTATE_270){
            return 270;
        }
        return 0;
    }

    private Bitmap rotate(Bitmap src, float degree){
        Matrix matrix = new Matrix();
        matrix.postRotate(degree);
        return Bitmap.createBitmap(src,0,0,src.getWidth(),src.getHeight(),matrix,true);
    }

    @Override
    public void onClick(View v){
        Intent mainintent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(mainintent);
        finishAffinity();

    }
}
