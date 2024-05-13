package com.example.joyceapplication

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.joyceapplication.databinding.FragmentJsonBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [JsonFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class JsonFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragmen
        val binding = FragmentJsonBinding.inflate(inflater, container, false)

        val year = arguments?.getString("searchYear") ?: "2024"
        // https://apis.data.go.kr/B552584/UlfptcaAlarmInqireSvc/getUlfptcaAlarmInfo?year=2020&pageNo=1&numOfRows=2&returnType=json&serviceKey=Vpi6St7a9MH4GooTM8GMZzlO%2Fb1I8Ca6%2B%2FoMMAoGq2TKh0ZSAlodtCklIu5P7XIGUqy5i6P7XmMV5j0Erj7Aww%3D%3D
        val call : Call<JsonResponse> = RetrofitConnection.jsonNetworkServ.getJsonList(
            year.toInt(),
            1,
            10,
            "json",
            "Vpi6St7a9MH4GooTM8GMZzlO/b1I8Ca6+/oMMAoGq2TKh0ZSAlodtCklIu5P7XIGUqy5i6P7XmMV5j0Erj7Aww=="
        )

        call?.enqueue(object: Callback<JsonResponse>{
            // 성공
            override fun onResponse(call: Call<JsonResponse>, response: Response<JsonResponse>) {
                Log.d("mobileApp", "$response")
                Log.d("mobileApp", "${response.body()}")
                binding.jsonRecyclerView.adapter = JsonAdapter(response.body()?.response!!.body!!.items)
                binding.jsonRecyclerView.layoutManager = LinearLayoutManager(activity)
                binding.jsonRecyclerView.addItemDecoration(DividerItemDecoration(requireContext(), LinearLayoutManager.VERTICAL))
            }

            // 오류
            override fun onFailure(call: Call<JsonResponse>, t: Throwable) {
                Log.d("mobileApp", "onFailure")
            }
        })
        return binding.root
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment JsonFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            JsonFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}