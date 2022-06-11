package dev.wairimu.workoutlog

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.google.android.material.textfield.TextInputLayout
import java.net.Inet4Address

class SignUpActivity : AppCompatActivity() {
    lateinit var tilFirstName: TextInputLayout
    lateinit var tilLastName: TextInputLayout
    lateinit var etFirstName: EditText
    lateinit var etLastName: EditText
    lateinit var tilEmailAddress: TextInputLayout
    lateinit var tilSignUpPassword:TextInputLayout
    lateinit var etEmailAddress: EditText
    lateinit var etSignUpPassword :EditText
    lateinit var btnSignUp:Button
    lateinit var tilConfirmPassword:TextInputLayout
    lateinit var etSignUpConfirmPassword:EditText
    lateinit var tvLogin:TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)


        tilFirstName = findViewById(R.id.tilFirstName)
        tilSignUpPassword = findViewById(R.id.tilSignUpPassword)
        tilLastName = findViewById(R.id.tilLastName)
        tilConfirmPassword = findViewById(R.id.tilConfirmPassword)
        tilEmailAddress = findViewById(R.id.tilEmailAddress)
        etFirstName = findViewById(R.id.etFirstName)
        etLastName = findViewById(R.id.etLastName)
        etEmailAddress = findViewById(R.id.etEmailAddress)
        etSignUpPassword = findViewById(R.id.etSignUpPassword)
        btnSignUp = findViewById(R.id.btnSignUp)
        etSignUpConfirmPassword = findViewById(R.id.etSignUpConfirmPassword)
        tvLogin = findViewById(R.id.tvLogin)

        tvLogin.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }

        btnSignUp.setOnClickListener{
            validateSignUp()
        }
    }
    fun validateSignUp(){
        val firstName = etFirstName.text.toString()
        val lastName = etLastName.text.toString()
        val email = etEmailAddress.text.toString()
        val signUpPassword =  etSignUpPassword.text.toString()
        val signUpConfirmPassword = etSignUpConfirmPassword.text.toString()
        var error = false

        if(firstName.isBlank()){
            tilFirstName.error = "Firstname required"
            error = true
        }
        if(lastName.isBlank()){
            tilLastName.error = "Lastname required"
            error = true
        }
        if(signUpPassword.isBlank()){
            tilSignUpPassword.error = "Password required"
            error = true
        }
        if(signUpConfirmPassword.isBlank()){
            tilConfirmPassword.error = "Confirm password"
            error = true
        }
        if (email.isBlank()){
            tilEmailAddress.error = "Email address required"
            error = true
        }
        if(signUpPassword != signUpConfirmPassword){
             tilConfirmPassword.error = "Password does not match"
            error = true
        }
        if(signUpPassword.length<8){
            tilSignUpPassword.error = "Password too short"
            error = true
        }
        if(signUpPassword.length>16){
            tilSignUpPassword.error = "Password too long"
            error = true
        }
        if(!error){
           startActivity(Intent(this,HomeActivity::class.java))
            finish()
        }

    }

}