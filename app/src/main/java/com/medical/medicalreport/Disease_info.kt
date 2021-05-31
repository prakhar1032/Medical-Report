package com.medical.medicalreport

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.google.firebase.ktx.Firebase
import java.util.*

class Disease_info : AppCompatActivity() {


    var reference: DatabaseReference? = null
     var database:FirebaseDatabase?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_disease_info)

       database= FirebaseDatabase.getInstance()
        reference=database?.getReference("Diseases")




    getData()



}


fun getData(){
          reference?.addValueEventListener(object : ValueEventListener{
              override fun onDataChange(snapshot: DataSnapshot) {
                  val diseases = ArrayList<DiseaseClass>()
                  var recycler_view = findViewById<RecyclerView>(R.id.recycler_view)
                  for(data in snapshot.children){
                      var model = data.getValue(DiseaseClass::class.java)
                      diseases.add(model as DiseaseClass)
                  }
                  if(diseases.size>0){
                      var adapter = diseaseAdapter(diseases,applicationContext)
                    recycler_view.layoutManager  = LinearLayoutManager(applicationContext)
                      recycler_view.adapter=adapter;
                  }

              }

              override fun onCancelled(task: DatabaseError) {
          Toast.makeText(applicationContext,"error",Toast.LENGTH_SHORT).show()

              }
          })

}}
