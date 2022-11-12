package com.example.developmentwords.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import com.example.developmentwords.R
import com.example.developmentwords.databinding.FragmentMainBinding
import com.example.developmentwords.databinding.FragmentSettingBinding

class MainFragment : Fragment() {

    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentMainBinding.inflate(inflater, container, false)

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