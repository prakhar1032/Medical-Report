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
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class SignUp : AppCompatActivity() {
    companion object {
      lateinit  var user_name: EditText
        var user_email: EditText? = null
        var user_password: EditText? = null
        var conform_password: EditText? = null



    }

   var firebaseAuth:FirebaseAuth?=null
    var firebaseDatabase: DatabaseReference? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)
        firebaseAuth= FirebaseAuth.getInstance()
        user_name = findViewById<EditText>(R.id.editTextTextPersonName4)
        user_email = findViewById<EditText>(R.id.editTextTextEmailAddress2)
        user_password = findViewById<EditText>(R.id.editTextTextPassword2)
        conform_password = findViewById<EditText>(R.id.editTextTextPassword3)
        firebaseDatabase = FirebaseDatabase.getInstance().reference.child("Users")
            .child(firebaseAuth?.currentUser!!.uid)

    }

    fun signup2(view: View)

    {
        RegisterUser()
        SaveUrer()
    }

    private fun RegisterUser() {

        var name = Intent(this,MainActivity::class.java)
        intent.putExtra(MainActivity.NAME_EXTRA, name)
        val email = user_email?.text.toString().trim()
        val password = user_password?.text.toString().trim()
        val repassword = conform_password?.text.toString().trim()
        if ( TextUtils.isEmpty(email) || TextUtils.isEmpty(password) || TextUtils.isEmpty(repassword)) {
            Toast.makeText(this, " This field cannot be empty", Toast.LENGTH_SHORT).show()

        }else if(!(password==repassword)){
           Toast.makeText(this, "Password did'nt match", Toast.LENGTH_SHORT).show()
        }

        else {

            firebaseAuth?.createUserWithEmailAndPassword(email,password)?.addOnCompleteListener(object : OnCompleteListener<AuthResult> {
                override fun onComplete(task: Task<AuthResult>) {
                    if (task.isSuccessful) {

                        Toast.makeText(applicationContext, "Account Created", Toast.LENGTH_SHORT).show()

                        var user :FirebaseUser = firebaseAuth!!.currentUser!!
                        user.sendEmailVerification().addOnCompleteListener(object : OnCompleteListener<Void> {
                            override fun onComplete(task: Task<Void>) {
                       if(task.isSuccessful) {
                           Toast.makeText(
                               applicationContext,
                               "Check Your Email",
                               Toast.LENGTH_SHORT
                           ).show()
                           startActivity(Intent(this@SignUp, LoginActivity::class.java))


                       }
                        else{
                            val error = task.exception?.message
                           Toast.makeText(applicationContext, "Error $error", Toast.LENGTH_SHORT).show()

                       }
                            }

                        })

                    }
                    else{
                        val error = task.exception?.message
                        Toast.makeText(applicationContext, "Error! $error", Toast.LENGTH_SHORT).show()
                    }

                }


            })

        }
    }
    fun SaveUrer(){



        var name = Intent(this,MainActivity::class.java).toString()
        intent.putExtra(MainActivity.NAME_EXTRA, name)



        val email = user_email?.text.toString().trim()
        val password = user_password?.text.toString().trim()


        if (TextUtils.isEmpty(name)) {
            Toast.makeText(
                applicationContext,
                "Please Enter something in this Field",
                Toast.LENGTH_SHORT
            ).show()

        }else if (TextUtils.isEmpty(email)) {
            Toast.makeText(
                applicationContext,
                "Please Enter something in this Field",
                Toast.LENGTH_SHORT
            ).show()
        }
        else if (TextUtils.isEmpty(password)) {
            Toast.makeText(
                applicationContext,
                "Please Enter something in this Field",
                Toast.LENGTH_SHORT
            ).show()


        } else {
            val userinfo = HashMap<String, Any>()
            userinfo.put("name",name)

            userinfo.put("email",email)
            userinfo.put("Password",password)



            firebaseDatabase?.push()?.setValue(userinfo)
                ?.addOnCompleteListener(object : OnCompleteListener<Void> {
                    override fun onComplete(task: Task<Void>) {

                        if (task.isSuccessful) {
                            Toast.makeText(
                                applicationContext,
                                "Your information is updated",
                                Toast.LENGTH_SHORT
                            ).show()
                        } else {
                            val error = task.exception?.message
                            Toast.makeText(applicationContext, "Error" + error, Toast.LENGTH_SHORT)
                                .show()
                        }
                    }

                })

        }


    }
}