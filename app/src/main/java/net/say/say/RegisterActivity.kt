package net.say.say

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.util.Patterns
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_register.*

class RegisterActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

    }

    override fun onStart() {
        super.onStart()


        val mAuth = FirebaseAuth.getInstance()

        btn_register.setOnClickListener {


            val repwd = et_repwd.text.toString()
            val FirstName = et_FirstName.text.toString()
            val LastName = et_lastName.text.toString()
            val pwd = et_pwd.text.toString()
            val email = et_email.text.toString()




            if (TextUtils.isEmpty(FirstName)) {
                et_FirstName.error = "Please enter FirstName"
                et_FirstName.requestFocus()
                return@setOnClickListener

            }
            if (TextUtils.isEmpty(LastName)) {
                et_lastName.error = "Please enter your LastName"
                et_lastName.requestFocus()
                return@setOnClickListener
            }


            if (TextUtils.isEmpty(email)) {
                et_email.error = "Please enter your email"
                et_email.requestFocus()
                return@setOnClickListener
            }

            if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                et_email.error = "Enter a valid email"
                et_email.requestFocus()
                return@setOnClickListener
            }

            if (TextUtils.isEmpty(pwd)) {
                et_pwd.error = "Enter a password"
                et_pwd.requestFocus()
                return@setOnClickListener
            }

            if (pwd != repwd) {
                et_pwd.error = "PassWord Dismatch"
                return@setOnClickListener
            }
            if (pwd.length < 6) {
                et_pwd.error = "PassWord  is weak"
                return@setOnClickListener
            }


            mAuth.createUserWithEmailAndPassword(et_email.text.toString(),et_pwd.text.toString())
                .addOnCompleteListener{

                    if ( it.isSuccessful)
                    {
                        Log.d("CreateUser","User Created Successfully.")
                        val user = mAuth.currentUser
                        User.Id = user!!.uid
                        User.FirstName = et_FirstName.text.toString()
                        User.LastName = et_lastName.text.toString()
                        User.Email = et_email.text.toString()
                        User.Password = et_pwd.text.toString()
                        User.IsUserAuth = true
                        writeNewUser(User.Id,User.FirstName,User.LastName,User.Email,User.Password,true)

                        val intent  = Intent(applicationContext,IndexActivity::class.java)
                        startActivity(intent)

                    }
                    else
                    {
                        Log.d("CreateUser","Error! " + it.exception!!.message)
                        Toast.makeText(applicationContext,"حدث خطأ في تسجيل الحساب نآمل المحاولة لاحقا",Toast.LENGTH_LONG).show()
                        et_pwd.text.clear()
                        et_repwd.text.clear()
                    }
                }

        }
    }
    private fun writeNewUser(userId: String, firstname: String,lastname:String, email: String,pwd : String,isAuth : Boolean) {
        val mDatabase = FirebaseDatabase.getInstance().getReference()
        val user = User(userId,firstname,lastname, email,pwd,isAuth)
        mDatabase.child("users").child(userId).setValue(user)
            .addOnSuccessListener {
            Toast.makeText(applicationContext,"user Added To Database Successfully",Toast.LENGTH_LONG).show()
        }
            .addOnFailureListener {
                Toast.makeText(applicationContext,"failed to Add user To Database ",Toast.LENGTH_LONG).show()
            }
    }
}