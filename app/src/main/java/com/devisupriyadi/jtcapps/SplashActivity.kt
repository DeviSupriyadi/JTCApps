package com.devisupriyadi.jtcapps

/* Tanggal Pengerjaan : 27 Juni 2020
   Deskripsi          : Bikin Splash Screen
   Nama               : Devi Tiana Octaviani
   NIM                : 10117181
   Kelas              : IF-5
 */

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        val handle = Handler()
        handle.postDelayed({
            val intent = Intent(this,
                    MainActivity::class.java)
            startActivity(intent)
            finish()
        },3000)
    }
}
