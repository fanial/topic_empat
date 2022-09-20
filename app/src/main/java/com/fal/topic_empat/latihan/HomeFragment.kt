package com.fal.topic_empat.latihan

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.fal.topic_empat.R
import com.fal.topic_empat.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    lateinit var binding : FragmentHomeBinding
    lateinit var share : SharedPreferences

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        share = activity?.getSharedPreferences("AKUN", Context.MODE_PRIVATE)!!
        val fullname = share.getString("fullname", "")
        binding.vFullName.text = fullname

        val unamePreferences = share.getString("usename", "")
        val fnamePreferences = share.getString("fullname", "")
        val passPreferences = share.getString("password", "")
        Log.d("Home","Username : $unamePreferences")
        Log.d("Home","Password : $passPreferences")
        Log.d("Home","Full Name : $fnamePreferences")

        binding.btnLogout.setOnClickListener {
            Navigation.findNavController(binding.root).navigate(R.id.action_homeFragment_to_sign_inFragment)
        }

    }

}