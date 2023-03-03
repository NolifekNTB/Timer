package com.example.myapplication1

import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import com.example.myapplication1.databinding.FragmentTimePieceBinding

class TimePieceFragment : Fragment() {
    private var _binding: FragmentTimePieceBinding? = null
    private val binding get() = _binding!!

    private val handler = Handler()
    private var startTime: Long = 0
    private val stopWatchRunnable = object : Runnable {
        override fun run() {
            val elapsedTime = System.currentTimeMillis() - startTime
            val hours = elapsedTime / (60 * 60 * 1000)
            val minutes = (elapsedTime % (60 * 60 * 1000)) / (60 * 1000)
            val seconds = (elapsedTime % (60 * 1000)) / 1000
            val milliseconds = elapsedTime % 1000
            val timeString = String.format("%02d:%02d:%02d.%03d", hours, minutes, seconds, milliseconds)
            binding.TextView.text = timeString
            handler.postDelayed(this, 10)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTimePieceBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    //It's called immediately after onCreateView
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.StartButton.setOnClickListener {
            startTime = System.currentTimeMillis()
            handler.post(stopWatchRunnable)
        }

        binding.StopButton.setOnClickListener {
            handler.removeCallbacks(stopWatchRunnable)
        }
    }
}