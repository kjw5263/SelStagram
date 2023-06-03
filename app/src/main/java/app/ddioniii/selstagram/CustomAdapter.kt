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
                    private val dataSet: List<JSONObject>
//                  , private var click : CustomClickEvent // Todo : Click Event
                    ) :
    RecyclerView.Adapter<CustomAdapter.ViewHolder>() {

    // first -> click (특정한 항목을!) -> second
    // 어떤 항목을 클릭했는지 알기 위해서 CustomAdapter Class 파라미터에 이벤트를 넘겨주어야 한다
//    private var click : CustomClickEvent = click

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
                .with(context)
                .load(data.getString("url"))
                .fitCenter() // 화면사이즈에맞춰 자동으로 줄임
                .into(binding.mImgMain) // 이미지 표시


            if(data.getBoolean("like")){
                // star
                binding.mImgLike.setImageResource(android.R.drawable.star_big_on)
            }else {
            // star
                binding.mImgLike.setImageResource(android.R.drawable.star_big_off)
            }
        }


    }

    // Create new views (invoked by the layout manager)
    // 화면에 대한 연결은 해당 메소드에서,
    // createView 홀더는 이 ViewHolder를 만들겟다
    // viewType 형태의 아이템 뷰를 위한 Viewholder를 만들어냅니다 
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        // Create a new view, which defines the UI of the list item
        // item_image 항목들을 반복적으로 조회할 수 있다.

        ItemImageBinding.inflate(LayoutInflater.from(context), viewGroup,false) // 내가만들었던 바인딩을 활용할 수있따. MainActiivty 꼭대기 까지 붙일거니 ? false

//        val view = LayoutInflater.from(viewGroup.context)
//            .inflate(R.layout.item_image, viewGroup, false)

        // inflater : XML 에 표기된 레이아웃을 객체화 시키는 것으로, XML 코드를 객체화 해서 코드에서 사용하기 위한것


//        return ViewHolder(view)
        return ViewHolder(context, ItemImageBinding.inflate(LayoutInflater.from(context), viewGroup,false) )
    }

    // Replace the contents of a view (invoked by the layout manager) -- 뷰가 리플레이스 된다.
    // 데이터의 index 와, 레이아웃아이템뷰의 index 를 연결하는 역할을 함!!!!
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {

        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        // viewHolder.textView.text = dataSet[position]
    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = dataSet.size

}
