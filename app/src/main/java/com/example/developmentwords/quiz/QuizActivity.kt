package com.example.developmentwords.quiz

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import com.example.developmentwords.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class QuizActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz)
        val type = intent.getStringExtra("type")
        auth = Firebase.auth

        val word:TextView = findViewById(R.id.word)
        val tvOptionOne:Button = findViewById(R.id.tv_option_one)
        val tvOptionTwo:Button = findViewById(R.id.tv_option_two)
        val tvOptionThree:Button = findViewById(R.id.tv_option_three)
        val tvOptionFour:Button = findViewById(R.id.tv_option_four)

        Log.d("확인",type.toString())

        var i = 1

        if (type == "english"){
            get("english",i)
        }
        else {
            get("cs",i)
        }

    }
    fun get(type : String,i: Int){
        val database = Firebase.database
        val level = database.getReference("users").child(auth.currentUser?.uid.toString()).child("${type}Level")
        level.get().addOnSuccessListener{

            val value1 = it.value
            val quiz = database.getReference("${type}Quiz").child("lv$value1")

            quiz.get().addOnSuccessListener {
                val value2 : ArrayList<Any> = it.value as ArrayList<Any>
                val valueMap: HashMap<String,String> = value2[i] as HashMap<String, String>

                val word:TextView = findViewById(R.id.word)
                val tvOptionOne:Button = findViewById(R.id.tv_option_one)
                val tvOptionTwo:Button = findViewById(R.id.tv_option_two)
                val tvOptionThree:Button = findViewById(R.id.tv_option_three)
                val tvOptionFour:Button = findViewById(R.id.tv_option_four)

                word.text = valueMap["word"]
                tvOptionOne.text = valueMap["option1"]
                tvOptionTwo.text = valueMap["option2"]
                tvOptionThree.text = valueMap["option3"]
                tvOptionFour.text = valueMap["option4"]


            }

        }

    }

}