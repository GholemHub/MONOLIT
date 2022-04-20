package com.example.googlesignin_monolit.model

import android.content.Context
import android.content.Context.COMPANION_DEVICE_SERVICE
import android.content.Context.MODE_PRIVATE
import android.content.Intent
import android.content.SharedPreferences
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
    //var auth: FirebaseAuth

    companion object{
        lateinit var main: MainActivity
            @JvmStatic
            lateinit var repository: AppRepository
            lateinit var viewToRestore: View
            lateinit var auth: FirebaseAuth


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


    fun firebaseAuthWithGoogle(idTocken: GoogleSignInAccount){
        val credential = GoogleAuthProvider.getCredential(idTocken.idToken, null)
        auth.signInWithCredential(credential)
            .addOnCompleteListener(main) {
            if(it.isSuccessful){

                //SharedPreferences1()
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

/*
 private fun signIn() {
        val signInIntent: Intent = mGoogleSignInClient!!.getSignInIntent()
        startActivityForResult(signInIntent, RC_SIGN_IN)
    }

    private val RC_SIGN_IN = 123

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            val task: Task<GoogleSignInAccount> = GoogleSignIn.getSignedInAccountFromIntent(data)
            try {
                // Google Sign In was successful, authenticate with Firebase
                val account: GoogleSignInAccount = task.getResult(ApiException::class.java)
                    repository.firebaseAuthWithGoogle(account)
            } catch (e: ApiException) {
                // Google Sign In failed, update UI appropriately
                // ...
                d("TAG", "Google sign in error")

                //Toast.makeText(this, e.message, Toast.LENGTH_SHORT).show()
            }
        }
    }


* */