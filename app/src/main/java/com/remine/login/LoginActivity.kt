package com.remine.login

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
//import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.remine.R
import com.remine.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {
    lateinit var binding: ActivityLoginBinding
    private lateinit var LoginFragment: LoginFragment
    override fun onCreate(savedInstanceState: Bundle?) {
        //installSplashScreen()
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setContentView(R.layout.activity_login)
        val newFragment = LoginFragment()
        val bundle = Bundle()
        newFragment.arguments = bundle

        // 프래그먼트 전환
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainerView, newFragment)
            .addToBackStack(null)
            .commit()
    }
}