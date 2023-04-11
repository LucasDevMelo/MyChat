package com.example.mychat

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
class RegisterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        findViewById<Button>(R.id.btn_register).setOnClickListener {
            createUser()
        }
    }
    private fun createUser() {
        val email = findViewById<EditText>(R.id.edit_email).text.toString() // 1
        val password = findViewById<EditText>(R.id.edit_password).text.toString() // 1
        if (email.isEmpty() || password.isEmpty()) { // 2
            Toast.makeText(this,

                "email e senha devem ser informados",
                Toast.LENGTH_LONG).show()

            return
        }
        FirebaseAuth.getInstance()
            .createUserWithEmailAndPassword(email, password) // 3
            .addOnCompleteListener { // 4
                if (it.isSuccessful) { // 5
                    Log.i("Teste", "UserID é ${it.result?.user?.uid}") // 6
                }
            }
            .addOnFailureListener { // 7
                Log.e("Teste", it.message, it) // 8
            }
    }
}
