package ps.eyad.loginfirebase.Fragment;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.arch.core.executor.TaskExecutor;
import androidx.fragment.app.Fragment;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskExecutors;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import ps.eyad.loginfirebase.Adapter.PhoneAdapter;
import ps.eyad.loginfirebase.Interface.phoneListener;
import ps.eyad.loginfirebase.Model.phoneKey;
import ps.eyad.loginfirebase.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SignUpStep3Fragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SignUpStep3Fragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private OnFragmentstep3Listener mListener;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private EditText mEtSignUpMobileNumber;
    private TextView mTvSignUpUpdate;
    private TextView mTvSignUpResend;
    private EditText mEtSignUp6digits;
    private Button mBtnSignUpRegister;
    private Button mBtnSignUpSubmit;
    private TextView mTvSignUpVerification;
    private Spinner mSpSignUpPhoneKey;
    String key;
    String phoneNumber;
    private String VerificationId;
    private FirebaseAuth mAuth ;

    public SignUpStep3Fragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SignUpStep3Fragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SignUpStep3Fragment newInstance(String param1, String param2) {
        SignUpStep3Fragment fragment = new SignUpStep3Fragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_sign_up_step3, container, false);
        mEtSignUpMobileNumber = v.findViewById(R.id.et_signUp_mobileNumber);
        mTvSignUpUpdate = v.findViewById(R.id.tv_signUp_update);
        mTvSignUpResend = v.findViewById(R.id.tv_signUp_resend);
        mEtSignUp6digits = v.findViewById(R.id.et_signUp_6digits);
        mBtnSignUpRegister = v.findViewById(R.id.btn_signUp_register);
        mBtnSignUpSubmit = v.findViewById(R.id.btn_signUp_submit);
        mTvSignUpVerification = v.findViewById(R.id.tv_signUp_verification);
        mSpSignUpPhoneKey = v.findViewById(R.id.sp_signUp_phoneKey);
        mAuth = FirebaseAuth.getInstance();

        mBtnSignUpSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                phoneNumber  = mEtSignUpMobileNumber.getText().toString();
                submit();
                SendVerificationCode();
            }
        });
        mBtnSignUpRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              String code =  mEtSignUp6digits.getText().toString();
              if (code.isEmpty() || code.length()<6){
                  Toast.makeText(getActivity(), "Enter Code", Toast.LENGTH_SHORT).show();
                  return;
              }
                verifyCode(code);

            }
        });

        ArrayList<phoneKey> phoneKeys = new ArrayList<>();
        phoneKeys.add(new phoneKey(1,"+970","Palestine",R.drawable.falg));
        PhoneAdapter adapter = new PhoneAdapter(phoneKeys);
        adapter.setListener(new phoneListener() {
            @Override
            public void onClick(phoneKey phoneKey) {
             key =    phoneKey.getKey();
            }
        });
        mSpSignUpPhoneKey.setAdapter(adapter);

        return v;

    }

    private void verifyCode(String code){
        PhoneAuthCredential credential = PhoneAuthProvider.getCredential(VerificationId,code);
        SignInWhithCerdentail(credential);
    }

    private void SignInWhithCerdentail(PhoneAuthCredential credential) {
        phoneNumber  = mEtSignUpMobileNumber.getText().toString();
        mListener.onFragmentStep3(phoneNumber,key);
//        mAuth.signInWithCredential(credential).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
//            @Override
//            public void onComplete(@NonNull Task<AuthResult> task) {
//            if (task.isSuccessful()){
//
//            }else {
//                Toast.makeText(getActivity(), ""+task.getException().getMessage(), Toast.LENGTH_SHORT).show();
//            }
//            }
//        });
    }

    private void submit() {
        mBtnSignUpSubmit.setVisibility(View.GONE);
        mEtSignUpMobileNumber.setVisibility(View.VISIBLE);
        mTvSignUpUpdate.setVisibility(View.VISIBLE);
        mTvSignUpResend.setVisibility(View.VISIBLE);
        mEtSignUp6digits.setVisibility(View.VISIBLE);
        mBtnSignUpRegister.setVisibility(View.VISIBLE);
        mTvSignUpVerification.setVisibility(View.VISIBLE);
    }
    private void SendVerificationCode(){
        Toast.makeText(getActivity(), ""+key+phoneNumber, Toast.LENGTH_SHORT).show();
        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                (key+phoneNumber),
                60,
                TimeUnit.SECONDS,
                TaskExecutors.MAIN_THREAD,
                mCallBack
        );
    }
    private PhoneAuthProvider.OnVerificationStateChangedCallbacks
            mCallBack = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
        @Override
        public void onCodeSent(@NonNull String s, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
            super.onCodeSent(s, forceResendingToken);
            VerificationId = s;
        }

        @Override
        public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
        String code = phoneAuthCredential.getSmsCode();
        if (code!=null){
            verifyCode(code);
        }
        }

        @Override
        public void onVerificationFailed(@NonNull FirebaseException e) {
            Toast.makeText(getActivity(), ""+e.getMessage(), Toast.LENGTH_SHORT).show();
            Log.e("Code",e.getMessage());
        }
    };


    public void onButtonPressed(String phoneNumber, String key) {
        if (mListener != null) {
            mListener.onFragmentStep3(phoneNumber, key);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentstep3Listener) {
            mListener = (OnFragmentstep3Listener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentstep3Listener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }


    public interface OnFragmentstep3Listener {
        // TODO: Update argument type and name
        void onFragmentStep3(String phoneNumber, String key);
    }
}