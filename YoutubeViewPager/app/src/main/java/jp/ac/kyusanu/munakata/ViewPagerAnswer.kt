package jp.ac.kyusanu.munakata

import android.content.Context
import android.util.Log
import androidx.appcompat.app.AlertDialog
import androidx.core.view.isVisible
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.Call
import okhttp3.Callback
import okhttp3.FormBody
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import java.io.IOException
import java.util.concurrent.TimeUnit

class ViewPagerAnswer(
    private val context: Context,
    private val adapter: ViewPagerAdapter,
    private val jsonDataArray: MutableList<JsonData>,
    private val pageViewHolder: ViewPagerAdapter.PageViewHolder
) {
    private var selectedText = ""
    private val youtubePlayerView = pageViewHolder.binding.youtubePlayerView
    private val radioGroup = pageViewHolder.binding.radioGroup
    private val editText = pageViewHolder.binding.editText
    private val cancelButton = pageViewHolder.binding.cancelButton
    private val okButton = pageViewHolder.binding.okButton

    fun setOnClickListener() {


        okButton.setOnClickListener {
            // ラジオボタンが選ばれているか.テキストが入力されているか確認
            if (radioGroup.checkedRadioButtonId != -1 || editText.text.isNotEmpty()) {
                CoroutineScope(Dispatchers.Main).launch {// 非同期タスク。メインスレッドで行う
                    try {
                        okHTTP(pageViewHolder)
                    } catch (e: Exception) {//try の処理がエラーを起こした場合
                        // エラー
                        e.printStackTrace()
                        Log.d("Failed", "")
                    }
                }
                youtubePlayerView.addYouTubePlayerListener(object : AbstractYouTubePlayerListener(){
                    override fun onReady(youTubePlayer: YouTubePlayer) {
                        youTubePlayer.play()
                    }
                })

                youtubePlayerView.isVisible = true
                val builder = AlertDialog.Builder(context)
                    .setTitle("ありがとうございました")
                    .setPositiveButton("OK",null)
                    .create()

                builder.show()
            } else {
                val builder = AlertDialog.Builder(context)
                    .setTitle("選択されていません")
                    .setPositiveButton("OK",null)
                    .create()

                builder.show()
            }
        }

        cancelButton.setOnClickListener {
            youtubePlayerView.isVisible = true
        }
    }

    private fun okHTTP(pageViewHolder: ViewPagerAdapter.PageViewHolder) {

        /*val client = OkHttpClient.Builder()//ビルダー作成
            //接続とレスポンスに60秒以上かかる場合タイムアウト
            .connectTimeout(5000.toLong(), TimeUnit.MILLISECONDS)
            .readTimeout(5000.toLong(), TimeUnit.MILLISECONDS)
            .build()

        if (radioGroup.isNotEmpty()) {//選択肢がある場合
            selectedText = radioGroup.findViewById<RadioButton>(radioGroup.checkedRadioButtonId).text.toString()

        }

        val data = JsonEncode(//JsonEncodeにデータを渡してエンコードしてもらう。結果をdataに渡す
            jsonDataArray[pageViewHolder.adapterPosition].id,
            selectedText,
            editText.text.toString()
        )
        Log.d("", Json.encodeToString(data))

        val requestBody = Json.encodeToString(data).//JSON形式の文字列に変換
        toRequestBody("application/json; charset=utf-8".toMediaType())//メディアタイプの指定。json,utf-8であることを伝える
        Log.d("JsonEncode",requestBody.toString())
        // リクエストオブジェクトの作成
        val request = Request.Builder()
            .url("https://my-json-server.typicode.com/typicode/demo/posts") // 送信先のURLを指定します
            .post(requestBody) // POSTメソッドを使用してJSONデータを送信
            .build()

        client.newCall(request).enqueue(object : Callback {//非同期
            override fun onFailure(call: Call, e: IOException) {//失敗

                e.printStackTrace()
                Log.d("Failed", "")
            }

            override fun onResponse(call: Call, response: Response) {//成功
                // レスポンスを受信した場合の処理

                adapter.judgeFlag(pageViewHolder.adapterPosition)

                val responseData = response.body?.string().orEmpty()
                Log.d("Response: ", responseData)
                response.close()
            }
        })*/

        val client = OkHttpClient.Builder()
           .connectTimeout(5000.toLong(), TimeUnit.MILLISECONDS)
           .readTimeout(5000.toLong(), TimeUnit.MILLISECONDS)
           .build()

           // Bodyのデータ（サンプル）
           val sendDataJson = "{\"name\":\"hogehoge\"}"

            val body:FormBody = FormBody.Builder()
                .add("test","test2")
                .build()

           // Requestを作成
           val request = Request.Builder()
               .url("http://133.17.165.172:8086/yamashita/munakata/uploadAnswer.php")
               .post(body)
               .build()

           client.newCall(request).enqueue(object : Callback {
               override fun onResponse(call: Call, response: Response) {
                   // Responseの読み出し
                   val responseBody = response.body?.string().orEmpty()
                   // 必要に応じてCallback
                   Log.d("response",responseBody)
                   adapter.judgeFlag(pageViewHolder.adapterPosition)
               }

               override fun onFailure(call: Call, e: IOException) {
                   Log.e("Error", e.toString())
                   // 必要に応じてCallback
               }
           })


    }

}