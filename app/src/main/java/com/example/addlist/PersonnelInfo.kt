package com.example.addlist

import android.annotation.SuppressLint
import android.content.Intent
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.TextView

class PersonnelInfo : AppCompatActivity() {

    lateinit var dbManager: DBManager
    lateinit var sqlitedb: SQLiteDatabase

    lateinit var tvName: TextView
    lateinit var tvPlace: TextView
    lateinit var tvStart: TextView
    lateinit var tvDate: TextView
    lateinit var tvTime: TextView
    lateinit var tvContent: TextView
    lateinit var tvLink: TextView


    lateinit var str_name: String
    lateinit var str_place :String
    lateinit var str_start: String
    lateinit var str_date: String
    lateinit var str_time: String
    lateinit var str_content: String
    lateinit var str_link: String

    @SuppressLint("Range", "MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_personnel_info)

        tvName = findViewById(R.id.edtName)
        tvPlace = findViewById(R.id.edtPlace)
        tvStart = findViewById(R.id.edtStart)
        tvDate = findViewById(R.id.edtDate)
        tvTime = findViewById(R.id.edtTime)
        tvContent = findViewById(R.id.edtContent)
        tvLink = findViewById(R.id.edtLink)


        val intent = intent
        str_name = intent.getStringExtra("intent_name").toString()

        dbManager = DBManager(this, "guruDB", null, 1)
        sqlitedb = dbManager.readableDatabase

        var cursor:Cursor
        cursor = sqlitedb.rawQuery("SELECT * FROM guru WHERE name = '"+str_name+"';", null)

        if(cursor.moveToNext()) {
            str_place = cursor.getString((cursor.getColumnIndex("place"))).toString()
            str_start = cursor.getString((cursor.getColumnIndex("start"))).toString()
            str_date = cursor.getString((cursor.getColumnIndex("date"))).toString()
            str_time = cursor.getString((cursor.getColumnIndex("time"))).toString()
            str_content = cursor.getString((cursor.getColumnIndex("content"))).toString()
            str_link = cursor.getString((cursor.getColumnIndex("link"))).toString()
        }

        cursor.close()
        sqlitedb.close()
        dbManager.close()

        tvName.text = str_name
        tvPlace.text = str_place
        tvStart.text = str_start
        tvDate.text = str_date
        tvTime.text = str_time
        tvContent.text = str_content
        tvLink.text = str_link + "\n "
    }
}