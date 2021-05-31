package com.medical.medicalreport

import android.app.Activity
import android.content.Context
import android.view.ContextMenu
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class diseaseAdapter(data : ArrayList<DiseaseClass>, var  context: Context ) : RecyclerView.Adapter<diseaseAdapter.ViewHolder>() {
    internal  var data: List<DiseaseClass>


    init {
        this.data=data

    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {

        val layout = LayoutInflater.from(context).inflate(R.layout.disease_view, parent, false)

        return ViewHolder(layout)

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.title.text=data[position].title
        holder.descriptionn.text=data[position].description
        holder.cause.text=data[position].cause


    }

    override fun getItemCount(): Int {
        return data.size
    }
    class ViewHolder (view : View)  :RecyclerView.ViewHolder(view) {

        internal val title: TextView
        internal val descriptionn: TextView
        internal val cause: TextView



        init{

            title = view.findViewById<TextView>(R.id.entertitle_info)
            descriptionn = view.findViewById<TextView>(R.id.diseasediscription_info)
            cause = view.findViewById<TextView>(R.id.diseasecause_info)



        }
    }
}







