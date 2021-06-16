package com.medipol.kotlinfinalprojesi.ui.splash

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import android.os.Build
import android.os.CountDownTimer
import androidx.annotation.RequiresApi
import com.medipol.kotlinfinalprojesi.R
import com.medipol.kotlinfinalprojesi.ui.login.LoginActivity
import com.medipol.kotlinfinalprojesi.utils.AlertDialogUtil
import com.medipol.kotlinfinalprojesi.utils.NetworkUtil

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        init()
    }
    private fun init() {
        val timer = object: CountDownTimer(3000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
            }

            @RequiresApi(Build.VERSION_CODES.M)
            override fun onFinish() {
                if(NetworkUtil.isOnline(applicationContext)){
                    startActivity(Intent(this@SplashActivity, LoginActivity::class.java))
                }
                else{
                    AlertDialogUtil.alertDialogShow(this@SplashActivity, resources.getString(R.string.splash_internet_kontrol),
                            resources.getString(R.string.splash_internet_kontrol_gerekli),
                            resources.getString(R.string.splash_internet_kontrol_ayarlara_git),
                            resources.getString(R.string.splash_internet_kontrol_kapat),
                            resources.getString(R.string.splash_activity))
                }
            }
        }
        timer.start()
    }
}