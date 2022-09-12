package dev.wairimu.workoutlog.repository

import dev.wairimu.workoutlog.api.ApiClient
import dev.wairimu.workoutlog.api.ApiInterface
import dev.wairimu.workoutlog.models.LoginRequest
import dev.wairimu.workoutlog.models.RegisterRequest
import dev.wairimu.workoutlog.models.RegisterResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response

class UserRepository {
    val apiClient = ApiClient.buildApiClient(ApiInterface::class.java)

    suspend fun loginUser(loginRequest: LoginRequest) = withContext(Dispatchers.IO){
        val response  =  apiClient.login(loginRequest)
        return@withContext response
    }
    suspend fun registerUser(registerRequest: RegisterRequest) = withContext(Dispatchers.IO){
        val response  = apiClient.registerUser(registerRequest)
        return@withContext response
    }
}