package com.example.presentationlayer

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.google.android.material.textfield.TextInputEditText

class RegisterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        var btn = findViewById<Button>(R.id.btn_register)
        btn.setOnClickListener{
            register()
        }
    }

    fun register(){
        var name = findViewById<TextInputEditText>(R.id.name)
        var email = findViewById<TextInputEditText>(R.id.emailInput2)
        var password = findViewById<TextInputEditText>(R.id.passwordInput2)
        val intent = Intent(this@RegisterActivity,RegisterActivity2::class.java)
        intent.putExtra("name",name.text.toString())
        intent.putExtra("email",email.text.toString())
        intent.putExtra("password",password.text.toString())
        startActivity(intent)
        finish()
    }
}