package com.example.ch18_image

import FourFragment
import ThreeFragment
import TwoFragment
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.preference.PreferenceManager
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.example.ch18_image.databinding.ActivityMainBinding
import com.google.android.material.navigation.NavigationView
import com.google.android.material.tabs.TabLayoutMediator
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private lateinit var binding: ActivityMainBinding
    private lateinit var toggle: ActionBarDrawerToggle
    private lateinit var headerView: View
    private lateinit var viewPager: ViewPager2
    lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this)
        val color = sharedPreferences.getString("color", "#FFFFFF")
        binding.tabs.setBackgroundColor(Color.parseColor(color))


        viewPager = binding.viewPager

        // DrawerLayout Toggle
        toggle = ActionBarDrawerToggle(
            this,
            binding.drawer,
            R.string.drawer_opened,
            R.string.drawer_closed
        )
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        toggle.syncState()

        // Drawer 메뉴
        binding.mainDrawerView.setNavigationItemSelectedListener(this)

        // 메인 네비게이션
        headerView = binding.mainDrawerView.getHeaderView(0)

        // 버튼 클릭 리스너
        val button = headerView.findViewById<Button>(R.id.btnAuth)
        button.setOnClickListener {
            Log.d("mobileapp", "button.setOnClickListener")
            val intent = Intent(this, AuthActivity::class.java)
            if (button.text == "로그인") {
                intent.putExtra("status", "logout")
            } else if (button.text == "로그아웃") {
                intent.putExtra("status", "login")
            }
            startActivity(intent)
            binding.drawer.closeDrawers()
        }

        // 검색 버튼 클릭 리스너
        binding.btnSearch.setOnClickListener {
            if (MyApplication.checkAuth()) {
                val name = binding.edtName.text.toString()
                Log.d("mobileapp", name)

                val call: Call<XmlResponse> =
                    RetrofitConnection.xmlNetworkService.getXmlList(
                        name,
                        1,
                        10,
                        "xml",
                        "Vpi6St7a9MH4GooTM8GMZzlO/b1I8Ca6+/oMMAoGq2TKh0ZSAlodtCklIu5P7XIGUqy5i6P7XmMV5j0Erj7Aww==" // 일반인증키(Decoding)
                    )

                call.enqueue(object : Callback<XmlResponse> {
                    override fun onResponse(
                        call: Call<XmlResponse>,
                        response: Response<XmlResponse>
                    ) {
                        if (response.isSuccessful) {
                            Log.d("mobileApp", "$response")
                            Log.d("mobileApp", "${response.body()}")
                            binding.xmlRecyclerView.layoutManager =
                                LinearLayoutManager(applicationContext)
                            binding.xmlRecyclerView.adapter =
                                XmlAdapter(response.body()?.body?.items?.item)
                            binding.xmlRecyclerView.addItemDecoration(
                                DividerItemDecoration(
                                    applicationContext,
                                    LinearLayoutManager.VERTICAL
                                )
                            )
                        }
                    }

                    override fun onFailure(call: Call<XmlResponse>, t: Throwable) {
                        Log.d("mobileApp", "onFailure ${call.request()}")
                    }
                })
            } else {
                Toast.makeText(this, "인증을 먼저 해주세요..", Toast.LENGTH_SHORT).show()
            }
        }

        // ViewPager2 설정
        viewPager.adapter = MyFragmentPagerAdapter(this)

        // TabLayout과 ViewPager2 연결
        TabLayoutMediator(binding.tabs, viewPager) { tab, position ->
            tab.text = "Tab ${position + 1}"
        }.attach()
    }

    // DrawerLayout Toggle
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (toggle.onOptionsItemSelected(item)) {
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    // Drawer 메뉴
    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.item_board -> {
                Log.d("mobileapp", "게시판 메뉴")
                val intent = Intent(this, BoardActivity::class.java)
                startActivity(intent)
            }
            R.id.item_setting -> {
                Log.d("mobileapp", "설정 메뉴")
                val intent = Intent(this, SettingActivity::class.java)
                startActivity(intent)
            }
        }
        return false
    }

    override fun onStart() {
        super.onStart()

        val button = headerView.findViewById<Button>(R.id.btnAuth)
        val tv = headerView.findViewById<TextView>(R.id.tvID)

        if (MyApplication.checkAuth()) {
            button.text = "로그아웃"
            tv.text = "${MyApplication.email}님 \n 반갑습니다."
        } else {
            button.text = "로그인"
            tv.text = "안녕하세요.."
        }
    }

    // 설정 바로 반영
    override fun onResume() {
        super.onResume()

        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this)
        val color = sharedPreferences.getString("color", "#FFFFFF")
        binding.tabs.setBackgroundColor(Color.parseColor(color))



    }

    // ViewPager2에 사용될 FragmentStateAdapter 구현
    inner class MyFragmentPagerAdapter(activity: FragmentActivity) :
        FragmentStateAdapter(activity) {

        // ViewPager2에 표시될 Fragment 목록
        private val fragments: List<Fragment> =
            listOf(TwoFragment(), ThreeFragment(), FourFragment())

        override fun getItemCount(): Int {
            return fragments.size
        }

        override fun createFragment(position: Int): Fragment {
            return fragments[position]
        }
    }
}
