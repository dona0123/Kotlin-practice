import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.ch18_image.databinding.FragmentTwoBinding

class TwoFragment : Fragment() {

    private var _binding: FragmentTwoBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentTwoBinding.inflate(inflater, container, false)
        val view = binding.root

        // 여기에 TwoFragment에 특화된 작업을 추가할 수 있습니다.

        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
