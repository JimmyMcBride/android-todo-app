package com.example.mytodoapp.repo.remote

import com.example.mytodoapp.models.AuthResponse
import com.example.mytodoapp.models.LoginBody
import com.example.mytodoapp.models.RegisterBody
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthService {
    @POST("auth/register")
    suspend fun register(@Body() body: RegisterBody): AuthResponse

    @POST("auth/login")
    suspend fun login(@Body() body: LoginBody): AuthResponse
}