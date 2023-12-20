package com.capstone.cuacatani.ui.about

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.Observer
import androidx.lifecycle.viewModelScope
import com.capstone.cuacatani.R
import com.capstone.cuacatani.ViewModelFactory
import com.capstone.cuacatani.data.pref.UserModel
import com.capstone.cuacatani.data.pref.UserPreference
import com.capstone.cuacatani.databinding.ActivityAboutBinding
import com.capstone.cuacatani.databinding.ActivityMainBinding
import com.capstone.cuacatani.response.LoginResult
import com.capstone.cuacatani.ui.login.LoginActivity
import com.capstone.cuacatani.ui.login.LoginViewModel
import com.capstone.cuacatani.ui.main.MainActivity
import com.capstone.cuacatani.ui.plants.PlantsActivity
import com.capstone.cuacatani.ui.welcome.WelcomeActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import kotlinx.coroutines.launch

class AboutActivity : AppCompatActivity() {
    private val viewModel by viewModels<AboutViewModel> {
        ViewModelFactory.getInstance(this)
    }
    private lateinit var bottomNavigationView: BottomNavigationView
    private lateinit var binding: ActivityAboutBinding
    private lateinit var userPreference: UserPreference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAboutBinding.inflate(layoutInflater)
        setContentView(binding.root)
        userPreference = UserPreference(this)

        setupUserInfo()
        bindUIComponents()
        handleTabButtonPress()

        bottomNavigationView.selectedItemId = R.id.navigation_about

        binding.logoutButton.setOnClickListener {
            logout()
        }

        supportActionBar?.hide()
    }

    private fun setupUserInfo(){
        val loginResult: LoginResult? = userPreference.gainUser()
        if (loginResult != null) {
            binding.username.text = loginResult.name
        }

        if (loginResult != null) {
            binding.email.text = loginResult.email
        }
    }

    private fun bindUIComponents() {
        bottomNavigationView = findViewById(R.id.nav_view)
    }
    private fun handleTabButtonPress() {
        bottomNavigationView.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.navigation_home -> {
                    loadActivities(MainActivity())
                    return@setOnItemSelectedListener true
                }

                R.id.navigation_plant -> {
                    loadActivities(PlantsActivity())
                    return@setOnItemSelectedListener true
                }

                R.id.navigation_about -> {
                    return@setOnItemSelectedListener true
                }

            }
            return@setOnItemSelectedListener false
        }
    }

    private fun loadActivities(activity: AppCompatActivity) {
        val intent = Intent(applicationContext, activity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NO_ANIMATION
        startActivity(intent)
        finish()
    }

    private fun logout() {
        viewModel.saveSession(LoginResult("", "",), this)
        val intent = Intent(this@AboutActivity, WelcomeActivity::class.java)
        startActivity(intent)
        finish()
    }


}