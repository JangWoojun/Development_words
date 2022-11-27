package com.example.developmentwords.quiz

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.example.developmentwords.MainActivity
import com.example.developmentwords.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import es.dmoral.toasty.Toasty

class QuizResultActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    var mBackWait:Long = 0
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
        val tv12 = findViewById<TextView>(R.id.textView12)

        val database = Firebase.database
        val myRef = database.getReference("users").child(auth.currentUser?.uid.toString())


        tv8.text = getString(R.string.result_text4,o)
        tv9.text = getString(R.string.result_text5,x)
        tv10.text = "$p%"

        myRef.child("todayWord").setValue(o!!.toLong())

        if (p==100){
            tv12.text = getString(R.string.levelUp)
            myRef.get().addOnSuccessListener{
                val value = it.value as Int
                if (value<=5){ // TODO 임시 레벨 제한 부분
                    myRef.child("${type}Level").setValue(value+1)
                }
            }
            button1.setOnClickListener {
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()
            }
        }
        else {
            tv12.text = getString(R.string.fail)
            tv12.setTextColor(Color.parseColor("#D7263D"))

            button1.text = "승급 실패"
            button1.setOnClickListener {
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()
            }
        }

    }
    override fun onBackPressed() {
        if(System.currentTimeMillis() - mBackWait >=2000 ) {
            mBackWait = System.currentTimeMillis()
            Toasty.warning(this, "한번 더 누르시면 종료됩니다", Toast.LENGTH_SHORT, true).show()
        } else {
            finish() //액티비티 종료
        }
    }
}