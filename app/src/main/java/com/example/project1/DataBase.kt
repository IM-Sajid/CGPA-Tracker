package com.example.project1

import android.content.Intent
import android.widget.Toast
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthInvalidUserException
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import com.google.firebase.firestore.FirebaseFirestore

class SignUpViewModel(private val context: android.content.Context) {
    private val auth = FirebaseAuth.getInstance()
    private val db = FirebaseFirestore.getInstance()
    private var  isLoading = false
    private var  finished = false

    fun createUser(user: User) {
        auth.createUserWithEmailAndPassword(user.email.text.replace(" ",""), user.password.text)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val userData = hashMapOf(
                        "name" to user.name.text,
                        "email" to user.email.text.replace(" ",""),
                        "password" to user.password.text
                    )
                    db.collection("users")
                        .document(auth.currentUser!!.uid)
                        .set(userData)
                        .addOnSuccessListener {
                            Toast.makeText(context, "User created successfully", Toast.LENGTH_SHORT)
                                .show()
                            context.startActivity(Intent(context, CalculationPage::class.java))
                        }
                        .addOnFailureListener { e ->
                            Toast.makeText(context, "Failed to add user: $e", Toast.LENGTH_SHORT)
                                .show()
                        }
                } else {
                    if (task.exception is FirebaseAuthUserCollisionException) {
                        // Email already exists, handle accordingly
                        Toast.makeText(context, "Email is already in use", Toast.LENGTH_SHORT).show()
                    } else {
                        // Other authentication failures
                        Toast.makeText(
                            context,
                            "Authentication failed: ${task.exception}",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            }
    }

    fun loginUser(user: User) {
        isLoading = true
        auth.signInWithEmailAndPassword(user.email.text.replace(" ",""), user.password.text)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    // Authentication successful, proceed to next activity or perform necessary actions
                    Toast.makeText(context, "Login successful", Toast.LENGTH_SHORT).show()
                    context.startActivity(Intent(context, CalculationPage::class.java))
                    // Example of starting a new activity
                } else {
                    if(task.exception is FirebaseAuthInvalidUserException){
                        Toast.makeText(context, "User not found", Toast.LENGTH_SHORT).show()
                    }
                    else if(task.exception is FirebaseAuthInvalidCredentialsException){
                        Toast.makeText(context, "Invalid password", Toast.LENGTH_SHORT).show()
                    }
                    else{
                        Toast.makeText(context, "Login failed: ${task.exception}", Toast.LENGTH_SHORT).show()
                    }
                }
            }
    }

}
//
//import android.content.ContentValues.TAG
//import android.util.Log
//import androidx.compose.runtime.Composable
//import com.google.firebase.Firebase
//import com.google.firebase.database.*
//
//
//
//class FirebaseManager {
//    private val database = FirebaseDatabase.getInstance().reference.child("users")
//
//    fun createUser(user: User, callback: (Boolean) -> Unit) {
//        val userEmail = user.email.replace(".", ",") // Firebase doesn't allow dot (.) in the key
//        database.child(userEmail).setValue(user)
//            .addOnCompleteListener { task ->
//                if (task.isSuccessful) {
//                    Log.d(TAG, "User created successfully")
//                } else {
//                    Log.e(TAG, "Failed to create user: ${task.exception}")
//                }
//            }
//    }
//
//    // Read user by email
//    fun getUserByEmail(email: String, callback: (User?) -> Unit) {
//        val userEmail = email.replace(".", ",")
//        database.child(userEmail).addListenerForSingleValueEvent(object : ValueEventListener {
//            override fun onDataChange(snapshot: DataSnapshot) {
//                val user = snapshot.getValue(User::class.java)
//                callback(user)
//            }
//
//            override fun onCancelled(error: DatabaseError) {
//                callback(null)
//            }
//        })
//    }
//
//    // Update user information
//    fun updateUser(user: User, callback: (Boolean) -> Unit) {
//        val userEmail = user.email.replace(".", ",")
//        database.child(userEmail).setValue(user)
//            .addOnSuccessListener { callback(true) }
//            .addOnFailureListener { callback(false) }
//    }
//
//    // Delete user
//    fun deleteUser(email: String, callback: (Boolean) -> Unit) {
//        val userEmail = email.replace(".", ",")
//        database.child(userEmail).removeValue()
//            .addOnSuccessListener { callback(true) }
//            .addOnFailureListener { callback(false) }
//    }
//}
