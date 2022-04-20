package com.example.googlesignin_monolit

import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.util.Log.d
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import com.example.googlesignin_monolit.MainActivity.Companion.bindingMain
import com.example.googlesignin_monolit.databinding.FragmentApplicationBinding
import com.example.googlesignin_monolit.databinding.FragmentAuthenticationBinding
import com.example.googlesignin_monolit.databinding.FragmentFirstBinding
import com.example.googlesignin_monolit.model.AppRepository
import com.example.googlesignin_monolit.model.AppRepository.Companion.auth
import com.example.googlesignin_monolit.model.AppRepository.Companion.repository
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth

class firstFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentFirstBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_first, container, false
        )

        repository = AppRepository()

        Handler().postDelayed({
            if (auth.currentUser != null) {
                Log.d("TAG", "Sign in")
                Navigation.findNavController(binding.root)
                    .navigate(R.id.action_firstFragment_to_application2)
            } else {
                Log.d("TAG", "Sign up")

                Navigation.findNavController(binding.root)
                    .navigate(R.id.action_firstFragment_to_authentication2)
            }
        }, 500)
        return binding.root
    }
}

    /*override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

            d("TAG", "Sign up")

            Navigation.findNavController(bindingMain.root).navigate(R.id.action_firstFragment_to_authentication2)
        }

    }*/



