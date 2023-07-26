package com.example.addlist

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button


class MainActivity : AppCompatActivity() {

    lateinit var btn_to_reg : Button

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn_to_reg = findViewById(R.id.btn_to_list)

        btn_to_reg.setOnClickListener {
            val intent = Intent(this, PersonnelReg::class.java )
            startActivity(intent)
        }
    }

}