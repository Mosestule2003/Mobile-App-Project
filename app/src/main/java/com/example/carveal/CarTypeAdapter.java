package com.example.carveal;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class CarTypeAdapter extends FragmentStateAdapter {
    public CarTypeAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 0: {
                AllFragment all = new AllFragment();
                return all;
            }
            case 1: {
                CoupeFragment coupe = new CoupeFragment();
                return coupe;
            }
            case 2: {
                SuvFragment suv = new SuvFragment();
                return suv;
            }
            case 3: {
                PickupFragment pickup = new PickupFragment();
                return pickup;
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

