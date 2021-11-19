package com.example.mytodoapp.view

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class AuthenticationRouter : AppCompatActivity() {
    private val navToAuth = true
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val authIntent = Intent(this, AuthActivity::class.java)
        val homeIntent = Intent(this, MainActivity::class.java)

        if (navToAuth) {
            startActivity(authIntent)
        } else {
            startActivity(homeIntent)
        }
    }
}