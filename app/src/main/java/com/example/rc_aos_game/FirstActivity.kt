package com.example.rc_aos_game

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.rc_aos_game.databinding.ActivityFirstBinding

class FirstActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFirstBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityFirstBinding.inflate(layoutInflater)


        setContentView(binding.root)
    }
}