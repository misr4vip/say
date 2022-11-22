package net.say.say

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_index.*

class IndexActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_index)

    }


    fun ShowDictionary(view: View) {

        var intent = Intent(applicationContext,DictionaryActivity::class.java)
        startActivity(intent)
    }

    fun ShowFirstCategory(view: View) {

        var intent = Intent(applicationContext,FirstCategoryActivity::class.java)
        startActivity(intent)
    }


}