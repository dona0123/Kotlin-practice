package com.example.ch13_activity

import android.app.Activity
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.ch13_activity.databinding.ActivityAddBinding

class AddActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityAddBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // addActivity의 홈화면에 붙이기
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val intent = intent
        val date = intent.getStringExtra("today")
        binding.date.text = date

        binding.btnSave.setOnClickListener {
            // MainActivity에 전달
            val intent = intent
            intent.putExtra("todo", binding.addEditView.text.toString())
            setResult(Activity.RESULT_OK, intent)

            finish()
            true
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        return super.onSupportNavigateUp()
    }

}
