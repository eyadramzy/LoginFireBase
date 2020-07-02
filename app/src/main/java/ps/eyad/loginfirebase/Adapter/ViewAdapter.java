package ps.eyad.loginfirebase.Adapter;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import java.util.ArrayList;

import ps.eyad.loginfirebase.Model.ViewModel;

public class ViewAdapter extends FragmentStatePagerAdapter {

    ArrayList<ViewModel> viewModelArrayList;

    public ViewAdapter(FragmentManager fm, ArrayList<ViewModel> viewModelArrayList) {
        super(fm);
        this.viewModelArrayList = viewModelArrayList;
    }

    @Override
    public Fragment getItem(int position) {
        return viewModelArrayList.get(position).getFragment();
    }

    @Override
    public int getCount() {
        return viewModelArrayList.size();
    }


}
