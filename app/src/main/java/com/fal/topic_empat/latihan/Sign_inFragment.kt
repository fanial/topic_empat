package com.fal.topic_empat.latihan

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.Navigation
import com.fal.topic_empat.R
import com.fal.topic_empat.databinding.FragmentSignInBinding

class Sign_inFragment : Fragment() {
    lateinit var binding: FragmentSignInBinding
    lateinit var share : SharedPreferences

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentSignInBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        share = activity?.getSharedPreferences("AKUN", Context.MODE_PRIVATE)!!

        val unamePreferences = share.getString("usename", "")
        val fnamePreferences = share.getString("fullname", "")
        val passPreferences = share.getString("password", "")
        Log.d("Login","Username : $unamePreferences")
        Log.d("Login","Password : $passPreferences")
        Log.d("Login","Full Name : $fnamePreferences")

        binding.btnSignIn.setOnClickListener {
            val username = binding.logUsername.text.toString()
            val password = binding.logPassword.text.toString()
            if (validateUser(username)){
                if (validatePass(password)){
                    Navigation.findNavController(binding.root).navigate(R.id.action_sign_inFragment_to_homeFragment)
                }else{
                    toast("Username atau Password Anda Salah!")
                }
            }else{
                toast("Akun anda tidak terdaftar!")
            }
        }
        binding.btnRegister.setOnClickListener {
            Navigation.findNavController(binding.root).navigate(R.id.action_sign_inFragment_to_regFragment)
        }
    }

    private fun toast(msg : String) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
    }

    private fun validatePass(password: String): Boolean {
        val passwordShare = share.getString("password", "")
        if (passwordShare != null){
            if (passwordShare.isBlank()){
                return false
            }else{
                if (passwordShare == password){
                    return true
                }
            }
        }
        return false
    }

    private fun validateUser(username : String): Boolean {
        val userShare = share.getString("username","")

        if (userShare != null){
            if (userShare.isBlank()){
                return false
            }else{
                if (userShare == username){
                    return true
                }
            }
        }
        return false
    }

}