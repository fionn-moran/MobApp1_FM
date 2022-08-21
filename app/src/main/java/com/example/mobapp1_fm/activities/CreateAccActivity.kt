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

class CreateAccActivity : AppCompatActivity() {

   private lateinit var emailCreate: EditText
   private lateinit var passCreate: EditText
   private lateinit var createaccBtn: Button
   private lateinit var loginBtn: Button

   private lateinit var auth: FirebaseAuth

   override fun onCreate(savedInstanceState: Bundle?) {
      super.onCreate(savedInstanceState)
      setContentView(R.layout.activity_createacc)

      emailCreate = findViewById(R.id.email_edt_text)
      passCreate = findViewById(R.id.pass_edt_text)
      loginBtn = findViewById(R.id.login_btn)
      createaccBtn = findViewById(R.id.createacc_btn)

      createaccBtn.setOnClickListener {
         val email: String = emailCreate.text.toString()
         val password: String = passCreate.text.toString()

         /// Checking for variations of empty strings
         if (TextUtils.isEmpty(email)) {
            Toast.makeText(this, "Please enter an email", Toast.LENGTH_LONG).show()
         }

         else if (TextUtils.isEmpty(password)){
            Toast.makeText(this, "Please enter a password", Toast.LENGTH_LONG).show()
         }

         else if ((TextUtils.isEmpty(email)) || TextUtils.isEmpty(password)) {
               Toast.makeText(this, "Please enter both an email and a password", Toast.LENGTH_LONG).show()
            }
         else
         {
            FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password) //validates and creates user (6 char password)
               .addOnCompleteListener(this) { task ->
                  if (task.isSuccessful) {
                     val currentuser = auth.currentUser?.email.toString()
                     Toast.makeText(this, "Welcome: $currentuser", Toast.LENGTH_LONG).show() //Shows a welcome message to the current user
                     val intent = Intent(this, MainActivity::class.java)
                     startActivity(intent)
                     finish()
                  }
               }
               .addOnFailureListener{
                  Toast.makeText(this, "Error: ${it.message}", Toast.LENGTH_LONG).show() //extra firebase validation on email/password fields. This will throw the specific error e.g. bad email/bad password format
               }
         }
      }

      loginBtn.setOnClickListener {
         val intent = Intent(this, LoginActivity::class.java)
         startActivity(intent)
         finish()
      }
   }
}

