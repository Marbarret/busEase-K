package com.example.myapplication

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private val emailTxt = findViewById<EditText>(R.id.txtEmail)
    private val passwordTxt = findViewById<EditText>(R.id.txtPassword)

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Inicialize o FirebaseAuth
        auth = FirebaseAuth.getInstance()

        // Chame a função de login quando o botão de login for clicado
        val buttonGoogle = findViewById<Button>(R.id.login_btn)
        buttonGoogle.setOnClickListener {
            val email = emailTxt.text.toString()
            val password = passwordTxt.text.toString()

            singInCredentials(email, password)
        }
    }

    private fun singInCredentials(email: String, pass: String) {
        auth.signInWithEmailAndPassword(email, pass)
            .addOnCompleteListener(this) { result ->
                if (result.isSuccessful) {
                    Log.d(TAG, "signIn:Failure", result.exception)
                    val user = auth.currentUser
                    println(user)
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