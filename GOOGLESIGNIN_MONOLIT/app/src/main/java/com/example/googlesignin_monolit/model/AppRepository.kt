package com.example.googlesignin_monolit.model

import android.content.Intent
import android.provider.Settings.Global.getString
import android.util.Log
import android.view.View
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.navigation.Navigation
import com.example.googlesignin_monolit.Authentication
import com.example.googlesignin_monolit.MainActivity
import com.example.googlesignin_monolit.R
import com.example.googlesignin_monolit.databinding.ActivityMainBinding
import com.example.googlesignin_monolit.model.AppRepository.Companion.main
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class AppRepository {

    lateinit var launcher: ActivityResultLauncher<Intent>
    lateinit var account: GoogleSignInAccount
    var auth: FirebaseAuth

    companion object{
        lateinit var main: MainActivity
            @JvmStatic
            lateinit var repository: AppRepository
            lateinit var viewToRestore: View
        }


    constructor()
    {
        auth = Firebase.auth
    }

    fun signInWithGoogle(){
        Log.d("TAG", "signInWithGoogle")
        val signInClient = getClient()
        launcher.launch(signInClient.signInIntent)

    }


    fun firebaseAuthWithGoogle(idTocken: String){
        val credential = GoogleAuthProvider.getCredential(idTocken, null)
        auth.signInWithCredential(credential).addOnCompleteListener {
            if(it.isSuccessful){

                Navigation.findNavController(viewToRestore).navigate(R.id.action_authentication_to_application)

                Log.d("TAG", "Google sign in done")
            }else{
                Log.d("TAG", "Google sign in error")
            }
        }
    }
    fun getClient(): GoogleSignInClient {
        Log.d("TAG", "getClient")
        val gso = GoogleSignInOptions
            .Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(main.getString(R.string.default_web_client_id))
            .requestEmail()
            .build()

        return GoogleSignIn.getClient(main, gso)
    }
}