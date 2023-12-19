package com.example.databasepractice.ui.gallery

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.databasepractice.databinding.ItemStudentBinding
import com.example.databasepractice.ui.gallery.Model.OnDeleteStudent
import com.example.databasepractice.ui.gallery.Model.Student

class Adapter(val stdlist: List<Student>,val dl:OnDeleteStudent,val context: ShowStudentActivity):RecyclerView.Adapter<Adapter.StudentViewhoolder>() {
    class StudentViewhoolder(val binding: ItemStudentBinding):ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentViewhoolder {
        return StudentViewhoolder(ItemStudentBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun getItemCount(): Int {
       return stdlist.size
    }

    override fun onBindViewHolder(holder: StudentViewhoolder, position: Int) {
        val st=stdlist[position]
       holder.binding.SNameTV.text=st.name
       holder.binding.emailTVV.text=st.email
       holder.binding.SMobileTV.text=st.mobile

        holder.binding.deletebtn.setOnClickListener{
            dl.OnDeleteStudentClicked(st.id)
        }

        holder.binding.updateCard.setOnClickListener {
            val i=Intent(context,AddStudentActivity::class.java)
            i.putExtra("Id",st.id)
            i.putExtra("name",st.name)
            i.putExtra("email",st.email)
            i.putExtra("mobile",st.mobile)
            i.putExtra("flag",1)
            context.startActivityForResult(i,1)
        }
    }
}