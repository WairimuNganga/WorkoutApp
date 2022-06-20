package dev.wairimu.workoutlog

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.FragmentContainerView
import com.google.android.material.bottomnavigation.BottomNavigationView
import dev.wairimu.workoutlog.databinding.ActivityHomeBinding
import dev.wairimu.workoutlog.databinding.ActivityLoginBinding
import dev.wairimu.workoutlog.databinding.ActivitySignUpBinding

class HomeActivity : AppCompatActivity() {
    lateinit var binding:ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        castViews()
        setUpBottomNav()
    }

    fun castViews(){
        binding.fcvHome
        binding.bnvHome
    }
    fun setUpBottomNav(){
//        This will help switch to a different navigation
       binding.bnvHome.setOnItemSelectedListener { item->
            when(item.itemId) {
                R.id.plan -> {
//                    val transaction = supportFragmentManager.beginTransaction()
//                    transaction.replace(R.id.fcvHome, PlanFragment())
//                    transaction.commit()
                    supportFragmentManager.beginTransaction().replace(R.id.fcvHome, PlanFragment()).commit()
                    true
                }


                R.id.track -> {
                    supportFragmentManager.beginTransaction().replace(R.id.fcvHome, TrackFragment()).commit()
                    true
                }

                R.id.profile -> {
                  supportFragmentManager.beginTransaction().replace(R.id.fcvHome, ProfileFragment()).commit()
                    true
                }
                else->false
            }
        }
    }
}