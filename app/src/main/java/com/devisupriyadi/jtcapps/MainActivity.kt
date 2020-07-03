package com.devisupriyadi.jtcapps

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.FrameLayout
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private var content: FrameLayout? = null

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.omah -> {
                val fragment = OmaheFragment.NewInstance()
                addFragment(fragment)
                return@OnNavigationItemSelectedListener true
            }
            R.id.futue -> {
                val fragment = FotoFragment()
                addFragment(fragment)
                return@OnNavigationItemSelectedListener true
            }
            R.id.videoo -> {
                val fragment = VideoFragment()
                addFragment(fragment)
                return@OnNavigationItemSelectedListener true
            }
            R.id.membs -> {
                val fragment = MembFragment()
                addFragment(fragment)
                return@OnNavigationItemSelectedListener true
            }
            R.id.contacts -> {
                val fragment = KontakFragment()
                addFragment(fragment)
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }
    private fun addFragment(fragment: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .setCustomAnimations(R.anim.design_bottom_sheet_slide_in, R.anim.design_bottom_sheet_slide_out)
            .replace(R.id.content, fragment, fragment.javaClass.getSimpleName())
            .commit()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        botnavmain.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
        val fragment = OmaheFragment.NewInstance()
        addFragment(fragment)
    }
}
