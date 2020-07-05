package ps.eyad.loginfirebase.Activity;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import ps.eyad.loginfirebase.R;

public class UploadImageActivity extends AppCompatActivity {

    private static final int CAMERA_PIC_REQUEST = 1000;
    private static final int SELECT_IMAGE = 2000;
    private ImageView mImgUploadImage;
    private Button mBtnUploadImageCamera;
    private Button mBtnUploadImageGallary;
    private Button mBtnUploadImageUpload;
    private Button mBtnUploadImageLater;
    Uri filePath;
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

        mBtnUploadImageCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getPermission();
            }
        });
        mBtnUploadImageGallary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Select Picture"),SELECT_IMAGE);
            }
        });
        mBtnUploadImageUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                StringRequest request = new StringRequest(Request.Method.POST, "http://aaqsoftware.com/aaq_APIs/Auth/submitImage.php", new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        startActivity(new Intent(UploadImageActivity.this,MainActivity.class));
                        finish();
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }){
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        HashMap<String,String> map = new HashMap();
                        map.put("uplodedImage",filePath+"");
                        map.put("appID","com.unitone.android.assignment");
                        return map;
                    }
                };
                Volley.newRequestQueue(getApplicationContext()).add(request);
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CAMERA_PIC_REQUEST) {
            Bitmap photo = (Bitmap) data.getExtras().get("data");
            mImgUploadImage.setImageBitmap(photo);
        }
        if (requestCode == SELECT_IMAGE && resultCode == RESULT_OK && data != null && data.getData() != null) {
            filePath = data.getData();
            Toast.makeText(this, "filePath: " + filePath, Toast.LENGTH_SHORT).show();
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(),
                        filePath);
                mImgUploadImage.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    private void getPermission(){
        Dexter.withContext(getApplication()).withPermission(Manifest.permission.CAMERA).withListener(new PermissionListener() {
            @Override
            public void onPermissionGranted(PermissionGrantedResponse permissionGrantedResponse) {
                Toast.makeText(UploadImageActivity.this, "Permission Granted"+" "+permissionGrantedResponse.getPermissionName(), Toast.LENGTH_SHORT).show();
                Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
                startActivityForResult(intent, CAMERA_PIC_REQUEST);
            }

            @Override
            public void onPermissionDenied(PermissionDeniedResponse permissionDeniedResponse) {
                Toast.makeText(UploadImageActivity.this, "Permission Denied"+" "+permissionDeniedResponse.getPermissionName(), Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onPermissionRationaleShouldBeShown(PermissionRequest permissionRequest, PermissionToken permissionToken) {
                permissionToken.continuePermissionRequest();
            }
        }).check();
    }

}