package com.example.developmentwords.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import com.example.developmentwords.R
import com.example.developmentwords.databinding.FragmentMainBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.*
import com.google.firebase.database.ktx.database
import com.google.firebase.database.ktx.getValue
import com.google.firebase.ktx.Firebase

class MainFragment : Fragment() {
    private lateinit var auth: FirebaseAuth
    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!

    val TAG = "MainFragment"

    @SuppressLint("SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentMainBinding.inflate(inflater, container, false)

        auth = Firebase.auth
        val database = Firebase.database
        val myRef = database.getReference("users").child(auth.currentUser?.uid.toString())

        myRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val value : HashMap<String, Long> = dataSnapshot.value as HashMap<String, Long>

                val csLevel = value["csLevel"]
                val englishLevel = value["englishLevel"]

                binding.statText.text = getString(R.string.statText1,csLevel.toString(),englishLevel.toString())
            }

            override fun onCancelled(error: DatabaseError) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException())
            }
        })


        binding.words.setOnClickListener{
            it.findNavController().navigate(R.id.action_mainFragment_to_wordFragment)
        }
        binding.learning.setOnClickListener{
            it.findNavController().navigate(R.id.action_mainFragment_to_studyFragment)
        }
        binding.setting.setOnClickListener{
            it.findNavController().navigate(R.id.action_mainFragment_to_settingFragment)
        }

        binding.linearLayout.setOnClickListener {
            it.findNavController().navigate(R.id.action_mainFragment_to_csWordFragment)
        }
        binding.linearLayout3.setOnClickListener {
            it.findNavController().navigate(R.id.action_mainFragment_to_englishWordFragment)
        }
        binding.linearLayout2.setOnClickListener {
            it.findNavController().navigate(R.id.action_mainFragment_to_csStudyFragment)
        }
        binding.linearLayout5.setOnClickListener {
            it.findNavController().navigate(R.id.action_mainFragment_to_englishStudyFragment)
        }

        return binding.root

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}