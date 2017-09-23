package com.example.jrodri.testtddanduiapplication.activities

import android.content.Intent
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.text.SpannableString
import android.text.style.UnderlineSpan
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import butterknife.BindView
import butterknife.ButterKnife
import com.example.jrodri.testtddanduiapplication.R
import com.example.jrodri.testtddanduiapplication.handlers.RegisterUserHandler
import com.example.jrodri.testtddanduiapplication.models.User
import com.example.jrodri.testtddanduiapplication.services.UserDataBaseService

class MainActivity2 : AppCompatActivity() {

    private lateinit var nameET: EditText
    private lateinit var usernameET: EditText

    @BindView(R.id.ageET)
    protected lateinit var ageET: EditText

    @BindView(R.id.passwordET)
    protected lateinit var passwordET: EditText

    @BindView(R.id.signInBtn)
    protected lateinit var signInBtn: TextView

    @BindView(R.id.signUpBtn)
    protected lateinit var signUpBtn: Button

    private lateinit var registerUserHandler: RegisterUserHandler

    private var isThereNotValidFields: Boolean = false

    private lateinit var userDataBaseService: UserDataBaseService


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        ButterKnife.bind(this)
        nameET = findViewById(R.id.nameET) as EditText
        usernameET = findViewById(R.id.usernameET) as EditText
        userDataBaseService = UserDataBaseService()
        registerUserHandler = RegisterUserHandler(userDataBaseService)
        signUpBtn.setOnClickListener { registerUser() }
        signInBtn.setOnClickListener { navigateToLogin() }
        setTextToSignInLink()
    }

    private fun setTextToSignInLink() {
        val content = SpannableString(getString(R.string.sign_in_invite))
        content.setSpan(UnderlineSpan(), 0, getString(R.string.sign_in_invite).length, 0)
        signInBtn.text = content
        signInBtn.setTextColor(ContextCompat.getColor(this, R.color.red))
    }

    private fun checkNonEmptyFields(editText: EditText) {
        if (registerUserHandler.checkEmptyString(editText.text.toString())) {
            editText.error = getString(R.string.field_can_not_be_empty)
            isThereNotValidFields = true
        } else {
            isThereNotValidFields = false
        }
    }

    private fun checkPasswordCorrectness(editText: EditText) {
        if (!registerUserHandler.checkValidPassword(editText.text.toString())) {
            editText.error = getString(R.string.password_requirement)
            isThereNotValidFields = true
        } else {
            isThereNotValidFields = false
        }
    }

    private fun registerUser() {
        checkNonEmptyFields(usernameET)
        checkNonEmptyFields(nameET)
        checkNonEmptyFields(ageET)
        checkPasswordCorrectness(passwordET)
        if (!isThereNotValidFields) {
            val user = User(nameET.text.toString(), usernameET.text.toString(),
                    passwordET.text.toString(), Integer.parseInt(ageET.text.toString()))
            val isUserSaved = registerUserHandler.saveInDatabase(user)
            if (isUserSaved) {
                Toast.makeText(this, R.string.user_creation_successfull, Toast.LENGTH_LONG).show()
                val intent = Intent(this, CountryActivity::class.java)
                startActivity(intent)
            } else {
                Toast.makeText(this, R.string.error_creating_user, Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun navigateToLogin() {
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
    }
}
