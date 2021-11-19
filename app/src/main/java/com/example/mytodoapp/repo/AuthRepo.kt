package com.example.mytodoapp.repo;

import com.example.mytodoapp.models.AuthResponse
import com.example.mytodoapp.models.LoginBody
import com.example.mytodoapp.models.RegisterBody
import com.example.mytodoapp.repo.remote.RetrofitInstance;

object AuthRepo {
    suspend fun login(loginInfo: LoginBody): AuthResponse {
        return RetrofitInstance.authService.login(loginInfo)
    }
    suspend fun register(registerInfo: RegisterBody): AuthResponse {
        return RetrofitInstance.authService.register(registerInfo)
    }
}
