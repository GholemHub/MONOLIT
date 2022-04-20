package com.example.googlesignin_monolit

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.example.googlesignin_monolit.databinding.FragmentApplicationBinding
import com.example.googlesignin_monolit.model.AppRepository
import com.example.googlesignin_monolit.model.AppRepository.Companion.auth
import com.example.googlesignin_monolit.model.AppRepository.Companion.main
import com.example.googlesignin_monolit.model.AppRepository.Companion.repository
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.firebase.auth.FirebaseAuth

class Application : Fragment() {



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding: FragmentApplicationBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_application, container, false
        )


        //val signInAccount = GoogleSignIn.getLastSignedInAccount(MainActivity())

       // binding.textGoogle.text = signInAccount?.email.toString()

        //d("TAG", "Email: ${auth.currentUser?.uid}")

        binding.logout.setOnClickListener {
            auth.signOut()
            repository = AppRepository()
            auth = FirebaseAuth.getInstance()
            Navigation.findNavController(binding.root).navigate(R.id.action_application_to_firstFragment)
        }

        return binding.root
    }

}
