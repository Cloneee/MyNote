package com.example.mynote.screens.register;

import static com.example.mynote.configs.Constant.NOTE_RESULT;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.mynote.MainActivity;
import com.example.mynote.R;
import com.example.mynote.api.Auth;
import com.example.mynote.configs.Constant;
import com.example.mynote.interfaces.BaseCallBack;
import com.example.mynote.databinding.ActivityRegisterBinding;
import com.example.mynote.models.LoginParams;
import com.example.mynote.models.LoginResponse;
import com.example.mynote.models.Note;
import com.example.mynote.models.RegisterParams;
import com.example.mynote.models.User;
import com.example.mynote.models.UserType;
import com.example.mynote.repos.AuthenticationRepository;
import com.example.mynote.screens.home.HomeScreen;
import com.example.mynote.screens.note.NoteScreen;
import com.example.mynote.services.s.ToastHelper;

public class RegisterScreen extends AppCompatActivity {
    ActivityRegisterBinding binding;

    AuthenticationRepository authenticationRepos = AuthenticationRepository.getInstance();

    ActivityResultLauncher<Intent> mStartForResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.Theme_MyNote);
        setContentView(R.layout.activity_register);

        mStartForResult = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
            result -> {
                try {
                    if(result == null){
                        ToastHelper.showToast("Verifying fail");
                        return;
                    }

                    if (result.getResultCode() == Activity.RESULT_OK) {
                        login();
                    }else{
                        ToastHelper.showToast("Verifying fail");
                    }
                }catch (Exception e){
                    Log.e("TAG", "error: " + e);
                }

            }
        );

        binding = ActivityRegisterBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        binding.loginButton.setOnClickListener(view1 -> finish());
        binding.registerButton.setOnClickListener(view1 -> register());
    }

    void register (){
        if(binding.username.getText().toString().isEmpty()){
            ToastHelper.showToast("User name cannot be empty");
            return;
        }
        if(binding.password.getText().toString().isEmpty()){
            ToastHelper.showToast("User name cannot be empty");
            return;
        }
        if(binding.confirmPassword.getText().toString().isEmpty()){
            ToastHelper.showToast("User name cannot be empty");
            return;
        }
        if(binding.email.getText().toString().isEmpty()){
            ToastHelper.showToast("User name cannot be empty");
            return;
        }
        if(!binding.password.getText().toString().equals(binding.confirmPassword.getText().toString())){
            ToastHelper.showToast("Confirm password is not match with password");
            return;
        }

//        ToastHelper.showLoadingDialog(this);
        Intent i = new Intent(this, OtpScreen.class);
        //0 is verify email
        //1 is other otp
        i.putExtra(Constant.OTP_RESULT, 0);
        i.putExtra(Constant.OTP_MAIL, binding.email.getText().toString());

        mStartForResult.launch(i);
//        authenticationRepos.register(binding.username.getText().toString(), binding.password.getText().toString(), binding.email.getText().toString(),
//            (res) -> {
//                Intent i = new Intent(this, OtpScreen.class);
//                //0 is verify email
//                //1 is other otp
//                i.putExtra(Constant.OTP_RESULT, 0);
//                mStartForResult.launch(i);
//            }
//        );
    }

    void login (){
        MainActivity.login(this, "", new User("", UserType.GUEST, false));

//        authenticationRepos.login(binding.username.getText().toString(), binding.password.getText().toString(),
//            (res)->{
//                MainActivity.login(this, ((LoginResponse) res).token, new User("", UserType.NORMAL, ((LoginResponse) res).verify));
//            }
//        );
    }

    @Override
    protected void onDestroy() {
        binding = null;
        super.onDestroy();
    }
}