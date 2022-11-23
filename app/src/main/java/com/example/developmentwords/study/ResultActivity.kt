package com.example.developmentwords.study

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.example.developmentwords.MainActivity
import com.example.developmentwords.R
import com.example.developmentwords.databinding.ActivityResultBinding

class ResultActivity : AppCompatActivity() {
    private lateinit var binding: ActivityResultBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)
        binding = ActivityResultBinding.inflate(layoutInflater)

        val o = intent.getStringExtra("o")
        val x = intent.getStringExtra("x")
        val p = (((o?.toFloat())?.div(15))?.times(100))?.toInt()

        val tv8 = findViewById<TextView>(R.id.textView8)
        val tv9 = findViewById<TextView>(R.id.textView9)
        val tv10 = findViewById<TextView>(R.id.textView10)
        val button1 = findViewById<Button>(R.id.button1)

        tv8.text = getString(R.string.result_text4,o)
        tv9.text = getString(R.string.result_text5,x)
        tv10.text = "$p%"

        button1.setOnClickListener{
            val intent = Intent(this,MainActivity::class.java)
            startActivity(intent)
            finish()
        }

    }
}