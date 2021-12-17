package com.example.rc_aos_game

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.app.ActivityCompat
import com.example.rc_aos_game.databinding.ActivityFailBinding

class FailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityFailBinding.inflate(layoutInflater)

        binding.btnAgain.setOnClickListener {
            finish()
            MainActivity.again = true
        }

        binding.btnFinish.setOnClickListener {
            ActivityCompat.finishAffinity(this)
            System.exit(0)
        }

        setContentView(binding.root)
    }
}