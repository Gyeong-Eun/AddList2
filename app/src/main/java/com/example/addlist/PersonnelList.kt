package com.example.addlist

import android.annotation.SuppressLint
import android.content.Intent
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView

class PersonnelList : AppCompatActivity() {

    lateinit var dbManager : DBManager
    lateinit var sqlitedb : SQLiteDatabase
    lateinit var layout: LinearLayout

    @SuppressLint("Range", "MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_personnel_list)

        dbManager = DBManager(this, "guruDB", null, 1)
        sqlitedb = dbManager.readableDatabase

        layout = findViewById(R.id.guru)

        var cursor : Cursor
        cursor = sqlitedb.rawQuery("SELECT * FROM guru", null)

        var num: Int =0
        while (cursor.moveToNext()) {

            var str_name = cursor.getString((cursor.getColumnIndex("name"))).toString()
            var str_place = cursor.getString((cursor.getColumnIndex("place"))).toString()
            var str_start = cursor.getString((cursor.getColumnIndex("start"))).toString()
            var str_date = cursor.getString((cursor.getColumnIndex("date"))).toString()
            var str_time = cursor.getString((cursor.getColumnIndex("time"))).toString()
            var str_content = cursor.getString((cursor.getColumnIndex("content"))).toString()
            var str_link = cursor.getString((cursor.getColumnIndex("link"))).toString()


            var layout_item:LinearLayout = LinearLayout(this)
            layout_item.orientation = LinearLayout.VERTICAL
            layout_item.id = num

            var tvName: TextView = TextView(this)
            tvName.text = str_name
            tvName.textSize = 30f
            tvName.setBackgroundColor(Color.LTGRAY)
            layout_item.addView(tvName)

            var tvPlace: TextView = TextView(this)
            tvPlace.text = str_place
            layout_item.addView(tvPlace)

            var tvStart: TextView = TextView(this)
            tvStart.text = str_start.toString()
            layout_item.addView(tvStart)

            var tvDate: TextView = TextView(this)
            tvDate.text = str_date
            layout_item.addView(tvDate)

            var tvTime: TextView = TextView(this)
            tvTime.text = str_time
            layout_item.addView(tvTime)

            var tvContent: TextView = TextView(this)
            tvContent.text = str_content
            layout_item.addView(tvContent)

            var tvLink: TextView = TextView(this)
            tvLink.text = str_link
            layout_item.addView(tvLink)

            layout_item.setOnClickListener {
                val intent = Intent(this, PersonnelInfo::class.java )
                intent.putExtra("intent_name", str_name)
                startActivity(intent)
            }

            layout.addView(layout_item)
            num++;
        }

        cursor.close()
        sqlitedb.close()
        dbManager.close()

    }
}