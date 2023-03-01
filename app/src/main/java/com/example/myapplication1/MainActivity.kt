package com.example.myapplication1

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.myapplication1.databinding.ActivityMainBinding
import java.util.*


class MainActivity : AppCompatActivity() {
    //View only in this file, create variable without initialize, initialize to use in entire class
    private lateinit var binding: ActivityMainBinding


    var TimerFragment = TimerFragment()
    var AlarmClockFragment = AlarmClockFragment()
    var TimePieceFragment = TimePieceFragment()
    var WorldTimeFragment = WorldTimeFragment()



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //Create view base on xml file, layoutInflate - create view based on activity
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        //Start fragment
        setCurrentFragment(TimerFragment)

        //Listener of select bottom navigation's items
        binding.bottomNavigation.setOnNavigationItemSelectedListener {

            //If clicked element (itemId) is id, set current fragment to that id
            when(it.itemId){
                R.id.Timer -> setCurrentFragment(TimerFragment)
                R.id.Timepiece -> setCurrentFragment(TimePieceFragment)
                R.id.AlarmClock -> setCurrentFragment(AlarmClockFragment)
                R.id.WorldTime -> setCurrentFragment(WorldTimeFragment)
            }
            //return type true (boolean) because setCurrentFragment (unit) is cannot be return
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
        //Same as - supportFragmentManager.beginTransaction().replace(R.id.container, fragment).commit()

        //Responsible for performing actions on fragments
        supportFragmentManager
            //Start a series of transactions (each change) on Fragments
            .beginTransaction()
            //execute a block of code within the context of an object
            .apply {
                //Replace container (FrameLayout) to choose fragment
                replace(R.id.container, fragment)
                //Schedule a commit of transaction (work asynchronously)
                commit()
                //From API 24, I can use commitNow(), but shouldn't use with addBackStack()
            }
    }
}