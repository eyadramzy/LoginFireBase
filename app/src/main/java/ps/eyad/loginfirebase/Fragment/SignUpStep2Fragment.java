package ps.eyad.loginfirebase.Fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.fragment.app.Fragment;

import ps.eyad.loginfirebase.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SignUpStep2Fragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SignUpStep2Fragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private OnFragmentstep2Listener mListener;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private EditText mEtSignUpFirstName;
    private EditText mEtSignUpLastName;
    private EditText mEtSignUpDate;
    private Spinner mSpSignUpGender;
    private Button mBtnSignUp3Next;

    public SignUpStep2Fragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SignUpStep2Fragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SignUpStep2Fragment newInstance(String param1, String param2) {
        SignUpStep2Fragment fragment = new SignUpStep2Fragment();
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
        View v = inflater.inflate(R.layout.fragment_sign_up_step2, container, false);
        mEtSignUpFirstName = v.findViewById(R.id.et_signUp_FirstName);
        mEtSignUpLastName = v.findViewById(R.id.et_signUp_LastName);
        mEtSignUpDate = v.findViewById(R.id.et_signUp_date);
        mSpSignUpGender = v.findViewById(R.id.sp_signUp_gender);
        mBtnSignUp3Next = v.findViewById(R.id.btn_signUp3_next);
        mBtnSignUp3Next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               String fName= mEtSignUpFirstName.getText().toString();
                String lName= mEtSignUpLastName.getText().toString();
                String date=mEtSignUpDate.getText().toString();
                String gender;
                if (mSpSignUpGender.getSelectedItemPosition() == 1) {
                    gender = "Male";
                } else {
                    gender = "Female";
                }
                mListener.onFragmentStep2(fName,lName,date,gender);
            }
        });
        return v;

    }


    public void onButtonPressed(String fName, String lName, String date, String gender) {
        if (mListener != null) {
            mListener.onFragmentStep2(fName, lName, date, gender);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentstep2Listener) {
            mListener = (OnFragmentstep2Listener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentstep2Listener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }


    public interface OnFragmentstep2Listener {
        // TODO: Update argument type and name
        void onFragmentStep2(String fName, String lName, String date, String gender);
    }
}