package com.capstone.cuacatani.ui.login

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import com.capstone.cuacatani.MainActivity
import com.capstone.cuacatani.R
import com.capstone.cuacatani.ViewModelFactory
import com.capstone.cuacatani.data.pref.UserModel
import com.capstone.cuacatani.databinding.ActivityLoginBinding
import com.capstone.cuacatani.ui.signup.SignupActivity

class LoginActivity : AppCompatActivity() {
    private val viewModel by viewModels<LoginViewModel> {
        ViewModelFactory.getInstance(this)
    }
    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        signIn()
        playAnimation()

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
            setUpAction(email, password)
        }
    }

    private fun setUpAction(email: String, password: String) {
        binding.btnLogin.setOnClickListener {
            val email = binding.emailEditText.text.toString()
            viewModel.saveUserSession(UserModel(email, "sample_token"))
            AlertDialog.Builder(this).apply {
                setTitle("Yeah!")
                setMessage("Login succesful. Wanna try it now?")
                setPositiveButton("Next") { _, _ ->
                    val intent = Intent(context, MainActivity::class.java)
                    intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
                    startActivity(intent)
                    finish()
                }
                create()
                show()
            }
        }
    }

    private fun playAnimation() {
        ObjectAnimator.ofFloat(binding.imgLogin, View.TRANSLATION_X, -30f, 30f).apply {
            duration = 6000
            repeatCount = ObjectAnimator.INFINITE
            repeatMode = ObjectAnimator.REVERSE
        }.start()

        val emailTextView =
            ObjectAnimator.ofFloat(binding.tvEmail, View.ALPHA, 1f).setDuration(100)
        val emailEditTextLayout =
            ObjectAnimator.ofFloat(binding.emailEditTextLayout, View.ALPHA, 1f).setDuration(100)
        val passwordTextView =
            ObjectAnimator.ofFloat(binding.tvPassword, View.ALPHA, 1f).setDuration(100)
        val passwordEditTextLayout =
            ObjectAnimator.ofFloat(binding.passEditTextLayout, View.ALPHA, 1f).setDuration(100)
        val login = ObjectAnimator.ofFloat(binding.btnLogin, View.ALPHA, 1f).setDuration(100)
        val tvOr = ObjectAnimator.ofFloat(binding.tvOr, View.ALPHA, 1f).setDuration(100)
        val google = ObjectAnimator.ofFloat(binding.btnGoogle, View.ALPHA, 1F).setDuration(100)
        val textSignup = ObjectAnimator.ofFloat(binding.llCreate, View.ALPHA, 1F).setDuration(100)

        AnimatorSet().apply {
            playSequentially(
                emailTextView,
                emailEditTextLayout,
                passwordTextView,
                passwordEditTextLayout,
                login,
                tvOr,
                google,
                textSignup
            )
            startDelay = 100
        }.start()
    }
}