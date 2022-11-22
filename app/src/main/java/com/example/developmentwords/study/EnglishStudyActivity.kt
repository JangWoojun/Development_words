package com.example.developmentwords.study

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import com.example.developmentwords.R
import com.example.developmentwords.databinding.ActivityEnglishStudyBinding
import com.example.developmentwords.recyclerview.Voca
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.yuyakaido.android.cardstackview.CardStackLayoutManager
import com.yuyakaido.android.cardstackview.CardStackListener
import com.yuyakaido.android.cardstackview.Direction

class EnglishStudyActivity : AppCompatActivity() {
    private lateinit var binding: ActivityEnglishStudyBinding
    lateinit var cardStackAdapter: CardStackAdapter
    lateinit var manager: CardStackLayoutManager
    private val list = mutableListOf<Voca>()
    private lateinit var auth: FirebaseAuth

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
}