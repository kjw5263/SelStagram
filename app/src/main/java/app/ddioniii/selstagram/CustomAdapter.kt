package app.ddioniii.selstagram

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import app.ddioniii.selstagram.databinding.ItemImageBinding
import com.bumptech.glide.Glide
import org.json.JSONObject

// 어댑터는 연결하는 역할
// DataSet 을 받는다.
class CustomAdapter(private val context: Context,
                    private val dataSet: List<JSONObject>,
                    private var click : CustomClickListener // Todo : Click Event
                    ) :
    RecyclerView.Adapter<CustomAdapter.ViewHolder>() {

    // first -> click (특정한 항목을!) -> second
    // 어떤 항목을 클릭했는지 알기 위해서 CustomAdapter Class 파라미터에 이벤트를 넘겨주어야 한다
    // FirstFragment로부터 CustomAdapter 생성자를 통해 받게 되었음!!
    private var clickListener : CustomClickListener = click

    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder)
     * 클래스에서 콜론':' 의 의미 : ~로부터 상속받았다
     * 메소드에서 콜론':' 의 의미 : 값을 반환한다.
     */
    class ViewHolder(private val context: Context,private val binding: ItemImageBinding) : RecyclerView.ViewHolder(binding.root) { // binding은 item_image.xml을 의미하고, binding.root 는 해당 xml의 root 컴포넌트인 RelativeLayout을 의미

        // 이미지를 어떻게 다운받을것이냐
        // glide
        fun bind(data : JSONObject){
            Glide
                .with(context)  // class에서 받은 context를 bind라는 함수안에서는 사용 불, private val 로 사용가능하게 해줘야함
                .load(data.getString("url")) //어디서 데이터를 가져오겠다
                .fitCenter() // 화면사이즈에맞춰 자동으로 줄임
                .into(binding.mImgMain) // 이미지 표시할 위치 (바인딩)


            if(data.getBoolean("like")){
                // star ON
                binding.mImgLike.setImageResource(android.R.drawable.star_big_on)
            }else {
                // star OFF
                binding.mImgLike.setImageResource(android.R.drawable.star_big_off)
            }

        }


    }

    // Create new views (invoked by the layout manager)
    // 화면에 대한 연결은 해당 메소드에서,
    // createView 홀더는 이 ViewHolder를 만들겟다
    // viewType 형태의 아이템 뷰를 위한 Viewholder 객체를 만들어냅니다
    // 그래서 위 classHolder에 매칭시키겠다는 의미
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        // Create a new view, which defines the UI of the list item
        // item_image 항목들을 반복적으로 조회할 수 있다.

        // 기존 방식이 아닌 biding 방식으로 적용해봄
        ItemImageBinding.inflate(LayoutInflater.from(context), viewGroup,false) // 내가만들었던 바인딩을 활용할 수있따. MainActiivty 꼭대기 까지 붙일거니 ? false
//        val view = LayoutInflater.from(viewGroup.context)
//            .inflate(R.layout.item_image, viewGroup, false)

        // inflater : XML 에 표기된 레이아웃을 객체화 시키는 것으로, XML 코드를 객체화 해서 코드에서 사용하기 위한것
        // Layout 안에 또다른 레이아웃, 또는 컴포넌트를 끼워넣을 때, 또는 교체할 때 쓰는것이다. 여기선 xml 파일로 교체하는 작업

//        return ViewHolder(view)
        return ViewHolder(context, ItemImageBinding.inflate(LayoutInflater.from(context), viewGroup,false))
    }

    // Replace the contents of a view (invoked by the layout manager) -- 뷰가 리플레이스 된다.
    // 데이터의 index 와, 레이아웃아이템뷰의 index 를 연결하는 역할을 함!!!!
    // 화면에 그 행이 나타났을 때, 그 화면이 나타났을 때 호출됨 , 그때그때마다 호출되는 함수임
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) { // 이뷰홀더는 위에서 만든 뷰홀더!
        viewHolder.bind(dataSet[position])
        viewHolder.itemView.setOnClickListener {  // itemView 는 안드로이드에서 제공하는 기본이름, 그 해당하는 행(item_image)을 의미함
            // row 가 아니라, 그 내부에 있는 또 다른 버튼이 있다면 itemView에 이벤트를 주면 안됨
            // 행 클릭 시 second Fragment로 이동할 것임
            clickListener.onClick(position, dataSet[position])
        }

    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = dataSet.size

}
