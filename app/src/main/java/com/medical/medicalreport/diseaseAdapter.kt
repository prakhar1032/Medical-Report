package com.medical.medicalreport

import android.app.Activity
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView

class diseaseAdapter(
    private val mContext: Activity,
    var diseasesList: List<Diseases>
) :
    ArrayAdapter<Any?>(mContext, R.layout.activity_disease_info, diseasesList) {
    override fun getView(
        position: Int,
        convertView: View?,
        parent: ViewGroup
    ): View {
        val inflater = mContext.layoutInflater
        val diseaseInfoView =
            inflater.inflate(R.layout.activity_disease_info, null, true)
        val entertitle_info = diseaseInfoView.findViewById<TextView>(R.id.entertitle_info)
        val diseasediscription_info =
            diseaseInfoView.findViewById<TextView>(R.id.diseasediscription_info)
        val diseasecause_info =
            diseaseInfoView.findViewById<TextView>(R.id.diseasecause_info)
        val diseases = diseasesList[position]
    }

}