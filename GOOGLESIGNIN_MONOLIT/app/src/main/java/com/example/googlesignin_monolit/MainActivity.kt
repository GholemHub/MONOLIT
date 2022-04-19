package com.example.googlesignin_monolit


import android.R
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.widget.FrameLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.get
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.example.googlesignin_monolit.databinding.ActivityMainBinding
import com.example.googlesignin_monolit.model.AppRepository.Companion.main
import com.google.firebase.auth.FirebaseAuth


lateinit var binding: ActivityMainBinding

class MainActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        main = this

        binding = ActivityMainBinding.inflate(layoutInflater)

        var view = binding.root
        setContentView(view)

    }

}