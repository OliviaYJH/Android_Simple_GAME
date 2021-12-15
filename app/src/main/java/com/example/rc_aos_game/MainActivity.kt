package com.example.rc_aos_game

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.Message
import com.example.rc_aos_game.databinding.ActivityMainBinding
import java.util.*
import kotlin.concurrent.thread

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    var totalTime = 60
    var started = false
    var serving = arrayOfNulls<Int>(12)
    var foods = arrayOf(R.drawable.burger, R.drawable.cheesecake, R.drawable.hotdog,
                            R.drawable.spaghetti, R.drawable.steak, R.drawable.sushi)

    var iv_customers = arrayOfNulls<Int>(12)
    var customers = arrayOf(R.drawable.person1, R.drawable.person2, R.drawable.person3, R.drawable.person4,
                                R.drawable.person5, R.drawable.person6, R.drawable.person7)

    var menu = arrayOf("햄버거", "치즈 케이크", "핫도그",
        "스파게티", "스테이크", "스시")
    var tv_menu = arrayOfNulls<Int>(12)

    val random = Random()
    var chances = 0 // 맞든 틀리든 수행한 횟수
    var heart = 3 // 생명


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)


        for(i in 0..11){ // 주문 menu
            val num = random.nextInt(6)
            serving[i] = num // 서빙할 음식 랜덤으로 12개 입력해놓기
            tv_menu[i] = num // 말풍선에 나올 메뉴 명
        }

        for(i in 0..11){ // 손님
            val custom_num = random.nextInt(7)
            iv_customers[i] = custom_num
        }


        // 1번 손님
        binding.ivCustom1.setImageResource(customers[serving[0]!!])
        binding.tvCustom1Menu.text = menu[tv_menu[0]!!]

        // 2번 손님
        binding.ivCustom2.setImageResource(customers[serving[1]!!])
        binding.tvCustom2Menu.text = menu[tv_menu[1]!!]

        // 3번 손님
        binding.ivCustom3.setImageResource(customers[serving[3]!!])
        binding.tvCustom3Menu.text = menu[tv_menu[2]!!]

        // 4번 손님
        binding.ivCustom4.setImageResource(customers[serving[4]!!])
        binding.tvCustom4Menu.text = menu[tv_menu[3]!!]





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