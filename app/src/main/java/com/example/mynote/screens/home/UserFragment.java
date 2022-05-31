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
import com.example.mynote.repos.AuthenticationRepository;
import com.example.mynote.screens.register.ChangePassword;
import com.example.mynote.screens.register.OtpScreen;
import com.example.mynote.services.s.ToastHelper;

public class UserFragment extends Fragment {

    private FragmentUserBinding binding;
    AuthenticationRepository repository = AuthenticationRepository.getInstance();

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
        Intent i = new Intent(getContext(), ChangePassword.class);
        i.putExtra(Constant.PASSWORD_SCREEN_TYPE, 1);
        startActivity(i);
    }

    void verifyEmail(){
        ToastHelper.showLoadingDialog(getContext());
        repository.genOtp(MainActivity.user.getEmail(), res1 -> {
            Intent i = new Intent(getContext(), OtpScreen.class);
            //0 is verify email
            //1 is other otp
            i.putExtra(Constant.OTP_RESULT, 0);
            startActivity(i);
        });
    }
}