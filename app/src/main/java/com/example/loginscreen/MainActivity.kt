package com.example.loginscreen

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import android.content.Intent

class MainActivity : AppCompatActivity() {

    enum class LoginSuccess constructor(val intValue: Int){
        login(1),
        password(2),
        success(0)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val txtLogin = findViewById<TextView>(R.id.idUsername)
        val txtPassword = findViewById<TextView>(R.id.idPassword)
        val btnLogin = findViewById<Button>(R.id.idButton)

        btnLogin.setOnClickListener {
            val intent = Intent(this, MainClassList::class.java)
            when(CheckLogin(txtLogin.text.toString(), txtPassword.text.toString())){
                LoginSuccess.login -> {
                    Toast.makeText(applicationContext, getString(R.string.errMessageLogin), Toast.LENGTH_LONG).show()
                    txtLogin.requestFocus()
                }
                LoginSuccess.password -> {
                    Toast.makeText(applicationContext, getString(R.string.errMessagePassword), Toast.LENGTH_LONG).show()
                    txtPassword.requestFocus()
                }

                else ->
                    startActivity(intent)
            }
        }
    }

    fun CheckLogin(txtLogin: String, txtPassword: String): LoginSuccess{
        val holdLogin = "Jesus"
        val holdPass = "password"

        if (holdLogin != txtLogin){
            return LoginSuccess.login
        }
        return if (holdPass != txtPassword){
            return LoginSuccess.password
        }else LoginSuccess.success
//        val intent = Intent(this, MainClassList::class.java)
//        startActivity(intent)
    }
}