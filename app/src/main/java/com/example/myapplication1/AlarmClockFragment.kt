package com.example.myapplication1

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.myapplication1.databinding.FragmentAlarmClockBinding
import java.util.*


class AlarmClockFragment : Fragment() {
    private var _binding: FragmentAlarmClockBinding? = null
    private val binding get() = _binding!!

    private lateinit var alarmManager: AlarmManager
    private lateinit var pendingIntent: PendingIntent
    private lateinit var alarmReceiver: BroadcastReceiver

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentAlarmClockBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // Create a BroadcastReceiver to handle the alarm
        alarmReceiver = object : BroadcastReceiver() {
            override fun onReceive(context: Context, intent: Intent) {
                // Handle the alarm when it goes off
            }
        }
        binding.setAlarmButton.setOnClickListener {
            onSetAlarmClick()
        }
    }

    private fun onSetAlarmClick() {
        val editText = binding.Godzina as EditText
        val value = editText.text.toString().toInt()

        val editText2 = binding.Minuta as EditText
        val value2 = editText2.text.toString().toInt()

        val calendar = Calendar.getInstance().apply {
                set(Calendar.HOUR_OF_DAY, value)
                set(Calendar.MINUTE, value2)
                set(Calendar.SECOND, 0)
                set(Calendar.MILLISECOND, 0)
            }

            alarmManager = requireContext().getSystemService(Context.ALARM_SERVICE) as AlarmManager

            // Create an Intent for the BroadcastReceiver
            val intent = Intent(requireContext(), alarmReceiver::class.java)

            // Create a PendingIntent for the BroadcastReceiver
            pendingIntent = PendingIntent.getBroadcast(
                requireContext(),
                0,
                intent,
                PendingIntent.FLAG_UPDATE_CURRENT
            )

            alarmManager.setExact(
                AlarmManager.RTC_WAKEUP,
                calendar.timeInMillis,
                pendingIntent
            )
            Toast.makeText(requireContext(), "Alarm set for $value:$value2", Toast.LENGTH_SHORT).show()
        }
    override fun onStart() {
        super.onStart()
        requireContext().registerReceiver(alarmReceiver, IntentFilter(alarmReceiver::class.java.name))
    }

    override fun onStop() {
        super.onStop()
        requireContext().unregisterReceiver(alarmReceiver)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}