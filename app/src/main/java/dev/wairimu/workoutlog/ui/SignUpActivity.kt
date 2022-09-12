package dev.wairimu.workoutlog.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import dev.wairimu.workoutlog.databinding.ActivitySignUpBinding
import dev.wairimu.workoutlog.models.RegisterRequest
import dev.wairimu.workoutlog.models.RegisterResponse
import dev.wairimu.workoutlog.api.ApiClient
import dev.wairimu.workoutlog.api.ApiInterface
import dev.wairimu.workoutlog.viewmodel.UserViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SignUpActivity : AppCompatActivity() {
    lateinit var binding: ActivitySignUpBinding
    val userViewModel: UserViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.tvLogin.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }

        binding.btnSignUp.setOnClickListener{
            validateSignUp()
        }
    }
//    fun makeRegistrationRequest(registerRequest: RegisterRequest){
//        var apiClient = ApiClient.buildApiClient(ApiInterface::class.java)
//        var request = apiClient.registerUser(registerRequest)
//        request.enqueue(object : Callback<RegisterResponse> {
//            override fun onResponse(
//                call: Call<RegisterResponse>,
//                response: Response<RegisterResponse>
//            ) {
//                if(response.isSuccessful){
//                    var message = response.body()?.message
//                    Toast.makeText(baseContext,message,Toast.LENGTH_LONG).show()
//
//                }
//                else{
//                    var error = response.errorBody()?.string()
//                    Toast.makeText(baseContext,error,Toast.LENGTH_LONG).show()
//                }
//            }
//
//            override fun onFailure(call: Call<RegisterResponse>, t: Throwable) {
//                Toast.makeText(baseContext,t.message,Toast.LENGTH_LONG).show()
//            }

    fun validateSignUp(){
        val firstName = binding.etFirstName.text.toString()
        val lastName =binding.etLastName.text.toString()
        val email = binding.etEmailAddress.text.toString()
        val phoneNumber = binding.etPhoneNumber.text.toString()
        val signUpPassword =  binding.etSignUpPassword.text.toString()
        val signUpConfirmPassword = binding.etSignUpConfirmPassword2.text.toString()
        var error = false

        if(firstName.isBlank()){
            binding.tilFirstName.error = "Firstname required"
            error = true
        }
        if(lastName.isBlank()){
            binding.tilLastName.error = "Lastname required"
            error = true
        }
        if(signUpPassword.isBlank()){
            binding.tilSignUpPassword.error = "Password required"
            error = true
        }
        if(signUpConfirmPassword.isBlank()){
            binding.tilConfirmPassword.error = "Confirm password"
            error = true
        }
        if(phoneNumber.isBlank()){
            binding.tilPhoneNumber.error = "PhoneNumber required"
            error = true
        }
        if (email.isBlank()){
            binding.tilEmailAddress.error = "Email address required"
            error = true
        }
        if(signUpPassword != signUpConfirmPassword){
            binding.tilConfirmPassword.error = "Password does not match"
            error = true
        }
        if(signUpPassword.length<8){
            binding.tilSignUpPassword.error = "Password too short"
            error = true
        }
        if(signUpPassword.length>16){
            binding.tilSignUpPassword.error = "Password too long"
            error = true
        }
        if(!error){
            val registerRequest = RegisterRequest(firstName,lastName,email,phoneNumber,signUpPassword)
//            makeRegistrationRequest(registerRequest)
            userViewModel.registerUser(registerRequest)

        }
    }
    override fun onResume(){
        super.onResume()
        userViewModel.loginResponseLiveData.observe(this, Observer{ registerResponse->
            Toast.makeText(baseContext,registerResponse.message,Toast.LENGTH_LONG).show()
            startActivity(Intent(this@SignUpActivity,LoginActivity::class.java))
//            finish()
        })

        userViewModel.registerErrorLiveData.observe(this, Observer{ errorMessage->
            Toast.makeText(baseContext,errorMessage,Toast.LENGTH_LONG).show()
        })
    }


//    fun makeRegistrationRequest(registerRequest: RegisterRequest){
//        var apiClient = ApiClient.buildApiClient(ApiInterface::class.java)
//        var request = apiClient.registerUser(registerRequest)
//
//        request.enqueue(object : Callback<RegisterResponse> {
//            override fun onResponse(
//                call: Call<RegisterResponse>,
//                response: Response<RegisterResponse>
//            ) {
//                if(response.isSuccessful){
//                var message = response.body()?.message
//                    Toast.makeText(baseContext,message,Toast.LENGTH_LONG).show()
//
//                }
//                else{
//                    var error = response.errorBody()?.string()
//                    Toast.makeText(baseContext,error,Toast.LENGTH_LONG).show()
//                }
//            }
//
//            override fun onFailure(call: Call<RegisterResponse>, t: Throwable) {
//                Toast.makeText(baseContext,t.message,Toast.LENGTH_LONG).show()
//            }
//
//        })
//    }

}