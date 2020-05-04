package org.wit.nurse.activities

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.card_nurse.view.*
import org.wit.nurse.R
import org.wit.nurse.models.NurseModel

interface NurseListener {
    fun onNurseClick(nurse: NurseModel)
}

class NurseAdapter constructor(private var nurses: List<NurseModel>,
                                   private val listener: NurseListener) : RecyclerView.Adapter<NurseAdapter.MainHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainHolder {
        return MainHolder(LayoutInflater.from(parent?.context).inflate(R.layout.card_nurse, parent, false))
    }

    override fun onBindViewHolder(holder: MainHolder, position: Int) {
        val nurse = nurses[holder.adapterPosition]
        holder.bind(nurse, listener)
    }

    override fun getItemCount(): Int = nurses.size

    class MainHolder constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(nurse: NurseModel,  listener : NurseListener) {
            itemView.nurseName.text = nurse.name
            itemView.nurseSalary.text = nurse.salary.toString()
//            itemView.imageIcon.setImageBitmap(readImageFromPath(itemView.context, nurse.image))
            itemView.setOnClickListener { listener.onNurseClick(nurse) }
        }
    }
}