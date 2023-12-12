package com.capstone.cuacatani.ui.signup

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.capstone.cuacatani.data.UserRepository
import kotlinx.coroutines.launch

class SignupViewModel(private val userRepository: UserRepository): ViewModel() {
    fun getSession() {
        viewModelScope.launch {
            userRepository.getSession()
        }
    }
}