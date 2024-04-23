package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material3.Button
import com.google.firebase.auth.FirebaseAuth
import com.example.myapplication.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = FirebaseAuth.getInstance()
        signInTextField()
// https://github.com/mobile-roadmap/android-developer-roadmap/blob/master/ROADMAP_STUDY_CONTENT.md
    }

    private fun signInTextField() {
        val emailTxt = this.findViewById<EditText>(R.id.txtEmail)
        val passwordTxt = this.findViewById<EditText>(R.id.txtPassword)

        val email = emailTxt.text.toString()
        val password = passwordTxt.text.toString()

        val buttonGoogle = findViewById<Button>(R.id.login_btn)
        buttonGoogle.setOnClickListener {
            singInCredentials(email, password)
        }
    }

    private fun singInCredentials(email: String, pass: String) {
        auth.signInWithEmailAndPassword(email, pass)
            .addOnCompleteListener { result ->
                if (result.isSuccessful) {
                    val intent = Intent(this, HomeViewActivity::class.java)
                    startActivity(intent)
                    finish()
                } else {
                    Log.d(TAG, "signIn:Failure", result.exception)
                    Toast.makeText(baseContext, "Sign In Failure", Toast.LENGTH_SHORT).show()
                }
            }
    }

    companion object {
        private const val  TAG = "LoginActiv"
    }
}

