package com.example.googlesignin_monolit

//import com.example.googlesignin_monolit.MainActivity.Companion.repository
//import com.example.googlesignin_monolit.model.AppRepository
import android.content.Intent
import android.os.Bundle
import android.util.Log.d
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.googlesignin_monolit.databinding.FragmentAuthenticationBinding
import com.example.googlesignin_monolit.model.AppRepository
import com.example.googlesignin_monolit.model.AppRepository.Companion.main
import com.example.googlesignin_monolit.model.AppRepository.Companion.repository
import com.example.googlesignin_monolit.model.AppRepository.Companion.viewToRestore
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task

class Authentication : Fragment() {
    private var RC_SIGN_IN = 123

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding: FragmentAuthenticationBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_authentication, container, false
        )

        repository = AppRepository()

        //repository.getClient()

        createRequest()

        //StartLauncher()

        binding.GoogleSignInButton.setOnClickListener {view ->

            signIn()

            viewToRestore = view
           // repository.signInWithGoogle()
        }

        return binding.root
    }
    private var mGoogleSignInClient: GoogleSignInClient? = null

    private fun createRequest() {
        val gso = GoogleSignInOptions
            .Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(main.getString(R.string.default_web_client_id))
            .requestEmail()
            .build()

        mGoogleSignInClient = GoogleSignIn.getClient(main, gso)
    }




    private fun signIn() {
        val signInIntent: Intent = mGoogleSignInClient!!.getSignInIntent()

        startActivityForResult(signInIntent, RC_SIGN_IN)
    }


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

}