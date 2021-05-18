package com.medical.medicalreport

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.google.firebase.storage.UploadTask

class MedicalReport : AppCompatActivity() {
    val uriTxt :TextView?=null
    val PDF : Int = 0
    val DOCX : Int = 1

    lateinit var uri : Uri
    lateinit var mStorage : StorageReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_medical_report)

 val upload_pdf = findViewById<Button>(R.id.upload_pdf)
 val upload_docx = findViewById<Button>(R.id.upload_docx)
        mStorage = FirebaseStorage.getInstance().getReference("Uploads")
        upload_pdf.setOnClickListener{
            val intent = Intent()
            intent.setType ("pdf/*")
            intent.setAction(Intent.ACTION_GET_CONTENT)
            startActivityForResult(Intent.createChooser(intent, "Select PDF"), PDF)

        }
   upload_docx.setOnClickListener{
           view: View? -> val intent = Intent()
       intent.setType ("docx/*")
       intent.setAction(Intent.ACTION_GET_CONTENT)
       startActivityForResult(Intent.createChooser(intent, "Select DOCX"), DOCX)
   }

        }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {

        if (resultCode == RESULT_OK) {
            if (requestCode == PDF) {
                uri = data!!.data!!
                uriTxt?.text = uri.toString()
                upload ()
            }
            else if (requestCode == DOCX) {
                uri = data!!.data!!
                uriTxt?.text = uri.toString()
                upload ()
            }
        }
        super.onActivityResult(requestCode, resultCode, data)
    }

    private fun upload() {
        var mReference = uri.lastPathSegment?.let { mStorage.child(it) }
        try {
            mReference?.putFile(uri)!!.addOnSuccessListener {
                    taskSnapshot: UploadTask.TaskSnapshot? -> var url = taskSnapshot!!
                val dwnTxt = findViewById<View>(R.id.dwnTxt) as TextView
                dwnTxt.text = url.toString()
                Toast.makeText(this, "Successfully Uploaded :)", Toast.LENGTH_LONG).show()
            }
        }catch (e: Exception) {
            Toast.makeText(this, e.toString(), Toast.LENGTH_LONG).show()
        }
    }
}

