package com.example.ch08_event;

import android.os.Bundle
import android.os.SystemClock
import android.util.Log
import android.view.KeyEvent
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.ch08_event.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    var initTime = 0L // 처음 BACK 버튼이 눌린 시간

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // 뷰 바인딩을 사용하여 레이아웃을 확장
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root) // 활동 콘텐츠를 레이아웃의 루트 뷰로 설정

        // 타이머 이전 시간을 저장하는 변수
        var prevTime = 0L

        // 시작 버튼에 대한 OnClickListener 설정
        binding.startButton.setOnClickListener {
            // 크로노미터의 기준 시간을 현재 시간에 이전 시간을 더한 값으로 설정
            binding.chronometer.base = SystemClock.elapsedRealtime() + prevTime

            // 크로노미터 시작
            binding.chronometer.start()

            // 시작 버튼 비활성화 및 정지 및 재설정 버튼 활성화
            binding.startButton.isEnabled = false
            binding.stopButton.isEnabled = true
            binding.resetButton.isEnabled = true
        }

        // 정지 버튼에 대한 OnClickListener 설정
        binding.stopButton.setOnClickListener {
            // 크로노미터 정지
            binding.chronometer.stop()

            // 이전 시간을 현재 기준 시간에서 크로노미터의 기준 시간을 뺀 값으로 설정
            prevTime = binding.chronometer.base - SystemClock.elapsedRealtime()

            // 정지 버튼 비활성화 및 시작 버튼 활성화
            binding.stopButton.isEnabled = false
            binding.startButton.isEnabled = true
            binding.resetButton.isEnabled = true
        }

        // 재설정 버튼에 대한 OnClickListener 설정
        binding.resetButton.setOnClickListener {
            // 대기 시간을 0으로 초기화
            prevTime = 0L

            // 크로노미터 정지
            binding.chronometer.stop()

            // 재설정 버튼 비활성화 및 시작 버튼 활성화, 정지 버튼 비활성화
            binding.resetButton.isEnabled = false
            binding.startButton.isEnabled = true
            binding.stopButton.isEnabled = false
        }
    } // onCreate

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        when(keyCode) {
            KeyEvent.KEYCODE_BACK -> {
                // 처음 눌렸을 때
                if (System.currentTimeMillis() - initTime > 3000) { // 3초 초과
                    Log.d("mobileapp", "BACK key가 눌렸어요.. 종료하려면 한번 더 누르세요..")
                    initTime = System.currentTimeMillis() // 처음 버튼이 클릭된 시간

                    Toast.makeText(this, "BACK key가 눌렸어요.. 종료하려면 한번 더 누르세요..", Toast.LENGTH_LONG).show()
                    return true
                }
            }

            KeyEvent.KEYCODE_VOLUME_UP -> Log.d("mobileapp", "VOLUME_UP key가 눌렸어요..")
            KeyEvent.KEYCODE_VOLUME_DOWN -> Log.d("mobileapp", "VOLUME_DOWN key가 눌렸어요..")
        }
        return super.onKeyDown(keyCode, event)
    }
} // MainActivity
