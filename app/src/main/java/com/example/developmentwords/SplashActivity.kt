package com.example.developmentwords

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class SplashActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    private lateinit var database: DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        auth = Firebase.auth
        database = Firebase.database.reference

        if(auth.currentUser?.uid == null){
            Handler().postDelayed({
                auth.signInAnonymously()
                    .addOnCompleteListener(this) { task ->
                        if (task.isSuccessful) {
                            database.child("users").child(auth.currentUser?.uid.toString())
                                .child("csLevel").setValue(1)
                            database.child("users").child(auth.currentUser?.uid.toString())
                                .child("englishLevel").setValue(1)
                            Toast.makeText(this, "환영합니다", Toast.LENGTH_SHORT).show()
                            startActivity(Intent(this,MainActivity::class.java))
                            finish()
                        } else {
                            Toast.makeText(this, "오류", Toast.LENGTH_SHORT).show()
                        }
                    }
            },2000)
        }
        else {
            Handler().postDelayed({

                Toast.makeText(this,"다시 돌아오신 걸 환영합니다",Toast.LENGTH_SHORT).show()
                startActivity(Intent(this,MainActivity::class.java))
                finish()
            },2000)
        }

    }
}