package com.example.triple_aos

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.triple_aos.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.bvMainMenu.itemIconTintList = null
//        displayFragment()
    }

//    private fun displayFragment() {
//        supportFragmentManager.beginTransaction()
//            .add(R.id.fc_main_container, HomeFragment())
//            .commit()
//
//        binding.bvMainMenu.setOnItemSelectedListener {
//            // TODO: 이거 자기꺼에 맞는걸로 바꿔 주세용~
//            changeFragment(
//                when (it.itemId) {
//                    R.id.menu_home -> HomeFragment()
//                    R.id.menu_wish -> HomeFragment()
//                    R.id.menu_create_travle -> HomeFragment()
//                    R.id.menu_mypage -> HomeFragment()
//                    else -> HomeFragment()
//                }
//            )
//            true
//        }
//        binding.bvMainMenu.selectedItemId = R.id.menu_home
//    }

    private fun changeFragment(fragment: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fc_main_container, fragment)
            .commit()
    }


}
