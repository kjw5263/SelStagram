package app.ddioniii.selstagram

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import app.ddioniii.selstagram.databinding.FragmentFirstBinding
import org.json.JSONObject

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {

    private var _binding: FragmentFirstBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // LayoutManager 연결
        // RecyclerView 에 표시할 초기 DataSet 리스트 생성 : url, like 등을 설정
        //RecyclerView 어댑터 생성
        // 툴 : RecyclerView adapter samle 검색
        // 이미지를 불러오는 glide 라이브러리로 ImageView에 이미지 표시
        // RecyclerView 어댑터에 DataSet 연결

        // 이미지 크기를 제각각 가변ㄴ적으로 구현하는 것은 StaggeredGridLayout
        // spanCount : 가로로 몇개를 보여줄지 의미/ orientaion : 화면 방향
        var layoutManager = StaggeredGridLayoutManager(3, LinearLayout.VERTICAL)
        binding.mRcvMain.layoutManager = layoutManager // 리사이클러뷰 레이아웃 매니저에 내가 만든 레이아웃 매니저 넣음
        binding.mRcvMain.setHasFixedSize(true) // 리사이클러뷰 컴포넌트 자체의 높이 고정여부

        makeList()

        // 어댑터에 넘기기
        var adapter = CustomAdapter(list) // CustomAdapter 가 받을 파라미터 종류를 데이터에 맞게 변경해준다.
        // 리사이클러뷰와 연결하기
        binding.mRcvMain.adapter = adapter
        // 데이터가 바뀌었음을 알려주기 (binding.mRcvMain.adapter 라고 하면 어떤걸 바인딩하려는지 모른다.)
        adapter.notifyDataSetChanged()

        binding.buttonFirst.setOnClickListener {
            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
        }
    }

    private var list = mutableListOf<JSONObject>()

    private fun makeList() {
        val jsonObject1 = JSONObject()
        jsonObject1.put("url", "https://cdn.pixabay.com/photo/2023/04/26/08/09/porridge-7951848_1280.jpg")
        jsonObject1.put("like", false)

        // 중략
        list.add(jsonObject1)
        list.add(jsonObject1)
        list.add(jsonObject1)
        list.add(jsonObject1)
        list.add(jsonObject1)
        list.add(jsonObject1)
        list.add(jsonObject1)
        // 중략
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}