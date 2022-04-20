package com.example.googlesignin_monolit


import android.R
import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.util.Log.d
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.appcompat.app.AppCompatActivity

import com.example.googlesignin_monolit.databinding.ActivityMainBinding
import com.example.googlesignin_monolit.model.AppRepository
import com.example.googlesignin_monolit.model.AppRepository.Companion.main
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {
    companion object{
        @JvmStatic
        lateinit var bindingMain: ActivityMainBinding
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        bindingMain = ActivityMainBinding.inflate(layoutInflater)

        main = this




        setContentView(bindingMain.root)
    }

}