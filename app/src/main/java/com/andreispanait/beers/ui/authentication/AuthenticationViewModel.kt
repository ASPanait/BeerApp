package com.andreispanait.beers.ui.authentication

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.andreispanait.beers.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthenticationViewModel @Inject constructor(
    private val userRepository: UserRepository
): ViewModel() {

    fun signIn(){

    }

    fun signUp(email: String, password: String){
        viewModelScope.launch {
            userRepository.signUp(email,password)
        }
    }

}