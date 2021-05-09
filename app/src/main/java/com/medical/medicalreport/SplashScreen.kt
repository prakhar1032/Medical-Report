package com.medical.medicalreport

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity


class SplashScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        Handler().postDelayed({
            if (!alreadyLoggedIn()) {
                val intent = Intent(this@SplashScreen, LoginActivity::class.java)
                startActivity(intent)
            } else {
                val intent = Intent(this@SplashScreen, MainActivity::class.java)
                startActivity(intent)
            }
            finish()
        }, 2000) // 3000 is the delayed time in milliseconds.
    }

    private fun alreadyLoggedIn(): Boolean {
        return false
    }
}

