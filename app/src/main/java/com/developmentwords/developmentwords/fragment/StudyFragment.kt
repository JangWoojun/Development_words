package com.developmentwords.developmentwords.fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import com.developmentwords.developmentwords.R
import com.developmentwords.developmentwords.databinding.FragmentStudyBinding
import com.developmentwords.developmentwords.study.CsStudyActivity
import com.developmentwords.developmentwords.study.EnglishStudyActivity


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
            val intent = Intent(context,CsStudyActivity::class.java)
            startActivity(intent)
        }
        binding.linearLayout2.setOnClickListener {
            val intent = Intent(context,EnglishStudyActivity::class.java)
            startActivity(intent)
        }


        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}