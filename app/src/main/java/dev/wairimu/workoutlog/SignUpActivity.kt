package dev.wairimu.workoutlog

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.google.android.material.textfield.TextInputLayout
import dev.wairimu.workoutlog.databinding.ActivityLoginBinding
import dev.wairimu.workoutlog.databinding.ActivitySignUpBinding
import java.net.Inet4Address

class SignUpActivity : AppCompatActivity() {
    lateinit var binding: ActivitySignUpBinding


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
    fun validateSignUp(){
        val firstName = binding.etFirstName.text.toString()
        val lastName =binding.etLastName.text.toString()
        val email = binding.etEmailAddress.text.toString()
        val signUpPassword =  binding.etSignUpPassword.text.toString()
        val signUpConfirmPassword = binding.etSignUpConfirmPassword.text.toString()
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
           startActivity(Intent(this,HomeActivity::class.java))
            finish()
        }

    }

}