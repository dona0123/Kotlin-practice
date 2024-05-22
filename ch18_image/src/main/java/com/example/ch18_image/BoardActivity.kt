package com.example.ch18_image

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ch18_image.databinding.ActivityBoardBinding
import com.google.firebase.firestore.Query

class BoardActivity : AppCompatActivity() {
    lateinit var binding : ActivityBoardBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBoardBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 로그인 한 사용자만 커뮤니티 사용 가능
        binding.mainFab.setOnClickListener {
            if(MyApplication.checkAuth()){
                startActivity(Intent(this, AddActivity::class.java))
            }
            else {
                Toast.makeText(this, "인증을 먼저 해주세요..", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onStart() {
        super.onStart()

        // 파이어베이스 데이터 가져오기
        if(MyApplication.checkAuth()){
            MyApplication.db.collection("comments")
                .orderBy("date_time", Query.Direction.DESCENDING)
                .get()
                .addOnSuccessListener { result ->
                    val itemList = mutableListOf<ItemData>()
                    for (document in result) {
                        val item = document.toObject(ItemData::class.java)
                        item.docId = document.id
                        itemList.add(item)
                    }
                    binding.recyclerView.layoutManager = LinearLayoutManager(this)
                    binding.recyclerView.adapter = BoardAdapter(this, itemList)
                }
                .addOnFailureListener{
                    Toast.makeText(this, "서버 데이터 획득 실패", Toast.LENGTH_SHORT).show()
                }
        }
    }
}