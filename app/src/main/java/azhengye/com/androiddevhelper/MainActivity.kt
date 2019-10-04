package azhengye.com.androiddevhelper

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import azhengye.com.androiddevhelper.fragment.BaseFragment
import azhengye.com.androiddevhelper.fragment.DiscoverFragment
import azhengye.com.androiddevhelper.fragment.HomeFragment
import azhengye.com.androiddevhelper.fragment.SettingsFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    val fragments = ArrayList<BaseFragment>(3)

    private val onNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_home -> {
                view_pager.setCurrentItem(0, false)
            }
            R.id.navigation_dashboard -> {
                view_pager.setCurrentItem(1, false)
            }
            R.id.navigation_notifications -> {
                view_pager.setCurrentItem(2, false)
            }
        }
        true
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val navView: BottomNavigationView = findViewById(R.id.nav_view)

        navView.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener)

        initFragmen()
        initViewPager();
    }

    private fun initFragmen() {
        val homeFragment = HomeFragment()
        var bundle = Bundle()
        bundle.putString("home", "home")
        homeFragment.arguments = bundle

        val discoverFragment = DiscoverFragment()
        bundle = Bundle()
        bundle.putString("discover", "discover")
        discoverFragment.arguments = bundle

        val settingsFragment = SettingsFragment()
        bundle = Bundle()
        bundle.putString("settings", "settings")
        settingsFragment.arguments = bundle

        fragments.add(homeFragment)
        fragments.add(discoverFragment)
        fragments.add(settingsFragment)
    }

    private fun initViewPager() {
        view_pager.isUserInputEnabled = false
        view_pager.adapter = object : FragmentStateAdapter(this) {
            override fun createFragment(position: Int): BaseFragment {
                return fragments[position]
            }

            override fun getItemCount(): Int {
                return fragments.size
            }
        }
    }
}
