package com.remine

import android.content.res.ColorStateList
import android.os.Bundle
import android.util.DisplayMetrics
import android.util.Log
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.size
import androidx.fragment.app.FragmentManager
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.remine.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()
        val navView: BottomNavigationView = binding.navView

        val navController = findNavController(R.id.nav_host_fragment_activity_main)


        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        /*
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_declaration, R.id.navigation_home, R.id.navigation_community, R.id.navigation_mypage
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration) */
        navView.setupWithNavController(navController)
        val menu = navView.menu
        for (i in 0 until menu.size()) {
            menu.getItem(i).title = ""
        }

        navView.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.navigation_declaration -> {
                    navController.navigate(R.id.navigation_declaration)
                    return@setOnItemSelectedListener true
                }

                R.id.navigation_home -> {
                    navController.navigate(R.id.navigation_home)
                    return@setOnItemSelectedListener true
                }

                R.id.navigation_community -> {
                    navController.navigate(R.id.navigation_community)
                    return@setOnItemSelectedListener true
                }

                R.id.navigation_mypage -> {
                    navController.navigate(R.id.navigation_mypage)
                    return@setOnItemSelectedListener true
                }
            }
            false
        }


//        // 화면의 가로 크기를 가져오는 코드
//        // 화면의 가로 크기를 가져오는 코드
//        val displayMetrics = DisplayMetrics()
//        windowManager.defaultDisplay.getMetrics(displayMetrics)
//        val screenWidth = displayMetrics.widthPixels
//
//// 화면의 너비를 4등분하여 아이콘의 크기를 설정
//
//// 화면의 너비를 4등분하여 아이콘의 크기를 설정
//       val iconSize = screenWidth / 4 // 예: 화면 너비를 4등분한 값
//        navView.itemIconSize = iconSize

    }
}