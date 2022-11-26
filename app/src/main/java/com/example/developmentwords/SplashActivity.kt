package com.example.developmentwords

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import es.dmoral.toasty.Toasty
import java.text.SimpleDateFormat
import java.util.*

class SplashActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    private lateinit var database: DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        val formatYYYYMMdd = "yyyyMMdd"

        val currentTime: Date = Calendar.getInstance().time

        val format = SimpleDateFormat(formatYYYYMMdd, Locale.getDefault())
        val current: String = format.format(currentTime)


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
                            Toasty.normal(this, "환영합니다", Toast.LENGTH_SHORT,getDrawable(R.drawable.home)).show()
                            startActivity(Intent(this,MainActivity::class.java))
                            finish()
                        } else {
                            Toasty.warning(this, "오류", Toast.LENGTH_SHORT, true).show()
                        }
                    }
            },2000)
        }
        else {
            Handler().postDelayed({
                Toasty.normal(this,"다시 돌아오신 걸 환영합니다",getDrawable(R.drawable.home)).show()
                startActivity(Intent(this,MainActivity::class.java))
                finish()
            },2000)
        }

    }
}