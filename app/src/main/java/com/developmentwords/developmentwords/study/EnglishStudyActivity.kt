package com.developmentwords.developmentwords.study

import android.animation.ObjectAnimator
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Vibrator
import android.util.Log
import android.view.View
import android.widget.Toast
import com.developmentwords.developmentwords.R
import com.developmentwords.developmentwords.databinding.ActivityEnglishStudyBinding
import com.developmentwords.developmentwords.recyclerview.Voca
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.yuyakaido.android.cardstackview.*
import es.dmoral.toasty.Toasty

class EnglishStudyActivity : AppCompatActivity() {
    var mBackWait:Long = 0
    private lateinit var binding: ActivityEnglishStudyBinding
    lateinit var cardStackAdapter: CardStackAdapter
    lateinit var manager: CardStackLayoutManager
    private val list = mutableListOf<Voca>()
    private lateinit var auth: FirebaseAuth
    private var count = 0
    var o = 0
    var x = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_english_study)
        binding = ActivityEnglishStudyBinding.inflate(layoutInflater)
        setContentView(binding.root)
        auth = Firebase.auth

        manager = CardStackLayoutManager(baseContext,object : CardStackListener {
            override fun onCardDragging(direction: Direction?, ratio: Float) {
            }

            override fun onCardSwiped(direction: Direction?) {
                if (direction == Direction.Left){
                    ++x
                    binding.message.text = getString(R.string.bad)
                    binding.message.visibility = View.VISIBLE
                    val fadeOut = ObjectAnimator.ofFloat(binding.message, "alpha", 1f, 0f)
                    fadeOut.duration = 1500
                    fadeOut.start()
                    val vibe = getSystemService(VIBRATOR_SERVICE) as Vibrator
                    vibe.vibrate(50)
                    ++count
                    Log.d("확인",count.toString())
                }
                else {
                    ++o
                    binding.message.text = getString(com.developmentwords.developmentwords.R.string.good)
                    binding.message.visibility = View.VISIBLE
                    val fadeOut = ObjectAnimator.ofFloat(binding.message, "alpha", 1f, 0f)
                    fadeOut.duration = 1500
                    fadeOut.start()
                    val vibe = getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
                    vibe.vibrate(50)
                    ++count
                    Log.d("확인",count.toString())
                }
                if (count==15){
                    val intent = Intent(this@EnglishStudyActivity,ResultActivity::class.java)
                    intent.putExtra("o",o.toString())
                    intent.putExtra("x",x.toString())
                    intent.putExtra("type","english")
                    startActivity(intent)
                    finish()
                }
            }

            override fun onCardRewound() {
            }

            override fun onCardCanceled() {
            }

            override fun onCardAppeared(view: View?, position: Int) {
            }

            override fun onCardDisappeared(view: View?, position: Int) {
            }

        })

        cardStackAdapter = CardStackAdapter(list)
        binding.cardStackView.layoutManager = manager
        binding.cardStackView.adapter = cardStackAdapter

        get()

        manager.setStackFrom(StackFrom.Bottom)
        manager.setSwipeThreshold(0.2f)

        cardStackAdapter = CardStackAdapter(list)
        binding.cardStackView.layoutManager = manager
        binding.cardStackView.adapter = cardStackAdapter

        get()

        binding.no.setOnClickListener {
            val setting1 = SwipeAnimationSetting.Builder()
                .setDirection(Direction.Left)
                .build()
            manager.setSwipeAnimationSetting(setting1)
            binding.cardStackView.swipe()
        }
        binding.ok.setOnClickListener{
            val setting2 = SwipeAnimationSetting.Builder()
                .setDirection(Direction.Right)
                .build()
            manager.setSwipeAnimationSetting(setting2)
            binding.cardStackView.swipe()
            binding.cardStackView.swipe()
        }

    }
    fun get(){
        val database = Firebase.database
        val level = database.getReference("users").child(auth.currentUser?.uid.toString()).child("englishLevel")

        level.get().addOnSuccessListener{

            val value = it.value
            binding.csWordLv.text = "영어 Lv.$value"
            val voca = database.getReference("englishWord").child("lv$value")
            Log.d("여기",voca.toString())

            voca.get().addOnSuccessListener {
                val value : ArrayList<Any> = it.value as ArrayList<Any>
                for (i in 1..15){
                    val valueMap: HashMap<String,String> = value[i] as HashMap<String, String>

                    list.add(Voca(valueMap["word"]!!.toString(),valueMap["mean"]!!.toString()))
                }
                binding.cardStackView.adapter?.notifyDataSetChanged()
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