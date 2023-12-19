package com.example.databasepractice.ui.gallery

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.EditText
import android.widget.Toast
import com.example.databasepractice.R
import com.example.databasepractice.databinding.ActivityAddStudentBinding
import com.example.databasepractice.ui.Database.DbHelper

class AddStudentActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddStudentBinding
    private lateinit var name:EditText
    private lateinit var email:EditText
    private lateinit var mobile:EditText
    private lateinit var i:Intent
    private lateinit var e:String
    private lateinit var m:String
    private lateinit var n:String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityAddStudentBinding.inflate(layoutInflater)
        setContentView(binding.root)

        name=binding.nameTV
        email=binding.emailTV
        mobile=binding.mobTv
        i=intent

        var dbHelper=DbHelper(this)

        if (i.getIntExtra("flag",0)==0) {
            binding.button.setOnClickListener {

                n = name.text.toString()
                e = email.text.toString()
                m = mobile.text.toString()

                var id = dbHelper.addStudent(n, e, m)

                if (id > 0)
                    Toast.makeText(this, "Success!!", Toast.LENGTH_SHORT).show()
                else
                    Toast.makeText(this, "Failed!!", Toast.LENGTH_SHORT).show()

                finish()
            }
        }
        else if (i.getIntExtra("flag",0)==1){

                setgetdata()
                binding.button.text="edit"
                binding.button.setOnClickListener {

                    n=name.text.toString()
                    e=email.text.toString()
                    m=mobile.text.toString()

                    var id=i.getIntExtra("Id",-1)
                    dbHelper.updateStudent(n,m,e,id)
                    finish()
                }
            }



    }

    private fun setgetdata() {
        if(i.hasExtra("Id")&&i.hasExtra("name")&&i.hasExtra("mobile")&&i.hasExtra("email")){

             n= i.getStringExtra("name").toString()
             m=i.getStringExtra("mobile").toString()
            e=i.getStringExtra("email")!!

            name.setText(n)
            mobile.setText(m)
            email.setText(e)
        }
    }
}