package com.example.mynote.screens.home;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.mynote.R;
import com.example.mynote.databinding.ActivityHomeScreenBinding;

public class HomeScreen extends AppCompatActivity {
    ActivityHomeScreenBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setTheme(R.style.Theme_MyNote);
        setContentView(R.layout.activity_home_screen);

        binding = ActivityHomeScreenBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        HomeFragment home = new HomeFragment();

        replaceFragment(home);

        CalendarFragment calendar = new CalendarFragment();
        FocusFragment focus = new FocusFragment();
        UserFragment user = new UserFragment();

        binding.navigation.setOnItemSelectedListener(item -> {
            switch (item.getItemId()){
                case R.id.home_screen:
                    replaceFragment(home);
                    break;
                case R.id.calendar_screen:
                    replaceFragment(calendar);
                    break;
                case R.id.focus_screen:
                    replaceFragment(focus);
                    break;
                case R.id.user_screen:
                    replaceFragment(user);
                    break;
            }
            return true;
        });
    }

    void replaceFragment(Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_container, fragment);
        fragmentTransaction.commit();
    }

}