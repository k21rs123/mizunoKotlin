package jp.ac.kyusanu.munakata

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.viewpager2.widget.ViewPager2
import jp.ac.kyusanu.munakata.databinding.ActivityMainBinding
import kotlinx.serialization.json.Json
import okhttp3.Call
import okhttp3.Callback
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import java.io.IOException
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class MainActivity : AppCompatActivity() {

    private lateinit var jsonArray: MutableList<JsonData>

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setup()
        connectServer()
        //setViewPager2()

    }

    private fun setup() {
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    private fun setViewPager2() {
        //viewPager2の設定、縦フリック、表示する画面のリンクの設定
        binding.viewPager2.orientation = ViewPager2.ORIENTATION_VERTICAL
        //adapter:表示するためのビューを管理し、必要に応じてデータの読み込み表示を行う
        binding.viewPager2.adapter = ViewPagerAdapter(jsonArray)
    }

    private fun loadJson(jsonString: String): MutableList<JsonData> {
        //jsonStringをjsonDataの型にデコードし、MutableListに格納した結果を代入

        return Json.decodeFromString(jsonString)
    }

    private fun connectServer() {
        //URL指定
        val url = "http://133.17.165.172:8086/yamashita/munakata/outputQuestionData.php"

        //リクエスト作成
        val request = Request.Builder()
            .url(url)
            .build()

        //enqueue:非同期　同期はexecute
        OkHttpClient().newCall(request).enqueue(object : Callback {
            //失敗時
            override fun onFailure(call: Call, e: IOException) {
                e.printStackTrace()
                Log.d("test!!!!!!", "miss")
            }

            //成功時
            override fun onResponse(call: Call, response: Response) {
                //帰ってきたデータをStringに
                val responseData = response.body?.string()

                //データがnullでなければresponseDataを入れたloadJsonを実行
                responseData?.also {
                    jsonArray = loadJson(it)
                    setStateDateTime(jsonArray)
                    runOnUiThread {
                        setViewPager2()
                    }
                }
                Log.d("test", responseData.toString())
            }
        })
    }

    //受け取ったデータの期限を過ぎておらず、開始日を超えているかを判断
    private fun setStateDateTime(jsonArray: MutableList<JsonData>) {
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
        val today = LocalDateTime.now()

        //for (i in 0 until jsonArray.size) {
        (0 until jsonArray.size).forEach{
            val startDateTime = LocalDateTime.parse(jsonArray[it].startDateTime, formatter)
            val endDateTime = LocalDateTime.parse(jsonArray[it].endDateTime, formatter)
            if (startDateTime >= today || endDateTime <= today) {
                jsonArray.removeAt(it)//条件に合わなければ消す
            }
        }
    }

}