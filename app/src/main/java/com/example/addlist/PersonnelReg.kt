package com.example.addlist

import android.annotation.SuppressLint
import android.content.Intent
import android.database.sqlite.SQLiteDatabase
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import android.widget.RadioGroup
import com.example.addlist.DBManager
import com.example.addlist.PersonnelInfo
import com.example.addlist.R

class PersonnelReg : AppCompatActivity() {

    lateinit var dbManager: DBManager
    lateinit var sqlitedb : SQLiteDatabase

    lateinit var edtName : EditText
    lateinit var edtPlace : EditText
    lateinit var edtStart : EditText
    lateinit var edtDate : EditText
    lateinit var edtTime : EditText
    lateinit var edtContent : EditText
    lateinit var edtLink : EditText
    lateinit var btn_to_list : Button

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_personnel_reg)

        edtName = findViewById(R.id.edtName)
        edtPlace = findViewById(R.id.edtPlace)
        edtStart = findViewById(R.id.edtStart)
        edtDate = findViewById(R.id.edtDate)
        edtTime = findViewById(R.id.edtTime)
        edtContent = findViewById(R.id.edtContent)
        edtLink = findViewById(R.id.edtLink)
        btn_to_list = findViewById(R.id.btn_to_list)


        dbManager = DBManager(this, "guruDB", null, 1)

        btn_to_list.setOnClickListener {
            var str_name: String = edtName.text.toString()
            var str_place: String = edtPlace.text.toString()
            var str_start: String = edtStart.text.toString()
            var str_date: String = edtDate.text.toString()
            var str_time: String = edtTime.text.toString()
            var str_content: String = edtContent.text.toString()
            var str_link: String = edtLink.text.toString()



            sqlitedb = dbManager.writableDatabase
            sqlitedb.execSQL("INSERT INTO guru VALUES ('" +str_name+"', '"+str_place+"', "+str_start+", '" +str_date+"', '" +str_time+"', '" +str_content+"', '" +str_link+"')")
            sqlitedb.close()

            val intent = Intent(this, PersonnelList::class.java)
            intent.putExtra("intent_name", str_name)
            startActivity(intent)
        }
    }
}