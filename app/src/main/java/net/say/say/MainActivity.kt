package net.say.say

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }

    override fun onStart() {
        super.onStart()

        var handel = Handler()
        handel.postDelayed(object : Runnable{
            override fun run() {
                val intent= Intent(applicationContext,LoginRegisterActivity::class.java)
                startActivity(intent)
            }

        },2000)
    }
}