package com.example.developmentwords.fragment

import android.animation.ObjectAnimator
import android.content.Context
import android.content.Context.VIBRATOR_SERVICE
import android.os.Bundle
import android.os.Vibrator
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.developmentwords.R
import com.example.developmentwords.databinding.FragmentCsStudyBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase


class CsStudyFragment : Fragment() {
    private var _binding: FragmentCsStudyBinding? = null
    private val binding get() = _binding!!
    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentCsStudyBinding.inflate(inflater, container, false)
        auth = Firebase.auth

        var i = 1

        get(i)

        var chk = 0

        binding.ok.setOnClickListener {
            if (i<15){
                i++
                get(i)
                binding.message.text = getString(R.string.good)
                binding.message.visibility = View.VISIBLE
                val fadeOut = ObjectAnimator.ofFloat(binding.message, "alpha", 1f, 0f)
                fadeOut.duration = 1500
                fadeOut.start()



            }
        }

        binding.no.setOnClickListener {
            if (i<15){
                i++
                get(i)
                chk++
                binding.message.text = getString(R.string.bad)
                binding.message.visibility = View.VISIBLE
                val fadeOut = ObjectAnimator.ofFloat(binding.message, "alpha", 1f, 0f)
                fadeOut.duration = 1500
                fadeOut.start()



            }
        }

        return binding.root
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
    }

    fun get(i : Int){
        val database = Firebase.database
        val level = database.getReference("users").child(auth.currentUser?.uid.toString()).child("csLevel")

        level.get().addOnSuccessListener{
            val value = it.value
            binding.csWordLv.text = "CS Lv.$value"
            val word = database.getReference("csWord").child("lv$value").child("$i").child("word")
            val mean = database.getReference("csWord").child("lv$value").child("$i").child("mean")

            word.get().addOnSuccessListener {
                binding.word.text = it.value.toString()
            }
            mean.get().addOnSuccessListener {
                binding.mean.text = it.value.toString()
            }

        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}