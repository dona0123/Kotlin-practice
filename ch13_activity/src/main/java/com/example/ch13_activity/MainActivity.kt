package com.example.ch13_activity

import android.content.Intent
import android.os.Bundle
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ch13_activity.databinding.ActivityMainBinding
import java.text.SimpleDateFormat

//ch10: 메뉴, 네비게이션, 액션뷰, 드로어 레이아웃, 토글
//ch11: 프래그먼트, 플로팅 버튼

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    var datas : MutableList<String>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // View Binding을 초기화합니다.
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 데이터 목록을 초기화합니다. 이전 상태를 복원합니다.
        datas = savedInstanceState?.let {
            it.getStringArrayList("datas")?.toMutableList()
        } ?: let {
            mutableListOf<String>()
        }

        // 어댑터를 생성하고 리사이클러뷰에 설정합니다.
        val adapter = MyAdapter(datas)
        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = LinearLayoutManager(this)

        // 리사이클러뷰에 구분선을 추가합니다.
        binding.recyclerView.addItemDecoration(
            DividerItemDecoration(this, LinearLayoutManager.VERTICAL)
        )

        // 결과를 처리하기 위한 ActivityResultLauncher를 등록합니다.
        val requestlauncher : ActivityResultLauncher<Intent> = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) { result ->
            // 결과 콜백
            result.data?.getStringExtra("todo")?.let {
                if(it != "") {
                    datas?.add(it)
                    adapter.notifyDataSetChanged()
                }
            }
        }

        // FAB(플로팅 액션 버튼) 클릭 이벤트 처리
        binding.mainFab.setOnClickListener {
            val intent = Intent(this, AddActivity::class.java)

            // 오늘 날짜를 전달합니다.
            val dateFormat = SimpleDateFormat("yyyy-MM-dd")
            intent.putExtra("today", dateFormat.format(System.currentTimeMillis()))

            // AddActivity를 시작하고 결과를 처리합니다.
            requestlauncher.launch(intent)
        }
    }

    // 액티비티 상태 저장
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putStringArrayList("datas", ArrayList(datas))
    }
}
