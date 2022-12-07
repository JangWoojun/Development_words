package com.developmentwords.developmentwords.quiz

import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.developmentwords.developmentwords.MainActivity
import com.developmentwords.developmentwords.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import es.dmoral.toasty.Toasty

class QuizActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    private val handler = Handler(Looper.getMainLooper())
    var o = 0
    var x = 0
    var mBackWait:Long = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz)
        val type = intent.getStringExtra("type")
        auth = Firebase.auth

        val database = Firebase.database
        val myRef = database.getReference("users").child(auth.currentUser?.uid.toString())

        myRef.get().addOnSuccessListener{
            val value1 = it.value as HashMap<String,Long>
            val value = value1["${type}Level"] as Long
            if (value>2){
                Toasty.normal(this, "다음 레벨 컨텐츠는 준비중입니다", Toast.LENGTH_SHORT).show()

                val intent = Intent(this,MainActivity::class.java)
                startActivity(intent)
            }
            else {
                if (type == "english"){
                    get("english",1)
                }
                else {
                    get("cs",1)
                }
            }
        }

    }
    fun get(type : String,i: Int){
        if (i==16){
            val intent = Intent(this,QuizResultActivity::class.java)
            intent.putExtra("o",o.toString())
            intent.putExtra("x",x.toString())
            intent.putExtra("type",type)
            startActivity(intent)
            finish()
        }
        else{
            val database = Firebase.database
            val level = database.getReference("users").child(auth.currentUser?.uid.toString()).child("${type}Level")
            val j = i+1
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
                    val item1 : ImageView = findViewById(R.id.cardItem1)
                    val item2 : ImageView = findViewById(R.id.cardItem2)

                    word.text = valueMap["word"]
                    tvOptionOne.text = valueMap["option1"]
                    tvOptionTwo.text = valueMap["option2"]
                    tvOptionThree.text = valueMap["option3"]
                    tvOptionFour.text = valueMap["option4"]

                    tvOptionOne.isClickable = true
                    tvOptionTwo.isClickable = true
                    tvOptionThree.isClickable = true
                    tvOptionFour.isClickable = true

                    val chk = valueMap["chk"]?.toInt()


                    tvOptionOne.setOnClickListener {

                        val click = 1
                        tvOptionOne.isClickable = false
                        tvOptionTwo.isClickable = false
                        tvOptionThree.isClickable = false
                        tvOptionFour.isClickable = false

                        if (chk == click){
                            it.setBackgroundResource(R.drawable.radius_green)
                            tvOptionOne.setTextColor(Color.parseColor("#ffffff"))
                            tvOptionOne.setTypeface( null, Typeface.BOLD )
                            item1.visibility = View.VISIBLE

                            o+=1

                            handler.postDelayed({
                                get(type,j)
                            },100)

                            handler.postDelayed({
                                item1.visibility = View.GONE

                                clear()

                            }, 500)

                        }
                        else {
                            it.setBackgroundResource(R.drawable.radius_red)
                            tvOptionOne.setTextColor(Color.parseColor("#ffffff"))
                            tvOptionOne.setTypeface( null, Typeface.BOLD )
                            item2.visibility = View.VISIBLE

                            x+=1

                            handler.postDelayed({
                                get(type,j)
                            },100)

                            handler.postDelayed({
                                item2.visibility = View.GONE

                                clear()

                            }, 500)

                        }
                    }
                    tvOptionTwo.setOnClickListener {
                        val click = 2

                        tvOptionOne.isClickable = false
                        tvOptionTwo.isClickable = false
                        tvOptionThree.isClickable = false
                        tvOptionFour.isClickable = false

                        if (chk == click){
                            it.setBackgroundResource(R.drawable.radius_green)
                            tvOptionTwo.setTextColor(Color.parseColor("#ffffff"))
                            tvOptionTwo.setTypeface( null, Typeface.BOLD )
                            item1.visibility = View.VISIBLE

                            o+=1

                            handler.postDelayed({
                                get(type,j)
                            },100)

                            handler.postDelayed({
                                item1.visibility = View.GONE

                                clear()

                            }, 500)

                        }
                        else {
                            it.setBackgroundResource(R.drawable.radius_red)
                            tvOptionTwo.setTextColor(Color.parseColor("#ffffff"))
                            tvOptionTwo.setTypeface( null, Typeface.BOLD )
                            item2.visibility = View.VISIBLE

                            x+=1

                            handler.postDelayed({
                                get(type,j)
                            },100)

                            handler.postDelayed({
                                item2.visibility = View.GONE

                                clear()

                            }, 500)

                        }
                    }
                    tvOptionThree.setOnClickListener {
                        val click = 3

                        tvOptionOne.isClickable = false
                        tvOptionTwo.isClickable = false
                        tvOptionThree.isClickable = false
                        tvOptionFour.isClickable = false

                        if (chk == click){
                            it.setBackgroundResource(R.drawable.radius_green)
                            tvOptionThree.setTextColor(Color.parseColor("#ffffff"))
                            tvOptionThree.setTypeface( null, Typeface.BOLD )
                            item1.visibility = View.VISIBLE

                            o+=1

                            handler.postDelayed({
                                get(type,j)
                            },100)

                            handler.postDelayed({
                                item1.visibility = View.GONE

                                clear()

                            }, 500)

                        }
                        else {
                            it.setBackgroundResource(R.drawable.radius_red)
                            tvOptionThree.setTextColor(Color.parseColor("#ffffff"))
                            tvOptionThree.setTypeface( null, Typeface.BOLD )
                            item2.visibility = View.VISIBLE

                            x+=1

                            handler.postDelayed({
                                get(type,j)
                            },100)

                            handler.postDelayed({
                                item2.visibility = View.GONE

                                clear()

                            }, 500)

                        }
                    }
                    tvOptionFour.setOnClickListener {
                        val click = 4

                        tvOptionOne.isClickable = false
                        tvOptionTwo.isClickable = false
                        tvOptionThree.isClickable = false
                        tvOptionFour.isClickable = false

                        if (chk == click){
                            it.setBackgroundResource(R.drawable.radius_green)
                            tvOptionFour.setTextColor(Color.parseColor("#ffffff"))
                            tvOptionFour.setTypeface( null, Typeface.BOLD )
                            item1.visibility = View.VISIBLE

                            o+=1

                            handler.postDelayed({
                                get(type,j)
                            },100)

                            handler.postDelayed({
                                item1.visibility = View.GONE

                                clear()

                            }, 500)

                        }
                        else {
                            it.setBackgroundResource(R.drawable.radius_red)
                            tvOptionFour.setTextColor(Color.parseColor("#ffffff"))
                            tvOptionFour.setTypeface( null, Typeface.BOLD )
                            item2.visibility = View.VISIBLE

                            x+=1

                            handler.postDelayed({
                                get(type,j)
                            },100)

                            handler.postDelayed({
                                item2.visibility = View.GONE

                                clear()

                            }, 500)

                        }
                    }


                }

            }
        }

    }
    private fun clear(){
        val tvOptionOne:Button = findViewById(R.id.tv_option_one)
        val tvOptionTwo:Button = findViewById(R.id.tv_option_two)
        val tvOptionThree:Button = findViewById(R.id.tv_option_three)
        val tvOptionFour:Button = findViewById(R.id.tv_option_four)

        tvOptionOne.setBackgroundResource(R.drawable.radius_white)
        tvOptionOne.setTextColor(Color.parseColor("#7A8089"))

        tvOptionTwo.setBackgroundResource(R.drawable.radius_white)
        tvOptionTwo.setTextColor(Color.parseColor("#7A8089"))

        tvOptionThree.setBackgroundResource(R.drawable.radius_white)
        tvOptionThree.setTextColor(Color.parseColor("#7A8089"))

        tvOptionFour.setBackgroundResource(R.drawable.radius_white)
        tvOptionFour.setTextColor(Color.parseColor("#7A8089"))
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