package com.andreispanait.beers.repository

import android.util.Log
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserRepository @Inject constructor() {


    private val auth: FirebaseAuth = Firebase.auth

    suspend fun signUp(email: String, password: String) = withContext(Dispatchers.IO) {

        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val user = auth.currentUser
                    Log.d("AUTENTICATION", user?.email.toString())
                } else {
                    Log.d("AUTENTICATIONERROR","Error", task.exception )
                }
            }
            .addOnFailureListener {

            }
    }
}