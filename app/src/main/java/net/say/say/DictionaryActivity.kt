package net.say.say

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_dictionary.*

class DictionaryActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dictionary)
    }

    override fun onStart() {
        super.onStart()

        iv_alphaptic.setOnClickListener {
           naviImages("alphaptic")
        }
        iv_animals.setOnClickListener {
            naviImages("animals")
        }
        iv_count.setOnClickListener {
            naviImages("countandmoney")
        }
        iv_family.setOnClickListener {
            naviImages("family")
        }
        iv_food.setOnClickListener {
            naviImages("food")
        }
        iv_health.setOnClickListener {
            naviImages("health")
        }
        iv_house.setOnClickListener {
            naviImages("houseandtools")
        }
        iv_learn.setOnClickListener {
            naviImages("education")
        }
        iv_oppisite.setOnClickListener {
            naviImages("oppisites")
        }
        iv_season.setOnClickListener {
            naviImages("season")
        }
    }




    fun naviImages(value : String)
    {
        var intent = Intent(applicationContext,ImagesActivity::class.java)
        intent.putExtra("category",value)
        startActivity(intent)
    }
}