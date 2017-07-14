package com.example.jrodri.testtddanduiapplication.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.jrodri.testtddanduiapplication.R;
import com.example.jrodri.testtddanduiapplication.handlers.RegisterUserHandler;
import com.example.jrodri.testtddanduiapplication.models.User;
import com.example.jrodri.testtddanduiapplication.services.UserDataBaseService;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by jrodri on 7/7/17.
 */

public class LoginActivity extends AppCompatActivity {

    @BindView(R.id.usernameET)
    protected EditText userNameET;

    @BindView(R.id.passwordET)
    protected EditText passwordET;

    @BindView(R.id.signInBtn)
    protected Button signInBtn;

    private RegisterUserHandler registerUserHandler;

    private UserDataBaseService userDataBaseService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        userDataBaseService = new UserDataBaseService();
        registerUserHandler = new RegisterUserHandler(userDataBaseService);
        signInBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });
    }

    public void login(){
        String username = userNameET.getText().toString();
        String password = passwordET.getText().toString();
        User user = new User(username,password);
        if(registerUserHandler.login(user)){
            Toast.makeText(this, R.string.successfull_login,Toast.LENGTH_LONG).show();
            Intent intent = new Intent(this, CountryActivity.class);
            startActivity(intent);
        }else{
            Toast.makeText(this, R.string.failed_login,Toast.LENGTH_LONG).show();
            passwordET.setText("");
        }
    }
}
