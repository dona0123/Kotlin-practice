package com.example.joyceapplication

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.joyceapplication.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_main)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 프래그먼트를 변수로
        val jsonfragment = JsonFragment()
        val xmlfragment = XmlFragment()
        val bundle = Bundle()

        binding.btnSearch.setOnClickListener {
            bundle.putString("searchYear", binding.edtYear.text.toString())

            // json 버튼이 눌렸을 시
            if (binding.rGroup.checkedRadioButtonId == R.id.rbJson) {
                jsonfragment.arguments = bundle
                supportFragmentManager.beginTransaction()
                    // activity_content -> jsonfragment
                    .replace(R.id.activity_content, jsonfragment)
                    // replace 실행
                    .commit()
            } else if (binding.rGroup.checkedRadioButtonId == R.id.rbXml) {
                xmlfragment.arguments = bundle
                supportFragmentManager.beginTransaction()
                    .replace(R.id.activity_content, xmlfragment)
                    .commit()
            }
        }
    }
}