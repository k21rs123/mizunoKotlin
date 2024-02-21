package jp.ac.kyusanu.munakata

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.RadioGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.PlayerConstants
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import jp.ac.kyusanu.munakata.databinding.ViewPagerBinding

//RecyclerView.Adapterを継承 型としてPageViewHolder　コンストラクタとしてlist型のlinks
class ViewPagerAdapter(private val jsonDataArray: MutableList<JsonData>) :
    RecyclerView.Adapter<ViewPagerAdapter.PageViewHolder>() {

    //質問に回答したかを判断するフラグ
    private var questionFlag = MutableList(jsonDataArray.size) { true }

    inner class PageViewHolder(val binding: ViewPagerBinding) :
        RecyclerView.ViewHolder(binding.root)

    //RecyclerViewが新しいViewHolderを作成する必要があるたびに呼び出される。
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PageViewHolder {

        //LayoutInflaterはXMLで定義されたレイアウトをビューオブジェクトに変換するクラス
        //LayoutInflaterでビューオブジェクトに変換するのはview_pager。次の引数のviewGroupは新しく作成されるビューの配属先指定
        val binding: ViewPagerBinding =
            ViewPagerBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return PageViewHolder(binding)
    }

    //指定された位置にデータを表示する。itemViewの内容を更新する
    //holderは内容を更新する必要のあるholderの指定。positionはその指定された項目の位置
    override fun onBindViewHolder(holder: PageViewHolder, position: Int) {

        //holderのitemViewから指定されたIDのYoutubePlayerViewを取得するコード
        //holder.itemViewはRecyclerViewの各レイアウトビューを表す、今回はYoutubePlayerView（IDはyoutube_player_view)
        val binding = holder.binding

        val editText = binding.editText
        val radioGroup = binding.radioGroup
        val youtubePlayerView = binding.youtubePlayerView
        val questionLayout = binding.questionLayout
        youtubePlayerView.isVisible = true
        questionLayout.isVisible =false
        editText.isVisible = true
        radioGroup.isVisible = true


        // 質問は，動画とテキスト版を混在で出したいな．
        // 制御は，サーバのJsonで行いましょう．
        // とりあえず，ここでは，positionで決めてますが，
        // 最終的には，Json内の値で決めて，positionでは決めないよ．

        //質問の内容
        binding.textView.text = jsonDataArray[holder.adapterPosition].question


        fun createRadioButton() {
            binding.radioGroup.removeAllViews()
            Log.d("test","startCreateRadioButton")


            //ラジオボタンに選択式の質問の選択肢を指定。最大四つで選択肢として何も入ってない部分も””で来るので毎回4つ分（重いかも）
            (0..3).forEach {
                if (jsonDataArray[holder.adapterPosition].choices[it] != "") {//""でなければ
                    val radioButton = RadioButton(binding.root.context)//ラジオボタン作成
                    radioButton.layoutParams =
                        RadioGroup.LayoutParams(//RadioGroupのRadioButtonのレイアウトを設定
                            RadioGroup.LayoutParams.WRAP_CONTENT,//レイアウトの指定WRAP_CONTENT使用
                            RadioGroup.LayoutParams.WRAP_CONTENT
                        )
                    radioButton.text = jsonDataArray[holder.adapterPosition].choices[it]
                    binding.radioGroup.addView(radioButton)
                    Log.d("test",radioGroup.toString())
                }
            }
        }

        //受け取ったフラグによって表示させるビューの指定
        when (jsonDataArray[holder.adapterPosition].answerFormatFlag) {//1：選択　２：テキスト　４：動画
            1 -> {
                editText.isVisible = false
                createRadioButton()
            }

            2 -> { radioGroup.isVisible = false }

            3 -> { createRadioButton() }

            4 -> {}

            5 -> {
                editText.isVisible = false
                createRadioButton()
            }

            6 -> { radioGroup.isVisible = false }

            7 -> { createRadioButton() }
        }


        youtubePlayerView.addYouTubePlayerListener(object : AbstractYouTubePlayerListener() {
            //youtubePlayerViewにリスナーの追加を指定
            override fun onReady(youTubePlayer: YouTubePlayer) {//YoutubePlayerの準備ができたら開始
//                youTubePlayer.cueVideo(links[holder.adapterPosition], 0f)
                // cueVideoよりloadVideoの方がよくね？
                youTubePlayer.loadVideo(jsonDataArray[holder.adapterPosition].videoId, 0f)
            }

            //現在の再生位置が指定の場所まで行ったら
            override fun onCurrentSecond(youTubePlayer: YouTubePlayer, second: Float) {
                //現在の秒数が指定の秒数まで到達したら && フラグが有効なら
                if (jsonDataArray[holder.adapterPosition].showQuestionnaireTime <= second && questionFlag[holder.adapterPosition]) {
                    youTubePlayer.pause()
                    questionLayout.isVisible = true

                    val viewPagerAnswer = ViewPagerAnswer(//ビューホルダーのコンテキスト、ViewPagerAdapter,Jsonデータ
                        holder.itemView.context,
                        this@ViewPagerAdapter,
                        jsonDataArray,
                        holder
                    )

                    viewPagerAnswer.setOnClickListener()
                }
            }

            //YoutubePlayerの状態が変わったら実行される処理
            override fun onStateChange(
                youTubePlayer: YouTubePlayer,
                state: PlayerConstants.PlayerState
            ) {

                // Repeat再生
                if (state == PlayerConstants.PlayerState.ENDED) {

                    youTubePlayer.seekTo(0f)
                }
            }
        })
    }

    //データソース内の項目の総数を返す。
    override fun getItemCount(): Int { return jsonDataArray.size }

    //フラグをfalseに
    fun judgeFlag(position: Int) { questionFlag[position] = false }
}