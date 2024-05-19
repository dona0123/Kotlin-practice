package com.example.ch18_image

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ch18_image.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // 0
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnSearch.setOnClickListener{
            val name = binding.edtName.text.toString()
            Log.d("mobileapp", name)

            val call: Call<XmlResponse> = RetrofitConnection.xmlNetworkService.getXmlList(
                name,
                1,
                10,
                "xml",
                "Vpi6St7a9MH4GooTM8GMZzlO/b1I8Ca6+/oMMAoGq2TKh0ZSAlodtCklIu5P7XIGUqy5i6P7XmMV5j0Erj7Aww==" // 일반인증키(Decoding)
            )

            call?.enqueue(object : Callback<XmlResponse> {
                override fun onResponse(call: Call<XmlResponse>, response: Response<XmlResponse>) {
                    if(response.isSuccessful){
                        Log.d("mobileApp", "$response")
                        Log.d("mobileApp", "${response.body()}")
                        binding.xmlRecyclerView.layoutManager = LinearLayoutManager(applicationContext)
                        binding.xmlRecyclerView.adapter = XmlAdapter(response.body()!!.body!!.items!!.item)
                        binding.xmlRecyclerView.addItemDecoration(DividerItemDecoration(applicationContext, LinearLayoutManager.VERTICAL))
                    }
                }

                override fun onFailure(call: Call<XmlResponse>, t: Throwable) {
                    Log.d("mobileApp", "onFailure ${call.request()}")
                }
            })
        }

    }
}