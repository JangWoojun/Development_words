package com.developmentwords.developmentwords.study

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.developmentwords.developmentwords.MainActivity
import com.developmentwords.developmentwords.R
import com.developmentwords.developmentwords.databinding.ActivityResultBinding
import com.developmentwords.developmentwords.quiz.QuizActivity
import es.dmoral.toasty.Toasty

class ResultActivity : AppCompatActivity() {
    var mBackWait:Long = 0
    private lateinit var binding: ActivityResultBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)
        binding = ActivityResultBinding.inflate(layoutInflater)

        val o = intent.getStringExtra("o")
        val x = intent.getStringExtra("x")
        val p = (((o?.toFloat())?.div(15))?.times(100))?.toInt()
        val type = intent.getStringExtra("type")


        val tv8 = findViewById<TextView>(R.id.textView8)
        val tv9 = findViewById<TextView>(R.id.textView9)
        val tv10 = findViewById<TextView>(R.id.textView10)
        val button1 = findViewById<Button>(R.id.button1)
        val button2 = findViewById<Button>(R.id.button2)

        tv8.text = getString(R.string.result_text4,o)
        tv9.text = getString(R.string.result_text5,x)
        tv10.text = "$p%"

        button1.setOnClickListener{
            val intent = Intent(this,MainActivity::class.java)
            startActivity(intent)
            finish()
        }
        button2.setOnClickListener {
            val intent = Intent(this,QuizActivity::class.java)
            intent.putExtra("type",type)
            startActivity(intent)
            finish()
        }

    }
    override fun onBackPressed() {
        if(System.currentTimeMillis() - mBackWait >=2000 ) {
            mBackWait = System.currentTimeMillis()
            Toasty.warning(this, "?????? ??? ???????????? ???????????????", Toast.LENGTH_SHORT, true).show()
        } else {
            finish()
        }
    }
}