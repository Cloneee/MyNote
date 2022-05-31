package com.example.mynote.screens.home;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mynote.MainActivity;
import com.example.mynote.R;
import com.example.mynote.configs.Constant;
import com.example.mynote.databinding.FragmentUserBinding;
import com.example.mynote.models.UserType;
import com.example.mynote.screens.register.ChangePassword;

public class UserFragment extends Fragment {

    private FragmentUserBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentUserBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initData();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    void initData(){
        binding.actionButton.setOnClickListener(view -> {
            MainActivity.logout(getContext());
        });
        if(MainActivity.user.getType() == UserType.NORMAL){

            binding.actionButton.setText("Logout");

            binding.changePassword.setVisibility(View.VISIBLE);
            binding.changePassword.setOnClickListener(view -> {
                changePassword();
            });

            if(!MainActivity.user.isVerified()){
                binding.verifyEmailButton.setVisibility(View.VISIBLE);
                binding.verifyEmailButton.setOnClickListener(view -> {
                    verifyEmail();
                });
            }


        }else{
            binding.actionButton.setText("Login");
        }
    }

    void changePassword(){
    }

    void verifyEmail(){

    }
}