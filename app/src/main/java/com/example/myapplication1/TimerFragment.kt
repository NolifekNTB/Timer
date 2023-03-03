package com.example.myapplication1

import android.os.Bundle
import android.os.CountDownTimer
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.myapplication1.databinding.FragmentTimerBinding


class TimerFragment : Fragment() {
    private var _binding: FragmentTimerBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTimerBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



        val timer = object : CountDownTimer(10000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                val seconds = millisUntilFinished/1000
                binding.TimerText.text = seconds.toString()
            }

            override fun onFinish() {
                binding.TimerText.text = "Ukończono DZYN DZYN"
            }
        }

        // To start the timer, call the start() method
        binding.StartButton.setOnClickListener{
            timer.start()
        }

        // To stop the timer, call the cancel() method
        binding.StopButton.setOnClickListener {
            timer.cancel()
        }
    }
}