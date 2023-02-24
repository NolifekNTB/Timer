package com.example.myapplication1

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.myapplication1.databinding.ActivityMainBinding
import java.util.*


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding


    var TimerFragment = TimerFragment()
    var AlarmClockFragment = AlarmClockFragment()
    var TimePieceFragment = TimePieceFragment()
    var WorldTimeFragment = WorldTimeFragment()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setCurrentFragment(TimerFragment)

        binding.bottomNavigation.setOnNavigationItemSelectedListener {
            when(it.itemId){
                R.id.Timer -> setCurrentFragment(TimerFragment)
                R.id.Timepiece -> setCurrentFragment(TimePieceFragment)
                R.id.AlarmClock -> setCurrentFragment(AlarmClockFragment)
                R.id.WorldTime -> setCurrentFragment(WorldTimeFragment)
            }
            true
        }
    }

    /*fun refreshTime() {
        val thread: Thread = object : Thread() {
            override fun run() {
                try {
                    while (!this.isInterrupted) {
                        sleep(1000)
                        runOnUiThread {
                            Timer()
                        }
                    }
                } catch (_: InterruptedException) {
                }
            }
        }
        thread.start()
    }
     */

    fun setCurrentFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.container, fragment)
            commit()
        }
    }
}