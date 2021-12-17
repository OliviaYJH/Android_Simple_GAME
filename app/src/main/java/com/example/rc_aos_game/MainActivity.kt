package com.example.rc_aos_game

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.Message
import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintSet
import com.example.rc_aos_game.databinding.ActivityMainBinding
import java.util.*
import kotlin.concurrent.thread

class MainActivity : AppCompatActivity() {

    companion object{
        var again = false
        var continueGame = false
        var started = false
        var checkhandler = false
    }

    private lateinit var binding: ActivityMainBinding
    var totalTime = 30 // 30초

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
                }else if(totalTime == 0){
                    // 실패 activity 띄우기
                    startActivity(Intent(this@MainActivity, FailActivity::class.java))
                }
            }
        }
        started = true
        thread(started){
            while(started){
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
                    showSuccessImage(success)
                }else{
                    invisibleHeartImage(heart)
                    showFailImage(fail)
                    checkIfFail()
                }
                GameSuccess()
            }

            binding.ivCheesecake.setOnClickListener{
                if(serving[checkNum] == 1){
                    check[checkNum] = true
                }

                if(check[checkNum]){
                    showSuccessImage(success)
                }else{
                    invisibleHeartImage(heart)
                    showFailImage(fail)
                    checkIfFail()
                }
                GameSuccess()
            }
            binding.ivHotdog.setOnClickListener{
                if(serving[checkNum] == 2){
                    check[checkNum] = true
                }

                if(check[checkNum]){
                    showSuccessImage(success)
                }else{
                    invisibleHeartImage(heart)
                    showFailImage(fail)
                    checkIfFail()
                }
                GameSuccess()
            }
            binding.ivSpaghetti.setOnClickListener{
                if(serving[checkNum] == 3){
                    check[checkNum] = true
                }

                if(check[checkNum]){
                    showSuccessImage(success)
                }else{
                    invisibleHeartImage(heart)
                    showFailImage(fail)
                    checkIfFail()
                }
                GameSuccess()
            }
            binding.ivSteak.setOnClickListener{
                if(serving[checkNum] == 4){
                    check[checkNum] = true
                }

                if(check[checkNum]){
                    showSuccessImage(success)
                }else{
                    invisibleHeartImage(heart)
                    showFailImage(fail)
                    checkIfFail()
                }
                GameSuccess()
            }
            binding.ivSushi.setOnClickListener{
                if(serving[checkNum] == 5){
                    check[checkNum] = true
                }

                if(check[checkNum]){
                    showSuccessImage(success)
                }else{
                    invisibleHeartImage(heart)
                    showFailImage(fail)
                    checkIfFail()
                }
                GameSuccess()
            }
        }

        setContentView(binding.root)
    }

    fun showSuccessImage(success: Array<ImageView>){
        success[isSuccess].visibility = View.VISIBLE // 성공 이미지 띄우기
        isSuccess += 1
    }

    fun showFailImage(fail: Array<ImageView>){
        fail[isSuccess].visibility = View.VISIBLE // 실패 이미지 띄우기
        isSuccess += 1
    }

    fun invisibleHeartImage(heart: Array<ImageView>){
        heart[heartnum].visibility = View.INVISIBLE
        heartnum += 1
    }

    fun checkIfFail(){
        if(heartnum == 2){
            // 실패 activity 띄우기
            startActivity(Intent(this, FailActivity::class.java))
        }
    }

    fun GameSuccess(){
        checkNum += 1
        if(checkNum == 7){
            // 성공 activity 띄우기
            startActivity(Intent(this, SuccessActivity::class.java))
        }
    }

    override fun onRestart() {
        super.onRestart()
        if(again){
            finish()
            startActivity(Intent(this, MainActivity::class.java))
            again = false
        }
    }

    override fun onPause() {
        super.onPause()
        // 시간 감소 멈추기
        started = false
        continueGame = true
    }

    override fun onResume() {
        super.onResume()

        if(continueGame){
            startActivity(Intent(this, OnPauseActivity::class.java))
        }

        if(checkhandler){
            var handler = object:Handler(Looper.getMainLooper()){
                override fun handleMessage(msg: Message){
                    val second = String.format("%02d", totalTime%60)

                    if(totalTime > 0){
                        binding.tvLimitTime.text = "$second 초"
                    }else{
                        binding.tvLimitTime.text = "종료"
                        // 실패 activity 띄우기

                    }
                }
            }
            started = true
            thread(started){
                while(started){
                    Thread.sleep(1000)
                    totalTime -= 1
                    handler?.sendEmptyMessage(0)
                }
            }

            checkhandler = false
        }


    }

    override fun onStop() {
        super.onStop()
        continueGame = false
    }

}




