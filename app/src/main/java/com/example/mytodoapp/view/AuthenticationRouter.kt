package com.example.mytodoapp.view

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.lifecycleScope
import com.example.mytodoapp.repo.local.datastore.DatastorePreferences
import kotlinx.coroutines.launch

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "jwt_store")

class AuthenticationRouter : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val homeIntent = Intent(this, MainActivity::class.java)
        val authIntent = Intent(this, AuthActivity::class.java)

        val datastore = DatastorePreferences(applicationContext)

        lifecycleScope.launch {
            val jwt = datastore.getJWT()
            homeIntent.putExtra("token", jwt)

            if (jwt.isNullOrBlank()) {
                startActivity(authIntent)
            } else {
                startActivity(homeIntent)
            }

        }

    }
}