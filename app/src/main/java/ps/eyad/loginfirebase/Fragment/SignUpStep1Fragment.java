package ps.eyad.loginfirebase.Fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.fragment.app.Fragment;

import ps.eyad.loginfirebase.Activity.SignInActivity;
import ps.eyad.loginfirebase.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SignUpStep1Fragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SignUpStep1Fragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private EditText mEtSignUpUserName;
    private EditText mEtSignUpEmail;
    private EditText mEtSignUpPassword;
    private Button mBtnSignUp2Next;
    private Button mBtnSignUp2SignIn;
    private OnFragmentstep1Listener mListener;

    public SignUpStep1Fragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SignUpStep1Fragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SignUpStep1Fragment newInstance(String param1, String param2) {
        SignUpStep1Fragment fragment = new SignUpStep1Fragment();
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
        View v = inflater.inflate(R.layout.fragment_sign_up_step1, container, false);
        mEtSignUpUserName = v.findViewById(R.id.et_signUp_UserName);
        mEtSignUpEmail = v.findViewById(R.id.et_signUp_email);
        mEtSignUpPassword = v.findViewById(R.id.et_signUp_password);
        mBtnSignUp2Next = v.findViewById(R.id.btn_signUp2_next);
        mBtnSignUp2SignIn = v.findViewById(R.id.btn_signUp2_SignIn);

        mBtnSignUp2Next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userName = mEtSignUpUserName.getText().toString();
                String email = mEtSignUpEmail.getText().toString();
                String password = mEtSignUpPassword.getText().toString();
                mListener.onFragmentStep1(userName,email,password);
            }
        });
        mBtnSignUp2SignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), SignInActivity.class));
                getActivity().finish();
            }
        });
        return v;

    }

    public void onButtonPressed(String userName, String email, String password) {
        if (mListener != null) {
            mListener.onFragmentStep1(userName, email, password);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentstep1Listener) {
            mListener = (OnFragmentstep1Listener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentstep1Listener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }


    public interface OnFragmentstep1Listener {
        // TODO: Update argument type and name
        void onFragmentStep1(String userName, String email, String password);
    }

}