package com.capstone.cuacatani.ui.signup

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import com.capstone.cuacatani.R
import com.capstone.cuacatani.ViewModelFactory
import com.capstone.cuacatani.databinding.ActivitySignupBinding
import com.capstone.cuacatani.ui.login.LoginActivity

class SignupActivity : AppCompatActivity() {

    private val viewModel by viewModels<SignupViewModel> {
        ViewModelFactory.getInstance(this)
    }
    private lateinit var binding: ActivitySignupBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding =ActivitySignupBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.tvLogin.setOnClickListener {
            val signupIntent = Intent(this, LoginActivity::class.java)
            startActivity(signupIntent)
        }

        onAction()
        playAnimation()
    }

    private fun onAction() {
        binding.btnSignup.setOnClickListener {
            val name = binding.nameEditText.text.toString()
            val email = binding.emailEditText.text.toString()
            val password = binding.passEditText.text.toString()

            AlertDialog.Builder(this).apply {
                setTitle("Yeah!")
                setMessage("Account with this $email is ready. Lets explore it.")
                setPositiveButton("Next") { _, _ ->
                    finish()
                }
                create()
                show()
            }
        }
    }

    private fun playAnimation() {
        ObjectAnimator.ofFloat(binding.imgSignup, View.TRANSLATION_X, -30f, 30f).apply {
            duration = 6000
            repeatCount = ObjectAnimator.INFINITE
            repeatMode = ObjectAnimator.REVERSE
        }.start()

        val nameTextView =
            ObjectAnimator.ofFloat(binding.tvName, View.ALPHA, 1f).setDuration(100)
        val nameEditTextLayout =
            ObjectAnimator.ofFloat(binding.nameEditTextLayout, View.ALPHA, 1f).setDuration(100)
        val emailTextView =
            ObjectAnimator.ofFloat(binding.tvEmail, View.ALPHA, 1f).setDuration(100)
        val emailEditTextLayout =
            ObjectAnimator.ofFloat(binding.emailEditTextLayout, View.ALPHA, 1f).setDuration(100)
        val passwordTextView =
            ObjectAnimator.ofFloat(binding.tvPassword, View.ALPHA, 1f).setDuration(100)
        val passwordEditTextLayout =
            ObjectAnimator.ofFloat(binding.passEditTextLayout, View.ALPHA, 1f).setDuration(100)
        val signup = ObjectAnimator.ofFloat(binding.btnSignup, View.ALPHA, 1f).setDuration(100)
        val textAlready = ObjectAnimator.ofFloat(binding.already, View.ALPHA, 1f).setDuration(100)
        val textLogin = ObjectAnimator.ofFloat(binding.tvLogin, View.ALPHA, 1F).setDuration(100)

        val together = AnimatorSet().apply {
            playTogether(textAlready, textLogin)
        }

        AnimatorSet().apply {
            playSequentially(
                nameTextView,
                nameEditTextLayout,
                emailTextView,
                emailEditTextLayout,
                passwordTextView,
                passwordEditTextLayout,
                signup,
                together
            )
            startDelay = 100
        }.start()
    }
}