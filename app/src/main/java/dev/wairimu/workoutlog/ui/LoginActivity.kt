package dev.wairimu.workoutlog.ui

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import dev.wairimu.workoutlog.databinding.ActivityLoginBinding
import dev.wairimu.workoutlog.models.LoginRequest
import dev.wairimu.workoutlog.models.LoginResponse
import dev.wairimu.workoutlog.api.ApiClient
import dev.wairimu.workoutlog.api.ApiInterface
import dev.wairimu.workoutlog.viewmodel.UserViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivity : AppCompatActivity() {
    lateinit var binding: ActivityLoginBinding
    lateinit var sharedPrefs: SharedPreferences
    val userViewModel: UserViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        sharedPrefs = getSharedPreferences("WORKOUT_PREFS", MODE_PRIVATE)


        binding.btnLogin.setOnClickListener {
            validateLogin()
        }
        binding.tvSignUp.setOnClickListener {
            val intent = Intent(this, SignUpActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
        override fun onResume(){
            super.onResume()
            userViewModel.loginResponseLiveData.observe(this, Observer{loginResponse->
                saveLoginDetails(loginResponse!!)
                Toast.makeText(baseContext,loginResponse?.message,Toast.LENGTH_LONG).show()
                startActivity(Intent(baseContext,HomeActivity::class.java))
                finish()
        })
            userViewModel.loginErrorLiveData.observe(this, Observer{ error->
                Toast.makeText(baseContext,error,Toast.LENGTH_LONG).show()
            })
    }
    fun validateLogin() {
        var email = binding.etEmail.text.toString()
        var password = binding.etPassword.text.toString()
        var error = false

        if (email.isBlank()) {
           binding.tilEmail.error = "Email is required"
        }
        if (password.isBlank()) {
            binding.tilPassword.error = "Password is required"
        }
        if(!error){
            var loginRequest = LoginRequest(email,password)
            binding.pbProgressBar.visibility = View.VISIBLE
        }
    }

    fun saveLoginDetails(loginResponse: LoginResponse){
        val editor = sharedPrefs.edit()
        editor.putString("ACCESS_TOKEN",loginResponse.accessToken)
        editor.putString("USER_ID",loginResponse.userId)
        editor.putString("PROFILE_ID",loginResponse.profileId)
        editor.apply()
    }
}