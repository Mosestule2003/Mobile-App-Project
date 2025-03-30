package com.example.carveal;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class FragAdapter extends FragmentStateAdapter {
    public FragAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 0: {
                HomeFragment home = new HomeFragment();
                return home;
            }
            case 1: {
                MessFragment mess = new MessFragment();
                return mess;
            }
            case 2: {
                NotiFragment noti = new NotiFragment();
                return noti;
            }
            case 3: {
                SetFragment set = new SetFragment();
                return set;
            }
            default: {
                return null;
            }
        }
    }

    @Override
    public int getItemCount() {
        return 4;
    }
}
