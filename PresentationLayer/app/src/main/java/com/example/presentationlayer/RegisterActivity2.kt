package com.example.presentationlayer


import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.presentationlayer.api.StartApi
import com.google.android.material.textfield.TextInputEditText
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class RegisterActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register2)
        var btn = findViewById<Button>(R.id.btn_register2)

        var autoCompleteTextView = findViewById<AutoCompleteTextView>(R.id.activation_rate)
        var autoCompleteTextView2 = findViewById<AutoCompleteTextView>(R.id.gender)

        val subject = arrayOf("كسول", "نشط قليلا", "نشط بشكل معتدل","نشط جدا","نشط فوق المعتاد")
        val adapter= ArrayAdapter<Any?>(this, R.layout.dropdown, subject)
        autoCompleteTextView.setAdapter(adapter)

        val genderType = arrayOf("ذكر", "انثى")
        val adapter2= ArrayAdapter<Any?>(this, R.layout.dropdown, genderType)
        autoCompleteTextView2.setAdapter(adapter2)

        var gender:String = ""
        var activationRate:Double = 1.0

        autoCompleteTextView2.setOnClickListener {
            Log.d("#",autoCompleteTextView2.text.toString())
            gender = if(autoCompleteTextView2.text.toString() == "ذكر"){
                "man"
            } else{
                "woman"
            }
        }

        autoCompleteTextView.setOnClickListener {
            activationRate = if(autoCompleteTextView.text.toString() == "كسول"){
                1.2
            } else if(autoCompleteTextView.text.toString() == "نشط قليلا") {
                1.4
            } else if(autoCompleteTextView.text.toString() == "نشط بشكل معتدلا") {
                1.6
            } else if(autoCompleteTextView.text.toString() == "نشط جدا") {
                1.75
            } else{
                2.0
            }
        }

        btn.setOnClickListener{
            register(gender,activationRate)
        }
    }

    fun register(gender:String , activationRate:Double){
        val instance: StartApi = StartApi()
        val bundle = intent.extras

        var name = bundle?.getString("name")
        var email = bundle?.getString("email")
        var password = bundle?.getString("password")
        var age  = findViewById<TextInputEditText>(R.id.age)
        var height  = findViewById<TextInputEditText>(R.id.height)
        var weight  = findViewById<TextInputEditText>(R.id.weight)
        Log.d("#", name.toString())
        Log.d("#", email.toString()+" "+password.toString())
        Log.d("#", gender + " " +height.text.toString() + " "+activationRate)
        Log.d("#", age.text.toString() + " " +weight.text.toString())
        instance.getInstance().register(name.toString(),email.toString(),password.toString(),gender,height.text.toString().toInt(),weight.text.toString().toDouble(),age.text.toString().toInt(),activationRate).
        enqueue(object: Callback<ResponseBody> {
                    override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                        if(response.isSuccessful){
                            Toast.makeText(this@RegisterActivity2,"Account has been created!!",Toast.LENGTH_LONG).show()
                            finish()
                        } else{
                            Toast.makeText(this@RegisterActivity2,"Your email is duplicated",Toast.LENGTH_LONG).show()
                        }
                    }
                    override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                        Log.d("#", t.message.toString())
                        Toast.makeText(this@RegisterActivity2,"Bad connection",Toast.LENGTH_SHORT).show()
                    }
                }
            )
    }
}
