package com.example.developmentwords.fragment

import android.os.Bundle
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


class CsWordFragment : Fragment() {
    private var _binding: FragmentCsWordBinding? = null
    private val binding get() = _binding!!
    private val csWords = listOf(
        Word("네카라쿠베1"),
        Word("네카라쿠베2"),
        Word("네카라쿠베3"),
        Word("네카라쿠베4"),
        Word("네카라쿠베5"),
        Word("네카라쿠베6"),
        Word("네카라쿠베7"),
        Word("네카라쿠베8"),
        Word("네카라쿠베9"),
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