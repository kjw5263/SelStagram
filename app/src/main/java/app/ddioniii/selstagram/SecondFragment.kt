package app.ddioniii.selstagram

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import app.ddioniii.selstagram.databinding.FragmentSecondBinding
import org.json.JSONObject

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class SecondFragment : Fragment() {

    private var _binding: FragmentSecondBinding? = null


    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private var jsonObject: JSONObject? = null
    private var idx: Int? = -1 // 아래서 arguments 가 nullable이기 때문에 여기서도 nullable 하기 때문에 Int? 라고 해줘야함!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // argument 라는것을 사용해서 데이터를 받아야한다. 원래 jsonObject 타입의 데이터였으니 변환해줌
        jsonObject = JSONObject(arguments?.getString("data")) // BaseBundle 형태의 String 데이터를 받는다.
        // idx 도 함께 받기
        idx = arguments?.getInt("idx")

        _binding = FragmentSecondBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonSecond.setOnClickListener {
            findNavController().navigate(R.id.action_SecondFragment_to_FirstFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}