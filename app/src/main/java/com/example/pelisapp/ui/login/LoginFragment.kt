package com.example.pelisapp.ui.login

import android.content.ContentValues
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.domain.common.Resource
import com.example.domain.interactors.user.User
import com.example.pelisapp.R
import com.example.pelisapp.databinding.FragmentLoginBinding
import org.koin.android.ext.android.inject


class LoginFragment : Fragment(R.layout.fragment_login) {
    private val loginViewModel: LoginViewModel by inject()
    private lateinit var binding : FragmentLoginBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLoginBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonLoginLogin.setOnClickListener {
            if(binding.edEmailLogin.text.toString().isNotEmpty() &&
                binding.edPasswordLogin.text.toString().isNotEmpty())
                loginViewModel.login(User(binding.edEmailLogin.text.toString(),
                    binding.edPasswordLogin.text.toString()))
            else
                Toast.makeText(requireContext(), "Debe llenar todos los campos", Toast.LENGTH_SHORT).show()
        }

        /*binding.tvDontHaveAnAccount.setOnClickListener {
            loginViewModel.registerUser()
        }*/

        observeLoginState()
    }

    private fun observeLoginState(){
        loginViewModel.signInViewState.observe(viewLifecycleOwner){state ->
            when(state){
                is Resource.Loading-> {
                    //todo agregar pantalla de carga
                }
                is Resource.Success->{
                    Toast.makeText(requireContext(), "Acceso Exitoso", Toast.LENGTH_SHORT).show()
                    findNavController().navigate(R.id.action_loginFragment2_to_catalogueFragment)
                }
                is Resource.Error ->{
                    Toast.makeText(requireContext(), "Error al ingresar: ${state.message}", Toast.LENGTH_SHORT).show()
                    Log.e(ContentValues.TAG, state.message!!)
                }
            }
        }
    }


}