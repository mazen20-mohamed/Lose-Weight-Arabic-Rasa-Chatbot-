package com.example.presentationlayer

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.example.presentationlayer.api.StartApi
import com.google.android.material.textfield.TextInputEditText
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivity : AppCompatActivity() {
    var id:Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        var btn = findViewById<Button>(R.id.btn_login)
        btn.setOnClickListener{
            login()
        }

        var text = findViewById<TextView>(R.id.register_text)
        text.setOnClickListener{
            val intent = Intent(this@LoginActivity,RegisterActivity::class.java)
            startActivity(intent)
        }
    }

    fun login(){
        val instance: StartApi = StartApi()
        var email = findViewById<TextInputEditText>(R.id.emailInput)
        var password = findViewById<TextInputEditText>(R.id.passwordInput)
        instance.getInstance().login(email.text.toString(),password.text.toString())?.enqueue(
            object:Callback<Long?>{
                override fun onResponse(call: Call<Long?>, response: Response<Long?>) {
                    if(response.isSuccessful){
                        val nullable:Int? = response.body()?.toInt()
                        if(nullable!=null){
                            id = nullable
                        }

                        val intent = Intent(this@LoginActivity,MainScreenActivity::class.java)
                        startActivity(intent)

                    } else{
                        Toast.makeText(this@LoginActivity,"Check your email or password",Toast.LENGTH_LONG).show()
                    }
                }

                override fun onFailure(call: Call<Long?>, t: Throwable) {
                    Toast.makeText(this@LoginActivity,"Bad connection",Toast.LENGTH_SHORT).show()
                }
            }
        )
    }
}
