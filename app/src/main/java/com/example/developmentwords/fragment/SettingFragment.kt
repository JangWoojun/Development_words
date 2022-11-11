package com.example.developmentwords.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import com.example.developmentwords.R
import com.example.developmentwords.databinding.FragmentSettingBinding


class SettingFragment : Fragment() {

    private var _binding: FragmentSettingBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSettingBinding.inflate(inflater, container, false)

        binding.words.setOnClickListener{
            it.findNavController().navigate(R.id.action_settingFragment_to_wordFragment)
        }
        binding.learning.setOnClickListener{
            it.findNavController().navigate(R.id.action_settingFragment_to_studyFragment)
        }
        binding.home.setOnClickListener{
            it.findNavController().navigate(R.id.action_settingFragment_to_mainFragment)
        }


        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}