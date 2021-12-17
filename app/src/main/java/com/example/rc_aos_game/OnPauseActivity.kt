package com.example.rc_aos_game

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.app.ActivityCompat
import com.example.rc_aos_game.databinding.ActivityOnPauseBinding

class OnPauseActivity : AppCompatActivity() {

    private lateinit var binding: ActivityOnPauseBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityOnPauseBinding.inflate(layoutInflater)

        binding.btnContinue.setOnClickListener {
            MainActivity.started = true
            MainActivity.continueGame = false
            MainActivity.checkhandler = true
            finish()
        }

        binding.btnFinish.setOnClickListener {
            ActivityCompat.finishAffinity(this)
            System.exit(0)
        }

        setContentView(binding.root)
    }
}