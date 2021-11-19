package com.example.mytodoapp.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.mytodoapp.R
import com.example.mytodoapp.databinding.ActivityAuthBinding
import com.example.mytodoapp.databinding.ActivityMainBinding

class AuthActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)
    }
}