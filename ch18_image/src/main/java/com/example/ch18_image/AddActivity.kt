package com.example.ch18_image

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.ch18_image.databinding.ActivityAddBinding
import java.text.SimpleDateFormat

// 하나의 데이터를 입력할 폼
class AddActivity : AppCompatActivity() {
    lateinit var binding : ActivityAddBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.tvId.text = MyApplication.email

        binding.saveButton.setOnClickListener {
            if(binding.input.text.isNotEmpty()) {
                // 시간 포멧 변경
                val dateFormat = SimpleDateFormat("yyyy-MM-dd hh:mm:ss")

                // 로그인 이메일, 별점, 한줄평, 입력 시간 저장
                val data = mapOf(
                    "email" to MyApplication.email,
                    "stars" to binding.ratingBar.rating.toFloat(),
                    "comments" to binding.input.text.toString(),
                    "date_time" to dateFormat.format(System.currentTimeMillis())
                )
                MyApplication.db.collection("comments")
                    .add(data)
                    .addOnSuccessListener {
                        Toast.makeText(this, "데이터 저장 성공", Toast.LENGTH_LONG).show()
                        finish()
                    }
                    .addOnFailureListener {
                        Toast.makeText(this, "데이터 저장 실패", Toast.LENGTH_LONG).show()
                    }
            }
            else {
                Toast.makeText(this, "한줄평을 입력 해주세요..", Toast.LENGTH_LONG).show()
            }
        }

    }
}