package com.developmentwords.developmentwords.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import com.developmentwords.developmentwords.R
import com.developmentwords.developmentwords.databinding.FragmentWordBinding


class WordFragment : Fragment() {
    private var _binding: FragmentWordBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentWordBinding.inflate(inflater, container, false)

        binding.home.setOnClickListener{
            it.findNavController().navigate(R.id.action_wordFragment_to_mainFragment)
        }
        binding.learning.setOnClickListener{
            it.findNavController().navigate(R.id.action_wordFragment_to_studyFragment)
        }
        binding.setting.setOnClickListener{
            it.findNavController().navigate(R.id.action_wordFragment_to_settingFragment)
        }

        binding.csWords.setOnClickListener {
            it.findNavController().navigate(R.id.action_wordFragment_to_csWordFragment)
        }
        binding.englishWord.setOnClickListener {
            it.findNavController().navigate(R.id.action_wordFragment_to_englishWordFragment)
        }


        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}