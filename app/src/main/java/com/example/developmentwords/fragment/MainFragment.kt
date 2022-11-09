package com.example.developmentwords.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.developmentwords.R

class MainFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // it.findNAvContraller().navigate(R.id.프레그먼트 이름)으로 프레그먼트 이동

        return inflater.inflate(R.layout.fragment_main, container, false)
    }


}