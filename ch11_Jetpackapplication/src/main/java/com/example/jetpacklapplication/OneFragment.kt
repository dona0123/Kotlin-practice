package com.example.jetpacklapplication

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import com.example.jetpacklapplication.databinding.FragmentOneBinding

// FragmentOneBinding을 사용하여 레이아웃과 바인딩

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [OneFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class OneFragment : Fragment() {
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

    // Fragment 작성
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Fragment 레이아웃을 인플레이트하여 바인딩
        val binding = FragmentOneBinding.inflate(inflater, container, false)
        binding.fragButton.setOnClickListener {
            binding.oneFragment.setBackgroundColor(Color.parseColor("#00ffff"))

            Toast.makeText(context, "oneFragment", Toast.LENGTH_LONG).show()

            context?.let { it1 -> // non-null 만 들어올 수 있음
                AlertDialog.Builder(it1).run() {
                    setTitle("알림 - 모바일 앱")
                    setIcon(android.R.drawable.ic_dialog_alert)
                    setMessage("정말로 종료하시겠습니까?")
                    setPositiveButton("예", null)
                    setNegativeButton("아니오", null)
                    show()
                }
            }
        }
        return binding.root
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment OneFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            OneFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}