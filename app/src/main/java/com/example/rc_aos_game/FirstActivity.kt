package com.example.rc_aos_game

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.rc_aos_game.databinding.ActivityFirstBinding

class FirstActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFirstBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityFirstBinding.inflate(layoutInflater)
        
        binding.btnGamestart.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }

        setContentView(binding.root)
    }
}