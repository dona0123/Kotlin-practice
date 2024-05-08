package com.example.ch17_storage

import android.app.Activity
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Color
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.preference.PreferenceManager
import com.example.ch17_storage.databinding.ActivityAddBinding
import java.io.File
import java.io.OutputStreamWriter
import java.text.SimpleDateFormat

class AddActivity : AppCompatActivity() {
    lateinit var binding: ActivityAddBinding
    lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding= ActivityAddBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 설정에서 선택한 컬러 가져오기
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this)
        val color = sharedPreferences.getString("color", "#ffff00")

        // date 컬러 바꾸기
        binding.date.setTextColor(Color.parseColor(color))


        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        var date = intent.getStringExtra("today")
        binding.date.text = date

        binding.btnSave.setOnClickListener {
            val edt_str = binding.addEditView.text.toString()
            val intent = intent
            intent.putExtra("result", edt_str)
            setResult(Activity.RESULT_OK, intent)

            val db = DBHelper(this).writableDatabase
            db.execSQL("insert into my_table (todo) values (?)", arrayOf<String>(edt_str))
            db.close()

            // 파일 쓰기
            val dateFormat = SimpleDateFormat("yyyy-MM-dd hh:mm:ss") // 년 월 일 시 분 초
            val file = File(filesDir, "myFile.txt")
            val writestream:OutputStreamWriter = file.writer()
            writestream.write(dateFormat.format(System.currentTimeMillis()))
            writestream.flush()

            finish()
            true
        }
    } // onCreate()

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_setting, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId === R.id.menu_main_setting) {
            val intent = Intent(this, SettingActivity::class.java)
            startActivity(intent)
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onResume() {
        super.onResume()

        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this)
        val color = sharedPreferences.getString("color", "#ffff00")
        binding.date.setTextColor(Color.parseColor(color))

    }

    override fun onSupportNavigateUp(): Boolean {
        val intent = intent
        setResult(Activity.RESULT_OK, intent)
        finish()
        return true
    }
}