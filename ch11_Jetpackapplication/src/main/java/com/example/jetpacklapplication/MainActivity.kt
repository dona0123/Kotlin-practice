package com.example.jetpacklapplication

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.jetpacklapplication.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    // View Binding을 사용하여 레이아웃과 바인딩
    private lateinit var binding: ActivityMainBinding

    // ViewPager2에 사용될 FragmentStateAdapter 구현
    class MyFragmentPagerAdapter(activity: FragmentActivity) : FragmentStateAdapter(activity) {
        // ViewPager2에 표시될 Fragment 목록
        private val fragments: List<Fragment> = listOf(OneFragment(), TwoFragment(), ThreeFragment())

        override fun getItemCount(): Int {
            return fragments.size
        }

        override fun createFragment(position: Int): Fragment {
            return fragments[position]
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // View Binding 초기화
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // ViewPager2에 어댑터 설정
        binding.viewpager.adapter = MyFragmentPagerAdapter(this)
    }
}
