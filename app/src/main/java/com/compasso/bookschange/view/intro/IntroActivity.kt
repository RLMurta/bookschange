package com.compasso.bookschange.view.intro

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import androidx.core.app.ActivityCompat
import androidx.lifecycle.ViewModelProvider
import com.compasso.bookschange.databinding.ActivityIntroBinding
import com.compasso.bookschange.view.main.MainActivity
import com.compasso.bookschange.viewModel.ViewModelFactory
import com.compasso.bookschange.viewModel.intro.IntroActivityViewModel

class IntroActivity : AppCompatActivity() {

    private lateinit var binding: ActivityIntroBinding
    private lateinit var viewModel: IntroActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityIntroBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel =
            ViewModelProvider(this, ViewModelFactory(this)).get(IntroActivityViewModel::class.java)

        isAccessLocationPermissionAccepted()
        viewModel.isAccessLocationPermissionAccepted.observe(this, {
            goToMain()
        })
    }

    private fun isAccessLocationPermissionAccepted() {
        if (ActivityCompat.checkSelfPermission(
            this,
            Manifest.permission.ACCESS_FINE_LOCATION
        ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
            this,
            Manifest.permission.ACCESS_COARSE_LOCATION
        ) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                101
            )
        } else {
            viewModel.setLocationPermissionStatus()
        }
    }

    private fun goToMain() {
        Handler().postDelayed(Runnable {
            startActivity(Intent(this, MainActivity::class.java))
        }, 2000)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when(requestCode) {
            101 -> {
                viewModel.setLocationPermissionStatus()
            }
        }
    }
}