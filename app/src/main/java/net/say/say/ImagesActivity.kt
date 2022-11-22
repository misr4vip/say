package net.say.say

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.drawable.BitmapDrawable
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.bumptech.glide.Glide
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_images.*
import java.util.ArrayList

class ImagesActivity : AppCompatActivity() {
    var x = 0
    var value : String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_images)
        var intent = intent
        value = intent.getStringExtra("category")
        LoadImage(0)

            }

    override fun onStart() {
        super.onStart()

        btn_next.setOnClickListener {

            LoadImage(++x)
        }
        btn_prev.setOnClickListener {
            LoadImage(--x)
        }
    }
    fun LoadImage(index : Int)
    {
        val storageReference = Firebase.storage.reference.child(value!!)
        storageReference.listAll()
            .addOnSuccessListener {
                Log.d("ListenToStorageRefrance:","successed")

                if (index < it.items.size && index >= 0) {
                    it.items.elementAt(index).downloadUrl.addOnCompleteListener {
                        Picasso.get().load(it.result).into(iv_Image)
                    }
                }else
                {
                    Toast.makeText(applicationContext,"نهاية الصور",Toast.LENGTH_LONG).show()
                }
            }
            .addOnFailureListener {
                Toast.makeText(applicationContext,it.message!!,Toast.LENGTH_LONG)
                Log.d("GetImageresult",it.message!!)
            }
    }
}