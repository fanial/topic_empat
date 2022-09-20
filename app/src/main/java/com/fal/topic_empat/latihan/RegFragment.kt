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
import com.fal.topic_empat.databinding.FragmentRegBinding

class RegFragment : Fragment() {

    lateinit var binding: FragmentRegBinding
    lateinit var share : SharedPreferences

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentRegBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        share = activity?.getSharedPreferences("AKUN", Context.MODE_PRIVATE)!!

        binding.btnReg.setOnClickListener {
            val fullname = binding.regName.text.toString()
            val username = binding.regUsername.text.toString()
            val password = binding.regPassword.text.toString()
            val rePass = binding.regPassValidate.text.toString()

            if (password != rePass){
                toast("Password tidak sama!")
            }else{
                addSharePref(username,fullname,password)
                Navigation.findNavController(binding.root).navigate(R.id.action_regFragment_to_sign_inFragment)
            }
        }

    }

    private fun addSharePref(username: String, fullname: String, password: String) {
        val addAkun = share.edit()
        Log.d("Reg","Full Name : $fullname")
        Log.d("Reg","Username : $username")
        Log.d("Reg","Password : $password")
        addAkun.putString("fullname", fullname)
        addAkun.putString("username", username)
        addAkun.putString("password", password)
        addAkun.apply()
        toast("Registrasi Berhasil, silahkan Login!")
    }

    private fun toast(msg: String) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
    }

}