package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.databinding.ActivityMainBinding
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var auth: FirebaseAuth? = null
    private var nGoogleSignInClient: GoogleSignInClient? = null

    private var nSignIn = 123
    private lateinit var token: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = FirebaseAuth.getInstance()
        signInTextField()

        goToCreateAccountView()
        createRequest()

        binding.btnSignInGoogle.setOnClickListener {
        }
    }

    private fun signIn() {
        val signInIntent = nGoogleSignInClient!!.signInIntent
        startActivityForResult(signInIntent, nSignIn)
    }

    private fun createRequest() {
        val gSignIn = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()

        nGoogleSignInClient = GoogleSignIn.getClient(this, gSignIn)
    }

    @Deprecated("Deprecated in Java")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == nSignIn) {
            val task: Task<GoogleSignInAccount> = GoogleSignIn.getSignedInAccountFromIntent(data)
            try {
                val account: GoogleSignInAccount? = task.getResult(ApiException::class.java)
                if (account != null) {
                    firebaseAuthWithGoogle(account)
                }
            } catch (a: ApiException) {
                Toast.makeText(baseContext, "${a.message} Erro aqui", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun firebaseAuthWithGoogle(account: GoogleSignInAccount) {
        val credential = GoogleAuthProvider.getCredential(account.idToken, null)
        auth!!.signInWithCredential(credential)
            .addOnCompleteListener(this) { result ->
                if (result.isSuccessful) {
                    val user = auth!!.currentUser
                    val intentProfile = Intent(applicationContext, ProfileView::class.java)
                    startActivity(intentProfile)
                } else {
                    Toast.makeText(this, "Sorry, login Failure", Toast.LENGTH_SHORT)
                        .show()
                }
            }
    }

    private fun goToCreateAccountView() {

//        binding.btnCreateAccount.setOnClickListener {
            startActivity(Intent(this, CreateAccount::class.java))
//            val intent = Intent(applicationContext, CreateAccount::class.java)
//            startActivity(intent)
//            finish()
//        }
    }

    private fun signInTextField() {
        val emailTxt = this.findViewById<EditText>(R.id.txtEmail)
        val passwordTxt = this.findViewById<EditText>(R.id.txtPassword)

        val email = emailTxt.text.toString()
        val password = passwordTxt.text.toString()

        val buttonGoogle = findViewById<Button>(R.id.login_btn)
        buttonGoogle.setOnClickListener {
//            singInCredentials(email, password)
        }
    }

//    private fun singInCredentials(email: String, pass: String) {
//        auth!!.signInWithEmailAndPassword(email, pass)
//            .addOnCompleteListener { result ->
//                if (result.isSuccessful) {
//                    val intent = Intent(this, HomeViewActivity::class.java)
//                    startActivity(intent)
//                    finish()
//                } else {
//                    Log.d(TAG, "signIn:Failure", result.exception)
//                    Toast.makeText(baseContext, "Sign In Failure", Toast.LENGTH_SHORT).show()
//                }
//            }
//    }

    companion object {
        private const val  TAG = "LoginActiv"
    }
}

