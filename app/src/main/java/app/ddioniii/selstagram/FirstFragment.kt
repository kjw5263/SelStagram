package app.ddioniii.selstagram

import android.app.Application
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import app.ddioniii.selstagram.databinding.FragmentFirstBinding
import org.json.JSONObject

interface CustomClickListener {
    fun onClick(idx: Int, rowData: JSONObject) // 클릭 뿐만 아니라, 이미지 url 정보, 몇번째 아이템을 눌렀는지 넘겨주면 좋다.
}


/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment(), CustomClickListener {  // 인터페이스를 implements 하는 것은 ! 콤마를 찍고 추가해주면 됨

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


        // activity를 들어가보면 Fragment가 nullable임 -> 즉 함부로 사용하면 안된다!
        var act = activity
        if(act != null){
            // 어댑터에 넘기기
            /* Q. Activity 와 Context는 한끗차이 !
             Context 는 휴대폰의 많은 기능,구성요소에 접근할 수 있는 매개체
             activity 는 그 중 하나의 수단 (범용적 의미.. 여기서의 activity는 class를 의미), 즉 Context 중 일부
             nullable 인 context가 null 이 아닐때 사용할 수 있도록 함
             */
            // this 는 이 클래스의 onClick 이벤트를 의미
            var adapter = CustomAdapter(act, list, this) // CustomAdapter 가 받을 파라미터 종류를 데이터에 맞게 변경해준다.
            // 리사이클러뷰와 연결하기
            binding.mRcvMain.adapter = adapter
            // 데이터가 바뀌었음을 알려주기 (binding.mRcvMain.adapter 라고 하면 어떤걸 바인딩하려는지 모른다.)
            adapter.notifyDataSetChanged()
        }



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

    // override 구현했다 ~ 상속했다
    override fun onClick(idx: Int, rowData: JSONObject) {
        // custom Adapter -> click -> event -> onClick called -> move second
        // 커스텀 어댑터에서 클릭을 한다고 ?!
        // 넘기는 방식 !? Bundle !!
        /* 1. bundle 함수 만들기
           pair : 키와 값의 쌍으로 쓰겠다! (android 제공)
           JSONObject와 유사하긴 하다. 하지만 Bundle은 pair을 매개체로 데이터를 넘긴다.
         */
        var bundle = bundleOf("data" to rowData.toString()) // 문법...check (data 라는 키값에 rowData가 들어있다)
        bundle.putInt("idx", idx)

        // 2. 어떻게 넘겨서 전달하지? : findNavController 활용
        // naviage 함수에 어떤 번들을 담아서 보낼껀지도 추가할수있음
        findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment, bundle)


    }
}