package com.example.rc_aos_game

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.Message
import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintSet
import com.example.rc_aos_game.databinding.ActivityMainBinding
import java.util.*
import kotlin.concurrent.thread

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    var totalTime = 30 // 30초
    var started = false

    var serving = arrayOfNulls<Int>(7)
    var foods = arrayOf(R.drawable.burger, R.drawable.cheesecake, R.drawable.hotdog,
                            R.drawable.spaghetti, R.drawable.steak, R.drawable.sushi)
    var check = arrayOf(false, false, false, false, false, false, false)
    var checkNum = 0

    var isSuccess = 0

    var menu = arrayOf("햄버거", "치즈\n케이크", "핫도그",
        "스파\n게티", "스테\n이크", "스시")
    var tv_menu = arrayOfNulls<Int>(7)

    val random = Random()
    var heartnum = 0




    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)

        var textViewMenu = arrayOf(binding.tvCustomer1, binding.tvCustomer2, binding.tvCustomer3,
            binding.tvCustomer4, binding.tvCustomer5, binding.tvCustomer6, binding.tvCustomer7)

        var heart = arrayOf(binding.ivHeart1, binding.ivHeart2)

        var success = arrayOf(binding.ivSuccess1, binding.ivSuccess2, binding.ivSuccess3,
            binding.ivSuccess4, binding.ivSuccess5, binding.ivSuccess6, binding.ivSuccess7)
        var fail = arrayOf(binding.ivFail1, binding.ivFail2, binding.ivFail3, binding.ivFail4,
            binding.ivFail5, binding.ivFail6, binding.ivFail7)



        for(i in 0..6){ // 주문 menu
            val num = random.nextInt(6)
            serving[i] = num // 서빙할 음식 번호 저장
            tv_menu[i] = num // 말풍선에 나올 메뉴 명
            textViewMenu[i].text = menu[tv_menu[i]!!]
        }




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


        if (checkNum<7){
            binding.ivBurger.setOnClickListener{
                if(serving[checkNum] == 0){
                    check[checkNum] = true
                }
                if(check[checkNum]){
                    success[isSuccess].visibility = View.VISIBLE // 성공 이미지 띄우기
                    isSuccess += 1
                }else{
                    heart[heartnum].visibility = View.INVISIBLE // 생명 감소
                    heartnum += 1

                    fail[isSuccess].visibility = View.VISIBLE // 실패 이미지 띄우기
                    isSuccess += 1

                    if(heartnum == 2){
                        Toast.makeText(this, "game over", Toast.LENGTH_SHORT).show()
                    }
                }
                checkNum += 1
            }

            binding.ivCheesecake.setOnClickListener{
                if(serving[checkNum] == 1){
                    check[checkNum] = true
                }
                if(check[checkNum]){
                    success[isSuccess].visibility = View.VISIBLE // 성공 이미지 띄우기
                    isSuccess += 1
                }else{
                    heart[heartnum].visibility = View.INVISIBLE
                    heartnum += 1

                    fail[isSuccess].visibility = View.VISIBLE // 실패 이미지 띄우기
                    isSuccess += 1

                    if(heartnum == 2){
                        Toast.makeText(this, "game over", Toast.LENGTH_SHORT).show()
                    }
                }
                checkNum += 1
            }
            binding.ivHotdog.setOnClickListener{
                if(serving[checkNum] == 2){
                    check[checkNum] = true
                }
                if(check[checkNum]){
                    success[isSuccess].visibility = View.VISIBLE // 성공 이미지 띄우기
                    isSuccess += 1
                }else{
                    heart[heartnum].visibility = View.INVISIBLE
                    heartnum += 1

                    fail[isSuccess].visibility = View.VISIBLE // 실패 이미지 띄우기
                    isSuccess += 1

                    if(heartnum == 2){
                        Toast.makeText(this, "game over", Toast.LENGTH_SHORT).show()
                    }
                }
                checkNum += 1
            }
            binding.ivSpaghetti.setOnClickListener{
                if(serving[checkNum] == 3){
                    check[checkNum] = true
                }
                if(check[checkNum]){
                    success[isSuccess].visibility = View.VISIBLE // 성공 이미지 띄우기
                    isSuccess += 1
                }else{
                    heart[heartnum].visibility = View.INVISIBLE
                    heartnum += 1

                    fail[isSuccess].visibility = View.VISIBLE // 실패 이미지 띄우기
                    isSuccess += 1

                    if(heartnum == 2){
                        Toast.makeText(this, "game over", Toast.LENGTH_SHORT).show()
                    }
                }
                checkNum += 1
            }
            binding.ivSteak.setOnClickListener{
                if(serving[checkNum] == 4){
                    check[checkNum] = true
                }
                if(check[checkNum]){
                    success[isSuccess].visibility = View.VISIBLE // 성공 이미지 띄우기
                    isSuccess += 1
                }else{
                    heart[heartnum].visibility = View.INVISIBLE
                    heartnum += 1

                    fail[isSuccess].visibility = View.VISIBLE // 실패 이미지 띄우기
                    isSuccess += 1

                    if(heartnum == 2){
                        Toast.makeText(this, "game over", Toast.LENGTH_SHORT).show()
                    }
                }
                checkNum += 1
            }
            binding.ivSushi.setOnClickListener{
                if(serving[checkNum] == 5){
                    check[checkNum] = true
                }
                if(check[checkNum]){
                    success[isSuccess].visibility = View.VISIBLE // 성공 이미지 띄우기
                    isSuccess += 1
                }else{
                    heart[heartnum].visibility = View.INVISIBLE
                    heartnum += 1

                    fail[isSuccess].visibility = View.VISIBLE // 실패 이미지 띄우기
                    isSuccess += 1

                    if(heartnum == 2){
                        Toast.makeText(this, "game over", Toast.LENGTH_SHORT).show()
                    }
                }
                checkNum += 1

            }
        }




        setContentView(binding.root)
    }

}




