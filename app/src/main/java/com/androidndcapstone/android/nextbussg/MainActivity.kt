package com.androidndcapstone.android.nextbussg

import android.os.Bundle
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.tabs.TabLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
//import com.example.android.nextbussg.ui.main.SectionsPagerAdapter
import com.androidndcapstone.android.nextbussg.databinding.ActivityMainBinding
import com.androidndcapstone.android.nextbussg.ui.main.BusNumberFragment
import com.androidndcapstone.android.nextbussg.ui.main.BusStopFragment
import com.androidndcapstone.android.nextbussg.ui.main.NearbyFragment
import com.google.android.material.tabs.TabLayoutMediator


private val TAB_TITLES = arrayOf(
    R.string.tab_text_1,
    R.string.tab_text_2,
    R.string.tab_text_3
)

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        val sectionsPagerAdapter = SectionsPagerAdapter(this, supportFragmentManager)
        val viewPager: ViewPager2 = binding.viewPager

        viewPager.adapter = object : FragmentStateAdapter(this) {
            override fun createFragment(position: Int): Fragment {
//                return PlaceholderFragment.newInstance(position + 1)
                return when (position) {
                    0 -> BusNumberFragment()
                    1 -> BusStopFragment()
                    else -> NearbyFragment()
                }

            }

            override fun getItemCount(): Int {
                return 3
            }
        }
        val tabs: TabLayout = binding.tabs

        TabLayoutMediator(tabs, viewPager) { tab, position ->
            tab.text = getString(TAB_TITLES[position])
        }.attach()

//        tabs.setupWithViewPager(viewPager)
        val fab: FloatingActionButton = binding.fab

        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }
    }

    override fun onBackPressed() {
        val viewPager = binding.viewPager
        if (viewPager.currentItem == 0) {
            super.onBackPressed()
        } else {
            viewPager.currentItem = viewPager.currentItem - 1
        }
    }

}