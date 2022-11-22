package com.example.developmentwords.study

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import com.example.developmentwords.R
import com.example.developmentwords.databinding.ActivityCsStudyBinding
import com.example.developmentwords.recyclerview.Voca
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.yuyakaido.android.cardstackview.CardStackLayoutManager
import com.yuyakaido.android.cardstackview.CardStackListener
import com.yuyakaido.android.cardstackview.Direction

class CsStudyActivity : AppCompatActivity() {
    private lateinit var binding : ActivityCsStudyBinding
    lateinit var cardStackAdapter: CardStackAdapter
    lateinit var manager: CardStackLayoutManager
    private val list = mutableListOf<Voca>()
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cs_study)
        binding = ActivityCsStudyBinding.inflate(layoutInflater)
        setContentView(binding.root)
        auth = Firebase.auth

        manager = CardStackLayoutManager(baseContext,object : CardStackListener{
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