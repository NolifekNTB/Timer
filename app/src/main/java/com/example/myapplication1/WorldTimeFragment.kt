package com.example.myapplication1

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.myapplication1.databinding.FragmentWorldTimeBinding
import java.text.SimpleDateFormat
import java.util.*
import java.util.Timer

class WorldTimeFragment : Fragment() {
    private var _binding: FragmentWorldTimeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentWorldTimeBinding.inflate(layoutInflater, container, false)
        Timer()
        return binding.root
    }

        fun Timer() {
            val simpleDate = SimpleDateFormat("dd/M/yyyy hh:mm:ss")
            val currentDate = simpleDate.format(Date())
            binding.Text.text = "Current Date is: " + currentDate
        }
}
