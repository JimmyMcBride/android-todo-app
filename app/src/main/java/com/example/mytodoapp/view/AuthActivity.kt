package com.example.mytodoapp.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.mytodoapp.R
import com.example.mytodoapp.databinding.ActivityAuthBinding
import com.example.mytodoapp.databinding.ActivityMainBinding

class AuthActivity : AppCompatActivity() {
    private val binding by lazy { ActivityAuthBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)
    }

    override fun onStart() {
        super.onStart()
        val navController = findNavController(binding.authNavHostFragment.id)
        setupActionBarWithNavController(navController, AppBarConfiguration(setOf(R.id.loginFragment, R.id.registerFragment)))
    }
}