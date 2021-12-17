package com.example.rc_aos_game

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.app.ActivityCompat
import com.example.rc_aos_game.databinding.ActivitySuccessBinding

class SuccessActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySuccessBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySuccessBinding.inflate(layoutInflater)

        binding.btnAgain.setOnClickListener{
            finish()
            MainActivity.again = true
            MainActivity.continueGame = false
        }

        binding.btnFinish.setOnClickListener{
            MainActivity.continueGame = false
            ActivityCompat.finishAffinity(this)
            System.exit(0)
        }

        setContentView(binding.root)
    }
}