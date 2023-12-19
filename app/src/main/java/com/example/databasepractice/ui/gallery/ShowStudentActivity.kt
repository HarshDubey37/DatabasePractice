package com.example.databasepractice.ui.gallery

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.databasepractice.databinding.ActivityShowStudentBinding
import com.example.databasepractice.ui.Database.DbHelper
import com.example.databasepractice.ui.gallery.Model.OnDeleteStudent

 class ShowStudentActivity : AppCompatActivity(),OnDeleteStudent {
    private lateinit var binding: ActivityShowStudentBinding
    private  var dbHelper= DbHelper(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityShowStudentBinding.inflate(layoutInflater)
        setContentView(binding.root)

        showdata()
    }

    private fun showdata() {

        var stdlist=dbHelper.showStudent()

        val ad=Adapter(stdlist,this,this)
        binding.showRV.layoutManager=LinearLayoutManager(this)
        binding.showRV.adapter=ad
        binding.showRV.setHasFixedSize(true)
        binding.showRV.addItemDecoration(DividerItemDecoration(this,1))

    }

     override fun OnDeleteStudentClicked(id: Int) {
          dbHelper.deleteStudent(id)
//         if (r>0)
             showdata()
     }

     override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
         super.onActivityResult(requestCode, resultCode, data)
         if (requestCode==1)
             recreate()
     }
 }