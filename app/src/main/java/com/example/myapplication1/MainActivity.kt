package com.example.myapplication1

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import com.example.myapplication1.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationMenuView
import com.google.android.material.bottomnavigation.BottomNavigationView
import java.text.SimpleDateFormat
import java.util.*


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding


    var homeFragment = HomeFragment()
    var notificationFragment = NotificationFragment()
    var settingsFragment = SettingsFragment()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //refreshTime()

        setCurrentFragment(homeFragment)

        binding.bottomNavigation.setOnNavigationItemSelectedListener {
            when(it.itemId){
                R.id.home -> setCurrentFragment(homeFragment)
                R.id.notification -> setCurrentFragment(notificationFragment)
                R.id.settings -> setCurrentFragment(settingsFragment)
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

    fun Timer() {
        val simpleDate = SimpleDateFormat("dd/M/yyyy hh:mm:ss")
        val currentDate = simpleDate.format(Date())
        //binding.Text.text = "Current Date is: " + currentDate
    }

     */

    fun setCurrentFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.container, fragment)
            commit()
        }
    }
}