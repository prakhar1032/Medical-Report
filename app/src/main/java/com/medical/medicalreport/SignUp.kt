package com.medical.medicalreport

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.EditText
import android.widget.Toast
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class SignUp : AppCompatActivity() {
    var  user_name:EditText?=null
    var  user_email:EditText?=null
    var user_password:EditText?=null
    var conform_password:EditText?=null
   var firebaseAuth:FirebaseAuth?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)
        firebaseAuth= FirebaseAuth.getInstance()
        user_name = findViewById<EditText>(R.id.editTextTextPersonName4)
        user_email = findViewById<EditText>(R.id.editTextTextEmailAddress2)
        user_password = findViewById<EditText>(R.id.editTextTextPassword2)
        conform_password = findViewById<EditText>(R.id.editTextTextPassword3)

    }

    fun signup2(view: View)

    {
        RegisterUser()
    }

    private fun RegisterUser() {
        var name = user_name?.text.toString().trim()
        val email = user_email?.text.toString().trim()
        val password = user_password?.text.toString().trim()
        val repassword = conform_password?.text.toString().trim()
        if (TextUtils.isEmpty(name) || TextUtils.isEmpty(email) || TextUtils.isEmpty(password) || TextUtils.isEmpty(repassword)) {
            Toast.makeText(this, " This field cannot be empty", Toast.LENGTH_SHORT).show()
        }else if(!(password==repassword)){
           Toast.makeText(this, "Password did'nt match", Toast.LENGTH_SHORT).show()
        }
        else {

            firebaseAuth?.createUserWithEmailAndPassword(email,password)?.addOnCompleteListener(object : OnCompleteListener<AuthResult> {
                override fun onComplete(task: Task<AuthResult>) {
                    if (task.isSuccessful) {

                        Toast.makeText(applicationContext, "Account Created", Toast.LENGTH_SHORT).show()
                        startActivity(Intent(this@SignUp,MainActivity::class.java))
                    }
                    else{
                        val error = task.exception?.message
                        Toast.makeText(applicationContext, "Error! $error", Toast.LENGTH_SHORT).show()
                    }

                }


            })

        }
    }}