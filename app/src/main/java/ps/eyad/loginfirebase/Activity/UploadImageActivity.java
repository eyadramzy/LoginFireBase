package ps.eyad.loginfirebase.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import ps.eyad.loginfirebase.R;

public class UploadImageActivity extends AppCompatActivity {

    private ImageView mImgUploadImage;
    private Button mBtnUploadImageCamera;
    private Button mBtnUploadImageGallary;
    private Button mBtnUploadImageUpload;
    private Button mBtnUploadImageLater;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_image);
        findView();

        mBtnUploadImageLater.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(UploadImageActivity.this,MainActivity.class));
            }
        });
    }

    private void findView() {

        mImgUploadImage = findViewById(R.id.img_uploadImage);
        mBtnUploadImageCamera = findViewById(R.id.btn_uploadImage_camera);
        mBtnUploadImageGallary = findViewById(R.id.btn_uploadImage_gallary);
        mBtnUploadImageUpload = findViewById(R.id.btn_uploadImage_upload);
        mBtnUploadImageLater = findViewById(R.id.btn_uploadImage_later);
    }
}