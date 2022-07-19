package com.andreispanait.beers.ui.authentication.signup

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.andreispanait.beers.databinding.FragmentSignupBinding
import com.andreispanait.beers.ui.authentication.AuthenticationViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.util.regex.Pattern

@AndroidEntryPoint
class SignUpFragment : Fragment() {

    private val viewModel: AuthenticationViewModel by activityViewModels()
    private var _binding: FragmentSignupBinding? = null
    private val binding get() = _binding!!



    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSignupBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.signup.setOnClickListener {

            val emailPattern: Pattern = Pattern.compile(
                "[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" +
                        "\\@" +
                        "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" +
                        "(" +
                        "\\." +
                        "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" +
                        ")+"
            )


            val email = binding.email.editText?.text?.toString() ?: return@setOnClickListener
            if (!email.matches(emailPattern.toRegex())){
                 return@setOnClickListener
            }

            val password = binding.password.editText?.text?.toString() ?: return@setOnClickListener
            viewModel.signUp(email, password)
        }
    }
}