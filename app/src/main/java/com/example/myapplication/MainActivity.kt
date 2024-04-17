package com.example.myapplication

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    lateinit var username: EditText
    lateinit var password: EditText
    lateinit var  loginBtn: Button

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
//            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
//            insets
//        }
        username = findViewById(R.id.username_input)
        password = findViewById(R.id.pass_input)
        loginBtn = findViewById(R.id.login_btn)

        loginBtn.setOnClickListener {
            val user = username.text.toString()
            val pass = password.text.toString()

            Log.i("Test Credentials", "Username: $user and Password: $pass")
        }
    }
}