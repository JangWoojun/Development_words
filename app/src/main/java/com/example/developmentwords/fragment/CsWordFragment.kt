package com.example.developmentwords.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.developmentwords.R
import com.example.developmentwords.databinding.FragmentCsWordBinding
import com.example.developmentwords.recyclerview.CsWordAdapter
import com.example.developmentwords.recyclerview.Word
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase


class CsWordFragment : Fragment() {
    private lateinit var auth: FirebaseAuth
    private var _binding: FragmentCsWordBinding? = null
    private val binding get() = _binding!!
    val TAG = "CsWordFragment"
    private val csWords = ArrayList<Word>()


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

                            csWords.add(Word(valueMap["word"]!!.toString(),valueMap["mean"]!!.toString()))
                        }
                        binding.list.adapter?.notifyDataSetChanged()
                    }

            }
        

        initializeViews()

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun initializeViews() {
        binding.list.layoutManager = LinearLayoutManager(context)
        binding.list.adapter = CsWordAdapter(csWords)
    }

}