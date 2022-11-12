package com.example.developmentwords.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import com.example.developmentwords.R
import com.example.developmentwords.databinding.FragmentCsWordBinding
import com.example.developmentwords.databinding.FragmentMainBinding


class CsWordFragment : Fragment() {
    private var _binding: FragmentCsWordBinding? = null
    private val binding get() = _binding!!

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

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}