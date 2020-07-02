package ps.eyad.loginfirebase.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import java.util.ArrayList;

import me.relex.circleindicator.CircleIndicator;
import ps.eyad.loginfirebase.Adapter.ViewAdapter;
import ps.eyad.loginfirebase.Fragment.SignUpStep1Fragment;
import ps.eyad.loginfirebase.Fragment.SignUpStep2Fragment;
import ps.eyad.loginfirebase.Fragment.SignUpStep3Fragment;
import ps.eyad.loginfirebase.Fragment.SignUpStep4Fragment;
import ps.eyad.loginfirebase.Model.ViewModel;
import ps.eyad.loginfirebase.R;

public class SignUpActivity extends AppCompatActivity {

    private ImageView mImgSplashLogo;
    private TextView mTvSplashWelcome;
    private ViewPager mViewPagerSignUp;
    private CircleIndicator mIndicator;
    private Button mBtnSignUpNext;
    private Button mBtnSignUpSignIn;
    private Button mBtnSignUpSubmit;
    private Button mBtnSignUpRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        findView();
        chick();

        ArrayList<ViewModel> viewModelArrayList = new ArrayList<>();
        viewModelArrayList.add(new ViewModel(new SignUpStep1Fragment()));
        viewModelArrayList.add(new ViewModel(new SignUpStep2Fragment()));
        viewModelArrayList.add(new ViewModel(new SignUpStep3Fragment()));
        viewModelArrayList.add(new ViewModel(new SignUpStep4Fragment()));

        ViewAdapter adapter = new ViewAdapter(getSupportFragmentManager(), viewModelArrayList);
        mViewPagerSignUp.setAdapter(adapter);
        mIndicator.setViewPager(mViewPagerSignUp);
        mBtnSignUpNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mViewPagerSignUp.setCurrentItem(mViewPagerSignUp.getCurrentItem() + 1);
                chick();
            }
        });

        mBtnSignUpSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SignUpActivity.this,SignInActivity.class));
            }
        });

        mBtnSignUpSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mViewPagerSignUp.setCurrentItem(mViewPagerSignUp.getCurrentItem() + 1);
                chick();
            }
        });

        mBtnSignUpRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SignUpActivity.this,UploadImageActivity.class));
            }
        });

    }

    private void findView() {
        mImgSplashLogo = findViewById(R.id.img_splash_logo);
        mTvSplashWelcome = findViewById(R.id.tv_splash_welcome);
        mViewPagerSignUp = findViewById(R.id.viewPager_signUp);
        mIndicator = findViewById(R.id.indicator);
        mBtnSignUpNext = findViewById(R.id.btn_signUp_next);
        mBtnSignUpSignIn = findViewById(R.id.btn_signUp_SignIn);
        mBtnSignUpSubmit = findViewById(R.id.btn_signUp_submit);
        mBtnSignUpRegister = findViewById(R.id.btn_signUp_register);
    }

    private void chick() {
        if (mViewPagerSignUp.getCurrentItem() == 2) {
            mBtnSignUpNext.setVisibility(View.GONE);
            mBtnSignUpSubmit.setVisibility(View.VISIBLE);
        } else {
            mBtnSignUpNext.setVisibility(View.VISIBLE);
            mBtnSignUpSubmit.setVisibility(View.GONE);
        }
        if (mViewPagerSignUp.getCurrentItem() == 0) {
            mBtnSignUpSignIn.setVisibility(View.VISIBLE);
        } else {
            mBtnSignUpSignIn.setVisibility(View.GONE);
        }

        if(mViewPagerSignUp.getCurrentItem() == 3){
            mBtnSignUpRegister.setVisibility(View.VISIBLE);
            mBtnSignUpSignIn.setVisibility(View.GONE);
            mBtnSignUpSubmit.setVisibility(View.GONE);
            mBtnSignUpNext.setVisibility(View.GONE);
        }
    }
}