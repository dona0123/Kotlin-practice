package com.example.ch13_activity

import android.app.Activity
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.ch13_activity.databinding.ActivityAddBinding

class AddActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // View Binding을 사용하여 레이아웃을 확장합니다.
        val binding = ActivityAddBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 액션 바에서 뒤로 가기 버튼을 활성화합니다.
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        // MainActivity에서 전달된 데이터를 가져옵니다.
        val intent = intent
        val date = intent.getStringExtra("today")

        // 받은 날짜를 TextView에 설정합니다.
        binding.date.text = date

        // 저장 버튼에 대한 OnClickListener를 설정합니다.
        binding.btnSave.setOnClickListener {
            // MainActivity로 데이터를 다시 보내기 위한 새 인텐트를 생성합니다.
            val intent = intent

            // EditText에서 입력한 텍스트를 인텐트에 넣습니다.
            intent.putExtra("todo", binding.addEditView.text.toString())

            // 액티비티의 결과를 OK로 설정하고 인텐트를 전달합니다.
            setResult(Activity.RESULT_OK, intent)

            // 액티비티를 종료합니다.
            finish()

            // 이벤트가 소비되었음을 나타내기 위해 true를 반환합니다.
            true
        }
    }

    // 상위 네비게이션(액션 바의 뒤로 가기 버튼) 처리
    override fun onSupportNavigateUp(): Boolean {
        val intent = intent

        // 인텐트를 포함하여 액티비티의 결과를 설정합니다.
        setResult(Activity.RESULT_OK, intent)

        // 액티비티를 종료합니다.
        finish()
        return true
    }
}
