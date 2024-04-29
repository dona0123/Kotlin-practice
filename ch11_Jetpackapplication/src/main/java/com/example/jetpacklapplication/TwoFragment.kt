package com.example.jetpacklapplication

import android.content.Context
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Rect
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.jetpacklapplication.databinding.FragmentTwoBinding
import com.example.jetpacklapplication.databinding.ItemRecyclerviewBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [TwoFragment.newInstance] factory method to
 * create an instance of this fragment.
 */

class MyViewHolder(val binding: ItemRecyclerviewBinding) : RecyclerView.ViewHolder(binding.root)
class MyRcyclerAdater(val datas: MutableList<String>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return MyViewHolder(ItemRecyclerviewBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val binding = (holder as MyViewHolder).binding
        binding.itemData.text = datas[position]
    }

    override fun getItemCount(): Int {
        return datas.size
    }
}

class MyDecoration(val context: Context): RecyclerView.ItemDecoration() {
    override fun onDraw(c: Canvas, parent: RecyclerView, state: RecyclerView.State) { // 그림 -> 항목
        super.onDraw(c, parent, state)
        c.drawBitmap(BitmapFactory.decodeResource(context.resources, R.drawable.kbo), 0f, 0f, null)
    }

    override fun onDrawOver(c: Canvas, parent: RecyclerView, state: RecyclerView.State) { // 항목 -> 그림
        super.onDrawOver(c, parent, state)
        c.drawBitmap(BitmapFactory.decodeResource(context.resources, R.drawable.kbo), 500f, 500f, null)
    }

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        super.getItemOffsets(outRect, view, parent, state)
        view.setBackgroundColor(Color.parseColor("#345678"))
    }
}

class TwoFragment : Fragment() {
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
        // Inflate the layout for this fragment
        val binding = FragmentTwoBinding.inflate(inflater, container, false)

        // 데이터 변경
        var datas = mutableListOf<String>()
        for(i in 1 .. 10) {
            datas.add("Item $i")
        }

        // adapter & viewHolder (어뎁터, 뷰 홀더)
        val adapter = MyRcyclerAdater(datas)
        binding.recyclerView.adapter = adapter // MyRcyclerAdaper(datas)

        // layoutManger (레이아웃 매니저)
        val linear = LinearLayoutManager(activity)
        binding.recyclerView.layoutManager = linear
        linear.orientation = LinearLayoutManager.HORIZONTAL

        var grid = GridLayoutManager(activity, 2, GridLayoutManager.VERTICAL, false)
        binding.recyclerView.layoutManager = grid // linear

        // 선택적 꾸미기
        binding.recyclerView.addItemDecoration(MyDecoration(activity as Context))

        // 플로팅 버튼이 눌리면
        binding.mainFab.setOnClickListener{
            datas.add("Android Add")
            adapter.notifyDataSetChanged()
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
         * @return A new instance of fragment TwoFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            TwoFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}