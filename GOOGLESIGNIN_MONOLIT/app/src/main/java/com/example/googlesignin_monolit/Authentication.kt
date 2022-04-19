package com.example.googlesignin_monolit

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.provider.ContactsContract
import android.util.Log
import android.util.Log.d
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation

//import com.example.googlesignin_monolit.MainActivity.Companion.repository
import com.example.googlesignin_monolit.databinding.FragmentAuthenticationBinding
import com.example.googlesignin_monolit.model.AppRepository
import com.example.googlesignin_monolit.model.AppRepository.Companion.main
import com.example.googlesignin_monolit.model.AppRepository.Companion.repository
import com.example.googlesignin_monolit.model.AppRepository.Companion.viewToRestore
//import com.example.googlesignin_monolit.model.AppRepository
import com.google.android.gms.auth.api.identity.BeginSignInRequest
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class Authentication : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
      val binding: FragmentAuthenticationBinding = DataBindingUtil.inflate(
          inflater, R.layout.fragment_authentication, container, false
      )

        repository = AppRepository()


            StartLauncher()

        binding.GoogleSignInButton.setOnClickListener {view ->

            viewToRestore = view
            repository.signInWithGoogle()
        }

        return binding.root
    }


    fun StartLauncher() {
        repository.launcher = this.registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()){
            val task = GoogleSignIn.getSignedInAccountFromIntent(it.data)
            try {
                repository.account = task.getResult(ApiException::class.java)
                if(repository.account == null){

                    d("TAG", "NOT Registered")
                    repository.firebaseAuthWithGoogle(repository.account.idToken!!)
                }else{
                    d("TAG", "Registered $repository.account")
                    repository.firebaseAuthWithGoogle(repository.account.idToken!!)
                }
            }catch (e: ApiException){
                d("TAG", "ApiException1: $e")
            }
        }
    }


}