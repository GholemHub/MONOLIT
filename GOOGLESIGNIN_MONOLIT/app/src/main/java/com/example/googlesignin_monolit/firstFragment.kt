package com.example.googlesignin_monolit

import android.os.Bundle
import android.os.Handler
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import com.example.googlesignin_monolit.databinding.FragmentApplicationBinding
import com.example.googlesignin_monolit.databinding.FragmentAuthenticationBinding
import com.example.googlesignin_monolit.databinding.FragmentFirstBinding
import com.google.firebase.auth.FirebaseAuth

class firstFragment : Fragment() {

    private lateinit var auth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentFirstBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_first, container, false
        )
        auth = FirebaseAuth.getInstance()
        val user = auth.currentUser
        Handler().postDelayed({
            if(user != null){
                Log.d("TAG", "Sign in")
            }else{
                Log.d("TAG", "Sign up")

                Navigation.findNavController(binding.root).navigate(R.id.action_firstFragment_to_authentication2)
            }
        }, 2000)

        return binding.root
    }
}