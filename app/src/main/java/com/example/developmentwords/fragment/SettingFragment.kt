package com.example.developmentwords.fragment

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.example.developmentwords.R
import com.example.developmentwords.databinding.FragmentSettingBinding


class SettingFragment : Fragment() {

    private var _binding: FragmentSettingBinding? = null
    private val binding get() = _binding!!
    private val addresses : Array<String> = arrayOf("woojun0107@naver.com")

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

        binding.click1.setOnClickListener {
            val intent = Intent(Intent.ACTION_SEND)
            intent.type = "*/*"
            intent.setPackage("com.google.android.gm")

            intent.putExtra(Intent.EXTRA_EMAIL, addresses)
            intent.putExtra(Intent.EXTRA_SUBJECT, "코딩보카 관련 문의")
            intent.putExtra(Intent.EXTRA_TEXT, "(앱에 대한 문의사항 또는 건의할 점을 알려주세요!)")

            startActivity(intent)
        }

        binding.click2.setOnClickListener {
            val intent = Intent(Intent.ACTION_SEND)
            intent.type = "*/*"
            intent.setPackage("com.google.android.gm")
            intent.putExtra(Intent.EXTRA_EMAIL, addresses)
            intent.putExtra(Intent.EXTRA_SUBJECT, "코딩보카 오류 제보")
            intent.putExtra(Intent.EXTRA_TEXT, "(앱을 사용하시며 겪은 오류사항을 제보해주세요!)")

            startActivity(intent)
        }



        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}