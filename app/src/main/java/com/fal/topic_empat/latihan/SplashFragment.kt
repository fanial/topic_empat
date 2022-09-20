package com.fal.topic_empat.latihan

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.Navigation
import com.fal.topic_empat.R
import com.fal.topic_empat.databinding.FragmentSplashBinding

class SplashFragment : Fragment() {
    lateinit var binding: FragmentSplashBinding
    lateinit var sharedPref: SharedPreferences

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentSplashBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        sharedPref = activity?.getSharedPreferences("AKUN", Context.MODE_PRIVATE)!!
        val splashTime : Long = 3000

        Handler().postDelayed({
            detectAkun()
        }, splashTime)
    }

    override fun onResume() {
        super.onResume()
        (activity as AppCompatActivity).supportActionBar?.hide()
    }

    override fun onStop() {
        super.onStop()
        (activity as AppCompatActivity).supportActionBar?.show()
    }

    private fun detectAkun() {
        val username = sharedPref.getString("username", "")
        if (username != null){
            if (username.isBlank()){
                Navigation.findNavController(binding.root).navigate(R.id.action_splashFragment_to_sign_inFragment)
            }else{
                Navigation.findNavController(binding.root).navigate(R.id.action_splashFragment_to_homeFragment)
            }
        }
    }

}