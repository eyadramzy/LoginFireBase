package ps.eyad.loginfirebase.Activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import ps.eyad.loginfirebase.R;

public class SignInActivity extends AppCompatActivity {

    private ImageView mImgSignInLogo;
    private TextView mTvSignInWelcome;
    private EditText mEtSignInEmail;
    private EditText mEtSignInPassword;
    private Button mBtnSignInSignIn;
    SharedPreferences sp ;
    SharedPreferences.Editor spEditor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        findView();
        sp = getSharedPreferences("User", MODE_PRIVATE);
        spEditor = sp.edit();
        mBtnSignInSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mEtSignInEmail.getText().toString().isEmpty()){
                    Toast.makeText(SignInActivity.this, "Enter Email", Toast.LENGTH_SHORT).show();
                    return;
                }else if (mEtSignInPassword.getText().toString().isEmpty()){
                    Toast.makeText(SignInActivity.this, "Enter Password", Toast.LENGTH_SHORT).show();
                    return;
                }
                StringRequest request = new StringRequest(Request.Method.POST, "http://aaqsoftware.com/aaq_APIs/Auth/login.php", new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject object =new JSONObject(response);
                            if (object.getString("message").equals("Login Success")){
                                startActivity(new Intent(SignInActivity.this,MainActivity.class));
                                spEditor.putString("email", mEtSignInEmail.getText().toString());
                                spEditor.putString("password", mEtSignInPassword.getText().toString());
                                spEditor.apply();
                                spEditor.commit();
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }){
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String, String> map = new HashMap<>();
                        map.put("email",mEtSignInEmail.getText().toString());
                        map.put("password",mEtSignInPassword.getText().toString());
                        map.put("appID","com.unitone.android.assignment");
                        return map;
                    }
                };

                Volley.newRequestQueue(getApplicationContext()).add(request);
            }
        });
    }

    private void findView() {

        mImgSignInLogo = findViewById(R.id.img_SignIn_logo);
        mTvSignInWelcome = findViewById(R.id.tv_SignIn_welcome);
        mEtSignInEmail = findViewById(R.id.et_SignIn_email);
        mEtSignInPassword = findViewById(R.id.et_SignIn_password);
        mBtnSignInSignIn = findViewById(R.id.btn_SignIn_SignIn);
    }
}