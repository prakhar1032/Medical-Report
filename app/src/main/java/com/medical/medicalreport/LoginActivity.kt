package com.medical.medicalreport

import android.content.Intent
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

private var Login_Button: Button?=null
private var SignIn_Button:Button?=null
private var user_email:EditText?=null
private var user_password:EditText?=null
private var firebaseAuth:FirebaseAuth?=null

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        Login_Button = findViewById<Button>(R.id.login)
        SignIn_Button = findViewById<Button>(R.id.signup)
        user_email = findViewById<EditText>(R.id.editTextTextEmailAddress)
        user_password = findViewById<EditText>(R.id.editTextTextPassword)
        firebaseAuth= FirebaseAuth.getInstance()


    }

    fun login(view: View) {

        LoginUser()

    }

    fun LoginUser()  {
        var email_text = user_email?.text.toString()
        var password_text = user_password?.text.toString()
  if(TextUtils.isEmpty(email_text) || TextUtils.isEmpty(password_text)){
      Toast.makeText(this," This field cannot be empty",Toast.LENGTH_SHORT).show()
  }
    else{
        firebaseAuth?.signInWithEmailAndPassword(email_text,password_text)?.addOnCompleteListener(object : OnCompleteListener<AuthResult> {
            override fun onComplete(task: Task<AuthResult>) {
                if (task.isSuccessful) {



                val user : FirebaseUser = firebaseAuth?.currentUser!!
                    if(user.isEmailVerified){
                        Toast.makeText(applicationContext, "Login Successfully", Toast.LENGTH_SHORT).show()
                        startActivity(Intent(this@LoginActivity,MainActivity::class.java))

                    }
                else{
                        Toast.makeText(applicationContext, "Account Not Verified", Toast.LENGTH_SHORT).show()

                    }
                }
                else{
                    val error = task.exception?.message
                    Toast.makeText(applicationContext, "Error! $error", Toast.LENGTH_SHORT).show()
                }

            }


        })


    }


    }

    fun signup(view: View) {
        val intent = Intent(this,SignUp::class.java)
        startActivity(intent)
    }
}

