package com.example.todotask;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.navigation.NavigationView;

public class MenuFragment extends Fragment {

    NavigationView navigationView ;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View root =  inflater.inflate(R.layout.fragment_menu, container, false);
        navigationView = root.findViewById(R.id.side_nav_menu);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                int i = menuItem.getItemId();
                if(i == R.id.side_nav_help)
                {
                    replaceFragment(new HelpFragment());
                }
                else if(i == R.id.side_nav_bin)
                {
                    replaceFragment(new BinFragment());
                }
                else if(i == R.id.side_nav_find)
                {
                    replaceFragment(new FindFragment());
                }
                else if(i == R.id.side_nav_upgrade)
                {

                }
                else if(i == R.id.side_nav_rate)
                {
                    replaceFragment(new RateFragment());
                }
                else if(i == R.id.side_nav_setting)
                {
                    replaceFragment(new SettingFragment());
                }

                return false;
            }
        });
        return  root;
    }
    private void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout, fragment);
        fragmentTransaction.commit();
    }
}