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
    private val csWords = listOf(
        Word("네카라쿠베1","이것은 네카라쿠베1 설명입니다"),
        Word("네카라쿠베2","이것은 네카라쿠베2 설명입니다"),
        Word("네카라쿠베3","이것은 네카라쿠베3 설명입니다"),
        Word("네카라쿠베4","이것은 네카라쿠베4 설명입니다"),
        Word("네카라쿠베5","이것은 네카라쿠베5 설명입니다"),
        Word("네카라쿠베6","이것은 네카라쿠베6 설명입니다"),
        Word("네카라쿠베7","이것은 네카라쿠베7 설명입니다"),
        Word("네카라쿠베8","이것은 네카라쿠베8 설명입니다"),
        Word("네카라쿠베9","이것은 네카라쿠베9 설명입니다"),
        )

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
        val myRef = database.getReference("users").child(auth.currentUser?.uid.toString()).child("csLevel")

        myRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val value = dataSnapshot.value
                binding.csWordLv.text = getString(R.string.cs_word_lv,value)

            }

            override fun onCancelled(error: DatabaseError) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException())
            }
        })

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