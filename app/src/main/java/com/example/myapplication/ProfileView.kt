package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.databinding.ActivityProfileViewBinding
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.firebase.auth.FirebaseAuth

class ProfileView : AppCompatActivity() {

    private lateinit var binding: ActivityProfileViewBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfileViewBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val name = binding.textName
        val email = binding.textEmail

        val account = GoogleSignIn.getLastSignedInAccount(this)

        if (account != null) {
            name.text = account.displayName
            email.text = account.email

            binding.btnLogout.setOnClickListener {
                FirebaseAuth.getInstance().signOut()
                val intentMain = Intent(applicationContext, MainActivity::class.java)
                startActivity(intentMain)
            }
        }
    }
}