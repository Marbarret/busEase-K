package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.Extensions.Extension.toast
import com.example.myapplication.Utils.FirebaseUtils.firebaseAuth
import com.example.myapplication.Utils.FirebaseUtils.firebaseUser
import com.example.myapplication.databinding.ActivityCreateAccountBinding
import com.google.firebase.auth.FirebaseUser

class CreateAccount : AppCompatActivity() {

    private lateinit var email: String
    private lateinit var password: String
    private lateinit var createAccountInput: Array<EditText>

    private var emailXml = binding.txtEmail
    private var passwordXml = binding.txtPassword

    private lateinit var binding: ActivityCreateAccountBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCreateAccountBinding.inflate(layoutInflater)
        setContentView(binding.root)
        createAccountInput = arrayOf(emailXml, passwordXml)

        binding.btnSignUp.setOnClickListener {
            signIn()
            val intentSuccess = Intent(applicationContext, SuccessScreen::class.java)
            startActivity(intentSuccess)
            finish()
        }
    }

    // Checar se existe usuário logado
    override fun onStart() {
        super.onStart()
        val user: FirebaseUser? = firebaseAuth.currentUser
        user?.let {
            startActivity(Intent(this, ProfileView::class.java))
            toast("welcome back")
        }
    }

    private fun notEmpty(): Boolean =
        emailXml.text.toString().trim().isNotEmpty() &&
        passwordXml.text.toString().trim().isNotEmpty()

    private fun identicalPassword(): Boolean {
        if (!notEmpty()) {
            createAccountInput.forEach { input ->
                if (input.text.toString().trim().isEmpty()) {
                    input.error = "${input.hint} is required"
                }
            }
        } else {
            toast("passwords are not matching !")
        }
        return false
    }

    private fun signIn() {
        if (identicalPassword()) {
            email = emailXml.text.toString().trim()
            password = passwordXml.text.toString().trim()

            firebaseAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        toast("created account successfully !")
                        sendEmailVerification()
                        startActivity(Intent(this, ProfileView::class.java))
                        finish()
                    } else {
                        toast("failed to Authenticate !")
                    }
                }
        }
    }

    private fun sendEmailVerification() {
        firebaseUser?.let {
            it.sendEmailVerification().addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    toast("email sent to $email")
                }
            }
        }
    }
}