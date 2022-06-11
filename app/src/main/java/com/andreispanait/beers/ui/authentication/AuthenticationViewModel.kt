package com.andreispanait.beers.ui.authentication

import androidx.lifecycle.ViewModel
import com.andreispanait.beers.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel

@HiltViewModel
class AuthenticationViewModel(
    private val userRepository: UserRepository
): ViewModel() {

    fun signIn(){

    }

    fun signUp(){

    }

}