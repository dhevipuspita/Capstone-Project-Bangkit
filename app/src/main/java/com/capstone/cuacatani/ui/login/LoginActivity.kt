package com.capstone.cuacatani.ui.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.capstone.cuacatani.R
import com.capstone.cuacatani.databinding.ActivityLoginBinding
import com.capstone.cuacatani.ui.signup.SignupActivity

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        signIn()

        binding.tvCreate.setOnClickListener {
            val signupIntent = Intent(this, SignupActivity::class.java)
            startActivity(signupIntent)
        }

        supportActionBar?.hide()
    }

    private fun signIn() {
        binding.btnLogin.setOnClickListener {
            val email = binding.emailEditText.text.toString()
            val password = binding.passEditText.text.toString()
        }
    }
    }
}