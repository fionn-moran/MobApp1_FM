package com.example.mobapp1_fm.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.mobapp1_fm.MainActivity
import com.example.mobapp1_fm.R
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : AppCompatActivity() {

    private lateinit var emailLogin: EditText
    private lateinit var passLogin: EditText
    private lateinit var createaccBtn: Button
    private lateinit var loginBtn: Button
    private lateinit var resetPasswordBtn: Button

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        emailLogin = findViewById(R.id.email_edt_text)
        passLogin = findViewById(R.id.pass_edt_text)
        createaccBtn = findViewById(R.id.createacc_btn)
        loginBtn = findViewById(R.id.login_btn)
        resetPasswordBtn = findViewById(R.id.resetPasswordBtn)

        loginBtn.setOnClickListener {
            val email: String = emailLogin.text.toString()
            val password: String = passLogin.text.toString()

            if (TextUtils.isEmpty(email) || TextUtils.isEmpty(password)) {
                Toast.makeText(this, "Please type your email and password", Toast.LENGTH_LONG)
                    .show()
            } else {
                auth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this) { task ->
                        if (task.isSuccessful) {
                            val currentuser = auth.currentUser?.email.toString()
                            Toast.makeText(this, "Welcome Back: $currentuser", Toast.LENGTH_LONG)
                                .show()
                            val intent = Intent(this, MainActivity::class.java)
                            startActivity(intent)
                            finish()
                        } else {
                            Toast.makeText(
                                this,
                                "Something went wrong, please check your details and try again",
                                Toast.LENGTH_LONG
                            ).show()
                        }
                    }
            }
        }

        createaccBtn.setOnClickListener {
            val intent = Intent(this, CreateAccActivity::class.java)
            startActivity(intent)
            finish()
        }

        resetPasswordBtn.setOnClickListener {
            val intent = Intent(this, ForgotPasswordActivity::class.java)
            startActivity(intent)
            finish()
        }

    }
}
