package com.example.jrodri.testtddanduiapplication.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jrodri.testtddanduiapplication.R;
import com.example.jrodri.testtddanduiapplication.handlers.RegisterUserHandler;
import com.example.jrodri.testtddanduiapplication.models.User;
import com.example.jrodri.testtddanduiapplication.services.UserDataBaseService;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.nameET)
    protected EditText nameET;

    @BindView(R.id.usernameET)
    protected EditText usernameET;

    @BindView(R.id.ageET)
    protected EditText ageET;

    @BindView(R.id.passwordET)
    protected EditText passwordET;

    @BindView(R.id.signInBtn)
    protected TextView signInBtn;

    @BindView(R.id.signUpBtn)
    protected Button signUpBtn;

    private RegisterUserHandler registerUserHandler;

    private boolean isThereNotValidFields;

    private UserDataBaseService userDataBaseService;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        userDataBaseService = new UserDataBaseService();
        registerUserHandler = new RegisterUserHandler(userDataBaseService);
        signUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerUser();
            }
        });
        signInBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigateToLogin();
            }
        });
        setTextToSignInLink();
    }

    private void setTextToSignInLink() {
        SpannableString content = new SpannableString(getString(R.string.sign_in_invite));
        content.setSpan(new UnderlineSpan(), 0, getString(R.string.sign_in_invite).length(), 0);
        signInBtn.setText(content);
        signInBtn.setTextColor(ContextCompat.getColor(this, R.color.red));
    }

    public void checkNonEmptyFields(EditText editText) {
        if (registerUserHandler.checkEmptyString(editText.getText().toString())) {
            editText.setError(getString(R.string.field_can_not_be_empty));
            isThereNotValidFields = true;
        } else {
            isThereNotValidFields = false;
        }
    }

    public void checkPasswordCorrectness(EditText editText) {
        if (!registerUserHandler.checkValidPassword(editText.getText().toString())) {
            editText.setError(getString(R.string.password_requirement));
            isThereNotValidFields = true;
        } else {
            isThereNotValidFields = false;
        }
    }

    public void registerUser() {
        checkNonEmptyFields(usernameET);
        checkNonEmptyFields(nameET);
        checkNonEmptyFields(ageET);
        checkPasswordCorrectness(passwordET);
        if (!isThereNotValidFields) {
            User user = new User(nameET.getText().toString(),usernameET.getText().toString(),
                    passwordET.getText().toString(), Integer.parseInt(ageET.getText().toString()));
            boolean isUserSaved = registerUserHandler.saveInDatabase(user);
            if(isUserSaved){
                Toast.makeText(this, R.string.user_creation_successfull,Toast.LENGTH_LONG).show();
                Intent intent = new Intent(this, CountryActivity.class);
                startActivity(intent);
            }else {
                Toast.makeText(this, R.string.error_creating_user,Toast.LENGTH_LONG).show();
            }
        }
    }

    public void navigateToLogin(){
        Intent intent = new Intent(this,LoginActivity.class);
        startActivity(intent);
    }
}
