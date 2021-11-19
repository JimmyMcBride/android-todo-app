package com.example.mytodoapp.viewmodel

import com.example.mytodoapp.models.LoginBody
import com.example.mytodoapp.models.RegisterBody
import com.example.mytodoapp.repo.AuthRepo

object AuthViewModel {
    suspend fun login(username: String, password: String) = AuthRepo.login(LoginBody(username, password))
    suspend fun register(username: String, email: String, password: String) = AuthRepo.register(RegisterBody(username, email, password))

}