package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.myapplication.databinding.ActivityCreateAccountBinding
import com.example.myapplication.databinding.ActivitySuccessScreenBinding

class SuccessScreen : AppCompatActivity() {

    private lateinit var binding: ActivitySuccessScreenBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySuccessScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.backLogin.setOnClickListener {
            val intentMain = Intent(applicationContext, MainActivity::class.java)
            startActivity(intentMain)
        }

    }
}