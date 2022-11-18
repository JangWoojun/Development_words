package com.example.developmentwords.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.developmentwords.R
import com.example.developmentwords.databinding.FragmentEnglishWordBinding
import com.example.developmentwords.recyclerview.EnglishWordAdapter
import com.example.developmentwords.recyclerview.Word

class EnglishWordFragment : Fragment() {
    private var _binding: FragmentEnglishWordBinding? = null
    private val binding get() = _binding!!
    val list = listOf<Word>(
        Word("NCLCB1","This is NCLCB1 mean"),
        Word("NCLCB2","This is NCLCB2 mean"),
        Word("NCLCB3","This is NCLCB3 mean"),
        Word("NCLCB4","This is NCLCB4 mean"),
        Word("NCLCB5","This is NCLCB5 mean"),
        Word("NCLCB6","This is NCLCB6 mean"),
        Word("NCLCB7","This is NCLCB7 mean"),
        Word("NCLCB8","This is NCLCB8 mean"),
        Word("NCLCB9","This is NCLCB9 mean")

        )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentEnglishWordBinding.inflate(inflater, container, false)

        binding.home.setOnClickListener {
            it.findNavController().navigate(R.id.action_englishWordFragment_to_mainFragment)
        }
        binding.words.setOnClickListener {
            it.findNavController().navigate(R.id.action_englishWordFragment_to_wordFragment)
        }
        binding.learning.setOnClickListener {
            it.findNavController().navigate(R.id.action_englishWordFragment_to_studyFragment)
        }
        binding.setting.setOnClickListener {
            it.findNavController().navigate(R.id.action_englishWordFragment_to_settingFragment)
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
        binding.list.adapter = EnglishWordAdapter(list)
    }
}