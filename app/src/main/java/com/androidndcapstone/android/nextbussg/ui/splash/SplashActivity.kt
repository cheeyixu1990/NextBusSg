package com.androidndcapstone.android.nextbussg.ui.splash

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.androidndcapstone.android.nextbussg.databinding.ActivitySplashBinding
import com.androidndcapstone.android.nextbussg.ui.main.MainActivity

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
@SuppressLint("CustomSplashScreen")
class SplashActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySplashBinding
    private val viewModel: SplashViewModel by lazy {
        val activity = requireNotNull(this) {
            "You can only access the viewModel after onViewCreated()"
        }
        ViewModelProvider(this, SplashViewModel.Factory(activity.application)).get(SplashViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySplashBinding.inflate(layoutInflater)
        binding.viewModel = viewModel

        viewModel.state.observe(this) {
            if (it.busRoutesLoaded && it.busServicesLoaded && it.busStopsLoaded) {
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            }
        }

        setContentView(binding.root)
    }
}