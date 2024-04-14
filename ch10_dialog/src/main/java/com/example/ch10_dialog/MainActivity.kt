package com.example.ch10_dialog

import android.app.AlertDialog
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.DialogInterface
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.DatePicker
import android.widget.TimePicker
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import com.example.ch10_dialog.databinding.ActivityMainBinding
import com.example.ch10_dialog.databinding.DialogCustomBinding


class MainActivity : AppCompatActivity() {

    // 바인딩 객체 생성
    lateinit var binding : ActivityMainBinding
    // 네비게이션 드로어의 열고 닫힘 상태를 토글하는 객체 생성
    lateinit var toggle : ActionBarDrawerToggle

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // 액티비티 메인 바인딩 객체 생성
        binding = ActivityMainBinding.inflate(layoutInflater)
        // 액티비티의 레이아웃으로 설정
        setContentView(binding.root)

        // ActionBarDrawerToggle 객체 생성 및 초기화
        toggle = ActionBarDrawerToggle(this, binding.drawer, R.string.drawer_opened, R.string.drawer_closed)
        // 액션바에 토글 버튼 표시
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        // 토글의 현재 상태를 액션바에 동기화
        toggle.syncState()

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

        // 단일 선택 목록 알림 대화상자 버튼 클릭 시 이벤트 처리 (라디오버튼)
        var selected = 0
        val eventHandler2 = object:DialogInterface.OnClickListener {
            override fun onClick(dialog: DialogInterface?, which: Int) {
                if (which == DialogInterface.BUTTON_POSITIVE) {
                    Log.d("mobileapp", "BUTTON_POSITIVE")
                    binding.btnAlertSingle.text = "${items[selected]} 선택"
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

        // 다중 선택 목록 알림 대화상자 버튼 클릭 시 이벤트 처리 (체크박스)
        binding.btnAlertMulit.setOnClickListener{
            AlertDialog.Builder(this).run() {
                setTitle("알림 - 다수 선택")
                setIcon(android.R.drawable.ic_dialog_alert)

                setMultiChoiceItems(items, booleanArrayOf(false, true, false, true), object :DialogInterface.OnMultiChoiceClickListener{
                    override fun onClick(dialog: DialogInterface?, which: Int, isChecked: Boolean) {
                        Log.d("mobileapp", "$which ${if(isChecked) "선택" else "해제"}")
                    }
                })

                setPositiveButton("예", eventHandler)
                setNegativeButton("아니오", eventHandler)
                show()
            }
        }

        // 커스텀 다이얼로그의 XML 레이아웃을 바인딩하여 사용 (dialog_custom.xml 사용)
        val dialogBinding = DialogCustomBinding.inflate(layoutInflater)

        // 커스텀 다이얼로그의 버튼 이벤트 처리를 위한 OnClickListener 정의
        val eventHandler3 = object: DialogInterface.OnClickListener {
            override fun onClick(dialog: DialogInterface?, which: Int) {
                // 사용자가 "예" 버튼을 클릭했을 때
                if (which == DialogInterface.BUTTON_POSITIVE) {
                    Log.d("mobileapp", "BUTTON_POSITIVE")
                    // 선택된 라디오 버튼에 따라 버튼 텍스트 설정
                    if(dialogBinding.rbtn1.isChecked) {
                        binding.btnAlertCustom.text = dialogBinding.rbtn1.text.toString()
                    }
                    else if(dialogBinding.rbtn2.isChecked) {
                        binding.btnAlertCustom.text = dialogBinding.rbtn2.text.toString()
                    }
                    else if(dialogBinding.rbtn3.isChecked) {
                        binding.btnAlertCustom.text = dialogBinding.rbtn3.text.toString()
                    }
                    else {
                        binding.btnAlertCustom.text = dialogBinding.rbtn4.text.toString()
                    }
                }
                // 사용자가 "아니오" 버튼을 클릭했을 때
                else if (which == DialogInterface.BUTTON_NEGATIVE) {
                    Log.d("mobileapp", "BUTTON_NEGATIVE")
                }
            }
        }

        // 커스텀 알림 대화상자 버튼 클릭 시 이벤트 처리
        binding.btnAlertCustom.setOnClickListener {
            AlertDialog.Builder(this).run() {
                setTitle("알림 - 사용자 화면")
                setIcon(android.R.drawable.ic_dialog_alert)

                // 커스텀 다이얼로그의 레이아웃 설정
                setView(dialogBinding.root)

                // "예" 버튼 및 "아니오" 버튼에 대한 이벤트 처리 설정
                setPositiveButton("예", eventHandler3)
                setNegativeButton("아니오", eventHandler3)
                show()
            }
        }
    }

    // 옵션 메뉴
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        // 옵션 메뉴를 확장하여 메뉴 아이템을 추가
        // menu_navigation.xml 파일에 정의된 메뉴 리소스를 인플레이트하여 옵션 메뉴에 추가
        menuInflater.inflate(R.menu.menu_navigation, menu)

        // 뷰를 가지고 있는 메뉴 아이템을 추가 (SearchView로 캐스팅)
        // import 단축키 (alt + enter)
        val searchView = menu?.findItem(R.id.menu_search)?.actionView as SearchView

        // 검색어 입력 이벤트 처리
        // Query(검색어)에서 일어나는 이벤트 처리
        searchView.setOnQueryTextListener(object :SearchView.OnQueryTextListener {
            // 검색어를 입력하고 엔터버튼을 눌렀을 때
            override fun onQueryTextSubmit(query: String?): Boolean {
                Toast.makeText(applicationContext, "$query 검색합니다.", Toast.LENGTH_LONG).show()
                return true
            }

            // 검색어에 변경이 생길 때
            override fun onQueryTextChange(newText: String?): Boolean {
                return true
            }
        })

        // 추가적인 초기화나 설정 작업
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(toggle.onOptionsItemSelected(item)) {
            return true
        }

        // 옵션 메뉴 아이템 클릭 이벤트 처리
        when(item.itemId) {
            R.id.item1 -> {
                // 메뉴 1 아이템 선택 시
                Log.d("mobileapp", "Option menu : 메뉴 1")
                // 버튼 텍스트 색상 변경
                binding.btnDate.setTextColor(Color.parseColor("#FFFF00"))
                true
            }
            R.id.item2 -> {
                // 메뉴 2 아이템 선택 시
                Log.d("mobileapp", "Option menu : 메뉴 2")
                true
            }
            R.id.item3 -> {
                // 메뉴 3 아이템 선택 시
                Log.d("mobileapp", "Option menu : 메뉴 3")
                true
            }
            R.id.item4 -> {
                // 메뉴 4 아이템 선택 시
                Log.d("mobileapp", "Option menu : 메뉴 4")
                true
            }
        }
        return super.onOptionsItemSelected(item)
    }
}
