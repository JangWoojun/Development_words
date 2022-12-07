package com.developmentwords.developmentwords.fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.developmentwords.developmentwords.R
import com.developmentwords.developmentwords.databinding.FragmentCsWordBinding
import com.developmentwords.developmentwords.quiz.QuizActivity
import com.developmentwords.developmentwords.recyclerview.Voca
import com.developmentwords.developmentwords.recyclerview.WordAdapter
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase


class CsWordFragment : Fragment() {
    private lateinit var auth: FirebaseAuth
    private var _binding: FragmentCsWordBinding? = null
    private val binding get() = _binding!!
    private val csWords = ArrayList<Voca>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCsWordBinding.inflate(inflater, container, false)

        binding.home.setOnClickListener {
            it.findNavController().navigate(R.id.action_csWordFragment_to_mainFragment)
        }
        binding.words.setOnClickListener {
            it.findNavController().navigate(R.id.action_csWordFragment_to_wordFragment)
        }
        binding.learning.setOnClickListener {
            it.findNavController().navigate(R.id.action_csWordFragment_to_studyFragment)
        }
        binding.setting.setOnClickListener {
            it.findNavController().navigate(R.id.action_csWordFragment_to_settingFragment)
        }

        binding.levelUp.setOnClickListener {
            val intent = Intent(context, QuizActivity::class.java)
            intent.putExtra("type","cs")
            startActivity(intent)
        }


        firebase()
        initializeViews()

        return binding.root
    }

    private fun firebase(){
        auth = Firebase.auth
        val database = Firebase.database
        val levelRef = database.getReference("users").child(auth.currentUser?.uid.toString()).child("csLevel")

        levelRef.get().addOnSuccessListener {
            val value = it.value
            binding.csWordLv.text = getString(R.string.cs_word_lv,value)
            val wordRef = database.getReference("csWord").child("lv"+value.toString())

            wordRef.get().addOnSuccessListener { it ->
                val value : ArrayList<Any> = it.value as ArrayList<Any>
                for (i in 1..15){
                    val valueMap: HashMap<String,String> = value[i] as HashMap<String, String>

                    csWords.add(Voca(valueMap["word"]!!.toString(),valueMap["mean"]!!.toString()))
                }
                binding.list.adapter?.notifyDataSetChanged()
            }

        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun initializeViews() {
        binding.list.layoutManager = LinearLayoutManager(context)
        binding.list.adapter = WordAdapter(csWords)
    }

}