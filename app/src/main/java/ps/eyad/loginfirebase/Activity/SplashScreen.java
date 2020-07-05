package ps.eyad.loginfirebase.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import ps.eyad.loginfirebase.R;

public class SplashScreen extends AppCompatActivity {

    private Button mBtnSplashSignIn;
    private Button mBtnSplashSignUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        findView();
        mBtnSplashSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SplashScreen.this,SignInActivity.class));

            }
        });
        mBtnSplashSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SplashScreen.this, SignUpActivity.class));

            }
        });
    }

    private void findView() {

        mBtnSplashSignIn = findViewById(R.id.btn_splash_SignIn);
        mBtnSplashSignUp = findViewById(R.id.btn_splash_SignUp);
    }
}