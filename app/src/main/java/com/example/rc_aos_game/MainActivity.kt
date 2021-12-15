package com.example.rc_aos_game

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.Message
import com.example.rc_aos_game.databinding.ActivityMainBinding
import kotlin.concurrent.thread

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    var totalTime = 60
    var started = false


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)

        var handler = object:Handler(Looper.getMainLooper()){
            override fun handleMessage(msg: Message){
                val second = String.format("%02d", totalTime%60)

                if(totalTime > 0){
                    binding.tvLimitTime.text = "$second 초"
                }else{
                    binding.tvLimitTime.text = "종료"
                }
            }
        }
        started = true
        thread(started){
            while(true){
                Thread.sleep(1000)
                totalTime -=1
                handler?.sendEmptyMessage(0)


            }
        }





        setContentView(binding.root)
    }
}