package ps.eyad.loginfirebase.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import ps.eyad.loginfirebase.Model.Data;
import ps.eyad.loginfirebase.R;

public class DataAdapter extends RecyclerView.Adapter<DataAdapter.DataViewHolder> {

    ArrayList<Data> DataList;

    public DataAdapter(ArrayList<Data> DataList) {
        this.DataList = DataList;
    }

    @NonNull
    @Override
    public DataViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);
        DataViewHolder holder = new DataViewHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull DataViewHolder holder, int position) {
        Data data = DataList.get(position);
        holder.onBind(data);
    }

    @Override
    public int getItemCount() {
        return DataList.size();
    }

    class DataViewHolder extends RecyclerView.ViewHolder {
        Data data;
        private ImageView mImgItem;
        private ImageView mImgItemUser;
        private TextView mTvItemUserName;
        private TextView mTvItemTime;

        public DataViewHolder(@NonNull View itemView) {
            super(itemView);
            mImgItem = itemView.findViewById(R.id.img_item);
            mImgItemUser = itemView.findViewById(R.id.img_item_user);
            mTvItemUserName = itemView.findViewById(R.id.tv_item_userName);
            mTvItemTime = itemView.findViewById(R.id.tv_item_time);
        }

        public void onBind(Data data) {
            this.data = data;
        }
    }
}
