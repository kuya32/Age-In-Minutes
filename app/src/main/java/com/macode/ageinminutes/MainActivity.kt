package com.macode.ageinminutes

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_minutes.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        val secondsFragment = SecondsFragment()
        val minutesFragment = MinutesFragment()
        val hoursFragment = HoursFragment()
        val daysFragment = DaysFragment()
        val weeksFragment = WeeksFragment()

        makeCurrentFragment(secondsFragment)

        bottomNavigation.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.seconds -> makeCurrentFragment(secondsFragment)
                R.id.minutes -> makeCurrentFragment(minutesFragment)
                R.id.hours -> makeCurrentFragment(hoursFragment)
                R.id.days -> makeCurrentFragment(daysFragment)
                R.id.weeks -> makeCurrentFragment(weeksFragment)
            }
            true
        }
    }

    private fun makeCurrentFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.mainFragmentContainer, fragment)
            commit()
        }
    }
}