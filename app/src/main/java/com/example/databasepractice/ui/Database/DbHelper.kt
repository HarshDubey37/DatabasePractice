package com.example.databasepractice.ui.Database

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Range
import android.widget.Toast
import com.example.databasepractice.ui.gallery.Model.Student

open class DbHelper(val context: Context):SQLiteOpenHelper(context, DATABASE_NAME,null, DATABESE_VERSION) {

    companion object{

        private const val DATABASE_NAME="STUDENT DATABASE"
        private val TABLE_NAME="STUDENT_TABLE"
        private val ID="ID"
        private val DATABESE_VERSION=1
        private val NAME="Name"
        private val EMAIL="EMAIL"
        private val MOBILE="MOBILE"
    }


    override fun onCreate(p0: SQLiteDatabase) {

        val q = ( "CREATE TABLE "+ TABLE_NAME +" ( "
        + ID +" INTEGER PRIMARY KEY, "+ NAME +" TEXT, "+ EMAIL+" TEXT, "+ MOBILE+" TEXT "+" )" )

        p0.execSQL(q)

    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {

        p0?.execSQL("DROP TABLE IF EXISTS $TABLE_NAME ")
        onCreate(p0!!)
    }

    fun addStudent(n:String,em:String,m:String):Long{

        val db=this.writableDatabase
        val c=ContentValues()
        c.put(NAME,n)
        c.put(EMAIL,em)
        c.put(MOBILE,m)

        val id = db.insert(TABLE_NAME,null,c)

        return id
    }
@SuppressLint("Range")
    fun showStudent():List<Student>{

        val db=this.readableDatabase
        val stdl= arrayListOf<Student>()

        val cursor=db.rawQuery("select * from $TABLE_NAME ",null)

        if (cursor.moveToFirst()){

            do {
                val std = Student(
                    cursor.getInt(cursor.getColumnIndex(ID)),
                    cursor.getString(cursor.getColumnIndex(NAME)),
                    cursor.getString(cursor.getColumnIndex(EMAIL)),
                    cursor.getString(cursor.getColumnIndex(MOBILE))
                )
                stdl.add(std)
            }while (cursor.moveToNext())

        }
            return stdl
    }

    fun deleteStudent(id:Int):Int{
        val dbHelper=this.writableDatabase

        val r=dbHelper.delete(TABLE_NAME, ID+" =? ", arrayOf(id.toString()))
        Toast.makeText(context,"Deleted!!",Toast.LENGTH_SHORT).show()

        dbHelper.close()
        return r
    }

    fun updateStudent(n:String,m: String,em: String,id:Int){
        val dbHelper=this.writableDatabase
        val c=ContentValues()

        c.put(NAME,n)
        c.put(EMAIL,em)
        c.put(MOBILE,m)

        val r=dbHelper.update(TABLE_NAME,c, "$ID LIKE?", arrayOf(id.toString()))
        if (r==-1)
            Toast.makeText(context,"Failed!!",Toast.LENGTH_SHORT).show()
        else
            Toast.makeText(context,"updated!!",Toast.LENGTH_SHORT).show()
    }

}