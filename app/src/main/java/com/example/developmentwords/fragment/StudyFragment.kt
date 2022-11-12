package com.example.developmentwords.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import com.example.developmentwords.R
import com.example.developmentwords.databinding.FragmentStudyBinding


class StudyFragment : Fragment() {
    private var _binding: FragmentStudyBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentStudyBinding.inflate(inflater, container, false)

        binding.words.setOnClickListener{
            it.findNavController().navigate(R.id.action_studyFragment_to_wordFragment)
        }
        binding.home.setOnClickListener{
            it.findNavController().navigate(R.id.action_studyFragment_to_mainFragment)
        }
        binding.setting.setOnClickListener{
            it.findNavController().navigate(R.id.action_studyFragment_to_settingFragment)
        }

        binding.linearLayout1.setOnClickListener {
            it.findNavController().navigate(R.id.action_studyFragment_to_csStudyFragment)
        }
        binding.linearLayout2.setOnClickListener {
            it.findNavController().navigate(R.id.action_studyFragment_to_englishStudyFragment)
        }


        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}