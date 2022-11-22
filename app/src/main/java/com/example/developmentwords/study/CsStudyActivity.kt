package com.example.developmentwords.study

import android.animation.ObjectAnimator
import android.content.Context
import android.os.Bundle
import android.os.Vibrator
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.developmentwords.R
import com.example.developmentwords.databinding.ActivityCsStudyBinding
import com.example.developmentwords.recyclerview.Voca
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.yuyakaido.android.cardstackview.*


class CsStudyActivity : AppCompatActivity() {
    private lateinit var binding : ActivityCsStudyBinding
    lateinit var cardStackAdapter: CardStackAdapter
    lateinit var manager: CardStackLayoutManager
    private val list = mutableListOf<Voca>()
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCsStudyBinding.inflate(layoutInflater)
        setContentView(binding.root)
        auth = Firebase.auth

        manager = CardStackLayoutManager(baseContext,object : CardStackListener{
            override fun onCardDragging(direction: Direction?, ratio: Float) {
            }

            override fun onCardSwiped(direction: Direction?) {
                if (direction == Direction.Left){
                    binding.message.text = getString(R.string.bad)
                    binding.message.visibility = View.VISIBLE
                    val fadeOut = ObjectAnimator.ofFloat(binding.message, "alpha", 1f, 0f)
                    fadeOut.duration = 1500
                    fadeOut.start()
                    val vibe = getSystemService(VIBRATOR_SERVICE) as Vibrator
                    vibe.vibrate(50)
                }
                else if (direction == Direction.Right){
                    binding.message.text = getString(com.example.developmentwords.R.string.good)
                    binding.message.visibility = View.VISIBLE
                    val fadeOut = ObjectAnimator.ofFloat(binding.message, "alpha", 1f, 0f)
                    fadeOut.duration = 1500
                    fadeOut.start()
                    val vibe = getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
                    vibe.vibrate(50)
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

        manager.setStackFrom(StackFrom.Bottom)
        manager.setSwipeThreshold(0.2f)

        cardStackAdapter = CardStackAdapter(list)
        binding.cardStackView.layoutManager = manager
        binding.cardStackView.adapter = cardStackAdapter

        get()


    }
    fun get(){
        val database = Firebase.database
        val level = database.getReference("users").child(auth.currentUser?.uid.toString()).child("csLevel")

        level.get().addOnSuccessListener{

            val value = it.value
            binding.csWordLv.text = "CS Lv.$value"
            val voca = database.getReference("csWord").child("lv$value")
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
}