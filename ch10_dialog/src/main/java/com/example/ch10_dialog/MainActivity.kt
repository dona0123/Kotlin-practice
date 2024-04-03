package com.example.ch10_dialog

import android.app.AlertDialog
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.DialogInterface
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.widget.DatePicker
import android.widget.TimePicker
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.ch10_dialog.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // 바인딩 객체 생성
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 날짜 선택 다이얼로그 띄우기
        binding.btnDate.setOnClickListener {
            DatePickerDialog(this, object: DatePickerDialog.OnDateSetListener {
                override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
                    // 선택한 날짜를 토스트 메시지로 표시
                    Toast.makeText(applicationContext, "$year 년 ${month + 1} 월 $dayOfMonth 일", Toast.LENGTH_LONG).show()

                    // 선택한 날짜를 버튼 텍스트로 설정
                    binding.btnDate.text = "$year 년 ${month + 1} 월 $dayOfMonth 일"

                    // 버튼 텍스트 크기 설정
                    binding.btnDate.textSize = 24f

                    // 버튼 텍스트 색상 설정
                    binding.btnDate.setTextColor(Color.parseColor("#ffff00"))
                }
            }, 2024, 3, 5).show() // 초기 날짜 설정
        }

        // 시간 선택 다이얼로그 띄우기
        binding.btnTime.setOnClickListener {
            TimePickerDialog(this, object: TimePickerDialog.OnTimeSetListener{
                override fun onTimeSet(view: TimePicker?, hourOfDay: Int, minute: Int) {
                    // 선택한 시간을 토스트 메시지로 표시
                    Toast.makeText(applicationContext, "$hourOfDay 시 $minute 분", Toast.LENGTH_LONG).show()

                    // 선택한 시간을 버튼 텍스트로 설정
                    binding.btnTime.text = "$hourOfDay 시 $minute 분"

                    // 버튼 텍스트 크기 설정
                    binding.btnTime.textSize = 24f

                    // 버튼 텍스트 색상 설정
                    binding.btnTime.setTextColor(Color.parseColor("#00ffff"))
                }
            }, 16, 56, true).show()
        }

        // 일반 알림 대화상자 버튼 클릭 리스너 설정
        val eventHandler = object: DialogInterface.OnClickListener {
            override fun onClick(dialog: DialogInterface?, which: Int) {
                if (which == DialogInterface.BUTTON_POSITIVE) {
                    Log.d("mobileapp", "BUTTON_POSITIVE")
                }
                else if (which == DialogInterface.BUTTON_NEGATIVE) {
                    Log.d("mobileapp", "BUTTON_NEGATIVE")
                }
            }
        }

        // 일반 알림 대화상자 버튼 클릭 시 이벤트 처리
        binding.btnAlert.setOnClickListener {
            AlertDialog.Builder(this).run() {
                setTitle("알림 - 모바일 앱")
                setIcon(android.R.drawable.ic_dialog_alert)
                setMessage("정말로 종료하시겠습니까?")
                setPositiveButton("예", eventHandler)
                setNegativeButton("아니오", eventHandler)
                setNeutralButton("상세 설명", eventHandler)
                show()
            }
        }

        // 목록 알림 대화상자 버튼 클릭 시 이벤트 처리
        val items = arrayOf<String>("골드키위", "그냥키위", "힙합고로케", "그냥고로케")
        binding.btnAlertItem.setOnClickListener {
            AlertDialog.Builder(this).run() {
                setTitle("알림 - 음식 선택")
                setIcon(android.R.drawable.ic_dialog_alert)
                setItems(items, object:DialogInterface.OnClickListener{
                    override fun onClick(dialog: DialogInterface?, which: Int) {
                        // 선택한 항목을 로그로 출력
                        Log.d("mobileapp", "${items[which]} 선택")

                        // 선택한 항목을 버튼 텍스트로 설정
                        binding.btnAlertItem.text = "${items[which]} 선택"

                        // 버튼 텍스트 크기 설정
                        binding.btnAlertItem.textSize = 24f

                        // 버튼 텍스트 색상 설정
                        binding.btnAlertItem.setTextColor(Color.parseColor("#FE9A2E"))
                    }
                })
                setPositiveButton("예", eventHandler)
                setNegativeButton("아니오", eventHandler)
                show()
            }
        }

        // 단일 선택 목록 알림 대화상자 버튼 클릭 시 이벤트 처리
        var selected = 0
        val eventHandler2 = object:DialogInterface.OnClickListener {
            override fun onClick(dialog: DialogInterface?, which: Int) {
                if (which == DialogInterface.BUTTON_POSITIVE) {
                    Log.d("mobileapp", "BUTTON_POSITIVE")
                }
                else if (which == DialogInterface.BUTTON_NEGATIVE) {
                    Log.d("mobileapp", "BUTTON_NEGATIVE")
                }
            }
        }

        binding.btnAlertSingle.setOnClickListener{
            AlertDialog.Builder(this).run() {
                setTitle("알림 - 음식 선택")
                setIcon(android.R.drawable.ic_dialog_alert)
                setSingleChoiceItems(items, 2, object:DialogInterface.OnClickListener{
                    override fun onClick(dialog: DialogInterface?, which: Int) {
                        selected = which
                    }
                })
                setPositiveButton("예", eventHandler2)
                setNegativeButton("아니오", eventHandler2)
                show()
            }
        }

        // 다중 선택 목록 알림 대화상자 버튼 클릭 시 이벤트 처리
        binding.btnAlertMulit.setOnClickListener{

        }

        // 커스텀 알림 대화상자 버튼 클릭 시 이벤트 처리
        binding.btnAlertCustom.setOnClickListener {

        }
    }
}
