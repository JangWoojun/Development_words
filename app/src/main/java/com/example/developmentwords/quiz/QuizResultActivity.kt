package com.example.developmentwords.quiz

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.example.developmentwords.MainActivity
import com.example.developmentwords.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class QuizResultActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz_result)
        auth = Firebase.auth

        val o = intent.getStringExtra("o")
        val x = intent.getStringExtra("x")
        val p = (((o?.toFloat())?.div(15))?.times(100))?.toInt()
        val type = intent.getStringExtra("type")

        val tv8 = findViewById<TextView>(R.id.textView8)
        val tv9 = findViewById<TextView>(R.id.textView9)
        val tv10 = findViewById<TextView>(R.id.textView10)
        val button1 = findViewById<Button>(R.id.button1)

        val database = Firebase.database
        val level = database.getReference("users").child(auth.currentUser?.uid.toString()).child("${type}Level")

        tv8.text = getString(R.string.result_text4,o)
        tv9.text = getString(R.string.result_text5,x)
        tv10.text = "$p%"

        if (p==100){
            button1.text = "승급 성공"
            level.get().addOnSuccessListener{
                val value = it.value as Int
                level.child("users").child(auth.currentUser?.uid.toString())
                    .child("${type}Level").setValue(value+1)
            }
            button1.setOnClickListener {
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()
            }
        }
        else {
            button1.text = "승급 실패"
            button1.setOnClickListener {
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()
            }
        }

    }
}