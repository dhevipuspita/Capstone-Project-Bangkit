package com.capstone.cuacatani.ui.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.capstone.cuacatani.data.UserRepository
import com.capstone.cuacatani.data.pref.UserModel
import kotlinx.coroutines.launch

class LoginViewModel(private val userRepository: UserRepository) : ViewModel() {

    fun saveUserSession(user: UserModel) {
        viewModelScope.launch {
            userRepository.saveSession(user)
        }
    }

}