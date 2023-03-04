package com.example.myapplication1

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.myapplication1.databinding.FragmentWorldTimeBinding
import java.text.SimpleDateFormat
import java.util.*
import java.util.Timer
import java.util.TimerTask
import androidx.core.app.ActivityCompat




class WorldTimeFragment : Fragment() {
    private var _binding: FragmentWorldTimeBinding? = null
    private val binding get() = _binding!!

    //Handler send and process Message and runnable objects with a thread's 'MessageQueue'
    private val handler = Handler()

    //Create object (annonymous class) which inherit from Runnable class
    private val updateRunnable = object : Runnable {
        //Contain code that will be executed when runnable wll be executed
        override fun run() {
            //PostDelayed - add this (object) to MessageQueue, to be run after specific time
            handler.postDelayed(this, 1000)
            Timer()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentWorldTimeBinding.inflate(layoutInflater, container, false)
        Timer()
        return binding.root
    }

    //OnResume - first foregroud (when user interact with app)
    override fun onResume() {
        super.onResume()
        //add updateRunnable to MessageQueue
        handler.postDelayed(updateRunnable, 1000)
    }

    //onPause - when user leave activity
    override fun onPause() {
        super.onPause()
        //Remove all messagess that been sending to MessageQueue from updateRunnable
        handler.removeCallbacks(updateRunnable)
    }

    fun Timer() {
            val simpleDate = SimpleDateFormat("dd/M/yyyy hh:mm:ss")
            val currentDate = simpleDate.format(Date())
            binding.Text.text = "Current Date is: " + currentDate
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
