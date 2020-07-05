package ps.eyad.loginfirebase.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.SpinnerAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import ps.eyad.loginfirebase.Interface.phoneListener;
import ps.eyad.loginfirebase.Model.phoneKey;
import ps.eyad.loginfirebase.R;

public class PhoneAdapter extends BaseAdapter {

    ArrayList<phoneKey> phoneKeyList;
    phoneListener listener;

    public PhoneAdapter(ArrayList<phoneKey> phoneKeyList) {
        this.phoneKeyList = phoneKeyList;
    }

    public phoneListener getListener() {
        return listener;
    }

    public void setListener(phoneListener listener) {
        this.listener = listener;
    }

//    @NonNull
//    @Override
//    public PhoneViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_phone_key, parent, false);
//        PhoneViewHolder holder = new PhoneViewHolder(v);
//        return holder;
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull PhoneViewHolder holder, int position) {
//        phoneKey phoneKey = phoneKeyList.get(position);
//        holder.onBind(phoneKey);
//    }
//
//    @Override
//    public int getItemCount() {
//        return phoneKeyList.size();
//    }

    @Override
    public int getCount() {
        return phoneKeyList.size();
    }

    @Override
    public Object getItem(int i) {
        return phoneKeyList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_phone_key, viewGroup, false);
        ImageView mImgItemPhoneFlag = v.findViewById(R.id.img_itemPhone_flag);
        TextView mTvItemPhoneKey = v.findViewById(R.id.tv_itemPhone_key);

        final phoneKey phoneKey = phoneKeyList.get(i);
        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onClick(phoneKey);
            }
        });
        mImgItemPhoneFlag.setImageResource(phoneKey.getFlag());
        mTvItemPhoneKey.setText(phoneKey.getCountryName()+"["+phoneKey.getKey()+"]");
        return v;
    }

}
