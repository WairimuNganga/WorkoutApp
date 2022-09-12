package dev.wairimu.workoutlog.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import dev.wairimu.workoutlog.R
import dev.wairimu.workoutlog.databinding.ActivityHomeBinding

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
//    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
//        menuInflater.inflate(R.menu.menu_main,menu)
//        return true
//    }
//
//    override fun onOptionsItemSelected(item: MenuItem): Boolean {
//        if (item.itemId == R.id.miLogout) {
//            Log.i(Tag, "Logout")
//            //Log out the user
//
//            val logoutIntent= Intent(this,LoginActivity::class.java)
//            logoutIntent.flags=Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
//            startActivity(logoutIntent)
//
//        }
//        return super.onOptionsItemSelected(item)
//    }
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