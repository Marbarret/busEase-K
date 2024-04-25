package com.example.myapplication.Utils

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

object FirebaseUtils {
    val firebaseAuth: FirebaseAuth = FirebaseAuth.getInstance()
    var firebaseUser: FirebaseUser? = firebaseAuth.currentUser
}