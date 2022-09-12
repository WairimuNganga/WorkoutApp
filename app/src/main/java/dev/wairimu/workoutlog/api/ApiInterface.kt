package dev.wairimu.workoutlog.api

import dev.wairimu.workoutlog.models.LoginRequest
import dev.wairimu.workoutlog.models.LoginResponse
import dev.wairimu.workoutlog.models.RegisterRequest
import dev.wairimu.workoutlog.models.RegisterResponse
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiInterface {
    @POST("/register")
    fun registerUser(@Body registerRequest: RegisterRequest):Response<RegisterResponse>
    @POST("/login")
    suspend fun login(@Body loginRequest: LoginRequest):Response<LoginResponse>
}