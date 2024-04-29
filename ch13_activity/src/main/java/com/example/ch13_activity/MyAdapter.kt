package com.example.ch13_activity

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.ch13_activity.databinding.ItemRecyclerviewBinding

// ViewHolder 클래스 정의
class MyViewHolder(val binding: ItemRecyclerviewBinding) : RecyclerView.ViewHolder(binding.root)

// Adapter 클래스 정의
class MyAdapter(val datas: MutableList<String>?): RecyclerView.Adapter<RecyclerView.ViewHolder>(){ // 2-1
    // 아이템의 개수를 반환
    // datas가 null이면 0을 반환, 그렇지 않으면 datas의 크기를 반환
    override fun getItemCount(): Int {
        return datas?.size ?:0;     // 2-2
        // ?: 만약 null 이면 0 리턴
    }


    // 새로운 아이템 뷰를 위한 ViewHolder를 생성하고 반환
    // LayoutInflater를 사용하여 XML 레이아웃 파일을 인플레이트하여 ViewHolder를 생성
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return MyViewHolder(ItemRecyclerviewBinding.inflate(LayoutInflater.from(parent.context), parent,false))
    }

    // ViewHolder와 데이터를 바인딩
    // 해당 위치(position)의 데이터를 ViewHolder에 표시
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val binding = (holder as MyViewHolder).binding
        binding.itemData.text = datas!![position]      // 2-3
    }
}
