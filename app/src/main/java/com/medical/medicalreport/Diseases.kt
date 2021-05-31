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
import androidx.core.view.inputmethod.InputConnectionCompat
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import java.lang.Exception

class Diseases : AppCompatActivity() {

    var disease_name: EditText? = null
    var disease_2_name: EditText? = null
    var disease_3_name: EditText? = null
    var update_btn: Button? = null

    var firebaseAuth: FirebaseAuth? = null
    var firebaseDatabase: DatabaseReference? = null
    var database:FirebaseDatabase?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_diseases)

        disease_name = findViewById(R.id.disease_name)
        disease_2_name = findViewById(R.id.disease_2name)
        disease_3_name = findViewById(R.id.disease_3name)

        update_btn = findViewById(R.id.disease_upload)
        firebaseAuth = FirebaseAuth.getInstance()


        database = FirebaseDatabase.getInstance()
        firebaseDatabase = database?.getReference("USER")

        update_btn?.setOnClickListener {

            saveData()
        }

    }

fun saveData(){
    val Entertitle = disease_name?.text.toString().trim()
    val Diseasedescription = disease_2_name?.text.toString().trim()
    val Diseasecause = disease_3_name?.text.toString().trim()
    if(Entertitle.isNotEmpty() && Diseasedescription.isNotEmpty() && Diseasecause.isNotEmpty()){
        var model=DiseaseClass(Entertitle,Diseasedescription,Diseasecause)
        var id = firebaseDatabase?.push()?.key
        firebaseDatabase?.child(id!!)?.setValue(model)
      Toast.makeText(applicationContext,"Successfully Uploaded",Toast.LENGTH_SHORT).show()
        disease_name?.setText("")
        disease_2_name?.setText("")
        disease_3_name?.setText("")
    }

}
//    private fun SaveUserInfo() {
//
//
//
//
//        if (TextUtils.isEmpty(Entertitle)) {
//            Toast.makeText(
//                applicationContext,
//                "Please Enter something in this Field",
//                Toast.LENGTH_SHORT
//            ).show()
//        } else if (TextUtils.isEmpty(Diseasedescription)) {
//            Toast.makeText(
//                applicationContext,
//                "Please Enter something in this Field",
//                Toast.LENGTH_SHORT
//            ).show()
//        } else if (TextUtils.isEmpty(Diseasecause)) {
//            Toast.makeText(
//                applicationContext,
//                "Please Enter something in this Field",
//                Toast.LENGTH_SHORT
//            ).show()
//        } else {
//            val userinfo = HashMap<String, Any>()
//            userinfo.put("EnterTitle", Entertitle)
//            userinfo.put("DiseaseDescription", Diseasedescription)
//            userinfo.put("DiseaseCause", Diseasecause)
//
//             disease_name?.setText("")
//             disease_2_name?.setText("")
//             disease_3_name?.setText("")
//
//            firebaseDatabase?.push()?.setValue(userinfo)
//                ?.addOnCompleteListener(object : OnCompleteListener<Void> {
//                    override fun onComplete(task: Task<Void>) {
//
//                        if (task.isSuccessful) {
//                            Toast.makeText(
//                                applicationContext,
//                                "Your information is updated",
//                                Toast.LENGTH_SHORT
//                            ).show()
//                        } else {
//                            val error = task.exception?.message
//                            Toast.makeText(applicationContext, "Error" + error, Toast.LENGTH_SHORT)
//                                .show()
//                        }
//                    }
//
//                })
//
//        }
//
//    }

    fun diseaseinfo(view: View) {
        val intent = Intent(this,Disease_info::class.java)
        startActivity(intent)
    }
}