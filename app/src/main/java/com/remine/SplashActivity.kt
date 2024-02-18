package com.remine

// SplashActivity.java
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.remine.login.LoginActivity


class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        // 지정된 시간 후에 다음 액티비티로 이동
        Handler().postDelayed({
            // 이동할 다음 액티비티를 설정
            val intent = Intent(
                this@SplashActivity,
                LoginActivity::class.java
            )
            startActivity(intent)
            finish() // 현재 액티비티 종료
        }, SPLASH_TIME_OUT.toLong())
    }

    companion object {
        // Splash 화면에 보여줄 시간(밀리초 단위)
        private const val SPLASH_TIME_OUT = 1000 // 예: 3초
    }
}

