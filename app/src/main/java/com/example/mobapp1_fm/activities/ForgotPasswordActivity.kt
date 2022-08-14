package com.example.mobapp1_fm.activities

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.mobapp1_fm.R
import com.google.firebase.auth.FirebaseAuth

class ForgotPasswordActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    private lateinit var resetPasswordBtn: Button
    private lateinit var emailreset: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forgotpassword)

        emailreset = findViewById(R.id.emailreset)
        resetPasswordBtn =findViewById(R.id.resetPasswordBtn)

        auth = FirebaseAuth.getInstance()

        resetPasswordBtn.setOnClickListener {
            val email: String = emailreset.text.toString()

            if (TextUtils.isEmpty(email)) {
                    Toast.makeText(this, "Please enter your email", Toast.LENGTH_LONG)
                        .show()
                }
            else {
                auth.sendPasswordResetEmail(email)
                    .addOnCompleteListener(this) { task ->
                        if (task.isSuccessful)
                            Toast.makeText(this, "An email with reset instructiona has been sent to: $email", Toast.LENGTH_LONG).show()
                        val intent = Intent(this, LoginActivity::class.java)
                        startActivity(intent)
                        finish()
                    }
            }
        }
    }
}