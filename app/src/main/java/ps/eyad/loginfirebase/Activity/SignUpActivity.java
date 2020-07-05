package ps.eyad.loginfirebase.Activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import me.relex.circleindicator.CircleIndicator;
import ps.eyad.loginfirebase.Adapter.ViewAdapter;
import ps.eyad.loginfirebase.Fragment.SignUpStep1Fragment;
import ps.eyad.loginfirebase.Fragment.SignUpStep2Fragment;
import ps.eyad.loginfirebase.Fragment.SignUpStep3Fragment;

import ps.eyad.loginfirebase.Model.ViewModel;
import ps.eyad.loginfirebase.R;

public class SignUpActivity extends AppCompatActivity implements SignUpStep1Fragment.OnFragmentstep1Listener, SignUpStep2Fragment.OnFragmentstep2Listener, SignUpStep3Fragment.OnFragmentstep3Listener {

    private ImageView mImgSplashLogo;
    private TextView mTvSplashWelcome;
    private ViewPager mViewPagerSignUp;
    private CircleIndicator mIndicator;
    String userName;
    String email;
    String password;
    String firstName;
    String lastName;
    String date;
    String gender;
    SharedPreferences sp ;
    SharedPreferences.Editor spEditor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        findView();
//        chick();
        sp = getSharedPreferences("User", MODE_PRIVATE);
        spEditor = sp.edit();

        ArrayList<ViewModel> viewModelArrayList = new ArrayList<>();
        viewModelArrayList.add(new ViewModel(new SignUpStep1Fragment()));
        viewModelArrayList.add(new ViewModel(new SignUpStep2Fragment()));
        viewModelArrayList.add(new ViewModel(new SignUpStep3Fragment()));

        ViewAdapter adapter = new ViewAdapter(getSupportFragmentManager(), viewModelArrayList);
        mViewPagerSignUp.setAdapter(adapter);
        mIndicator.setViewPager(mViewPagerSignUp);

    }

    private void findView() {
        mImgSplashLogo = findViewById(R.id.img_splash_logo);
        mTvSplashWelcome = findViewById(R.id.tv_splash_welcome);
        mViewPagerSignUp = findViewById(R.id.viewPager_signUp);
        mIndicator = findViewById(R.id.indicator);

    }


    @Override
    public void onFragmentStep1(String userName, String email, String password) {
        mViewPagerSignUp.setCurrentItem(mViewPagerSignUp.getCurrentItem() + 1);
        this.userName = userName;
        this.email = email;
        this.password = password;
    }

    @Override
    public void onFragmentStep2(String fName, String lName, String date, String gender) {
        mViewPagerSignUp.setCurrentItem(mViewPagerSignUp.getCurrentItem() + 1);
        this.firstName = fName;
        this.lastName = lName;
        this.date = date;
        this.gender = gender;
    }

    @Override
    public void onFragmentStep3(final String phoneNumber, final String key) {
        StringRequest request = new StringRequest(Request.Method.POST, "http://aaqsoftware.com/aaq_APIs/Auth/aaqreg.php", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject object = new JSONObject(response);
                    if (object.getString("message").equals("Register Success")){
                        startActivity(new Intent(SignUpActivity.this,UploadImageActivity.class));
                        spEditor.putString("email", email);
                        spEditor.putString("password", password);
                        spEditor.apply();
                        spEditor.commit();
                        finish();
                    }
                    else {
                        Toast.makeText(SignUpActivity.this, ""+object.getString("error"), Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String, String> map = new HashMap<>();
                map.put("email", email);
                map.put("password",password );
                map.put("appID", "com.unitone.android.assignment");
                map.put("phone",phoneNumber );
                map.put("phoneKey",key );
                map.put("firstName", firstName);
                map.put("lastName", lastName);
                map.put("birthDate",date );
                return map;
            }
        };
        Volley.newRequestQueue(getApplicationContext()).add(request);
    }


}