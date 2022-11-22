package net.say.say


import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.util.Patterns
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_login.*


class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
    }

    override fun onStart() {
        super.onStart()
        val mAuth = FirebaseAuth.getInstance()

        btn_login.setOnClickListener {
            if (TextUtils.isEmpty(et_email.text.toString())) {
                et_email.error = "email can not be empty"
                et_email.requestFocus()
                return@setOnClickListener
            }
            if (!Patterns.EMAIL_ADDRESS.matcher(et_email.text.toString()).matches()) {
                et_email.error = "Enter a valid email"
                et_email.requestFocus()
                return@setOnClickListener
            }
            if (TextUtils.isEmpty(et_pwd.text.toString())) {
                et_pwd.error = "Enter a password"
                et_pwd.requestFocus()
                return@setOnClickListener
            }
            mAuth.signInWithEmailAndPassword(et_email.text.toString(), et_pwd.text.toString())
                .addOnCompleteListener(
                    this
                ) { task ->
                    if (task.isSuccessful) {
                        // Sign in success, update UI with the signed-in user's information
                        Log.d("LoginActivity", "signInWithEmail:success")
                        val user = mAuth.currentUser
                        val intent = Intent(this,IndexActivity::class.java)
                        startActivity(intent)
                    } else {
                        // If sign in fails, display a message to the user.
                        Log.w("LoginActivity", "signInWithEmail:failure", task.exception)
                        Toast.makeText(applicationContext, "Authentication failed.", Toast.LENGTH_SHORT).show()

                    }

                    // ...
                }
        }
    }
}