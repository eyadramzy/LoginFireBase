package ps.eyad.loginfirebase.Model;

import androidx.fragment.app.Fragment;

public class ViewModel {

    private Fragment fragment ;


    public ViewModel(Fragment fragment) {
        this.fragment = fragment;

    }

    public Fragment getFragment() {
        return fragment;
    }

    public void setFragment(Fragment fragment) {
        this.fragment = fragment;
    }


}
