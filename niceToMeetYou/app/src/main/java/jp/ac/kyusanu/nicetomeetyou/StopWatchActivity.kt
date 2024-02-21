package jp.ac.kyusanu.nicetomeetyou

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import jp.ac.kyusanu.nicetomeetyou.databinding.ActivityStopWatchBinding
import java.text.SimpleDateFormat
import java.util.Locale

class StopWatchActivity : AppCompatActivity() {//stopのSを大文字に
   /* companion object{//JavaのStaticと同じようなものインスタンスを生成しなくて良くなる
        private const val termMilliSecond: Long = 100 //0.1秒 Long= ６４Bit整数
        //constをつけるとgetTermMilliSecondと書かなくて良くなる　？
    }*/

    private lateinit var binding : ActivityStopWatchBinding
    private lateinit var sharedPreferences : SharedPreferences
    private lateinit var editor : SharedPreferences.Editor
    private var time = 0L//時間を測る変数
    private val dataFormat = SimpleDateFormat("mm:ss.S" , Locale.getDefault())//値を日時の書式に変換する。
    private val handler = Handler(Looper.getMainLooper())
    private val timer = object : Runnable {
        override fun run() {
            time += 100
            binding.stopWatchText.text = dataFormat.format(time)
            //r=実行されるRunnable　今回は自分自身なのでthis
            handler.postDelayed(this , 100)//timerの処理をtermMillisecond後(０.1秒後）に行う
        }
    }

    override fun onCreate(savedInstanceState : Bundle?) {
        super.onCreate(savedInstanceState)

        setup()
        setIntentFinish()
        setOnClickListener()
    }

    private fun setup() {
        binding = ActivityStopWatchBinding.inflate(layoutInflater)
        setContentView(binding.root)

        sharedPreferences = getSharedPreferences("Datastore" , Context.MODE_PRIVATE)
        editor = sharedPreferences.edit()

        //ボタンの有効無効を設定
        binding.startButton.isEnabled = true
        binding.stopButton.isEnabled = false
        binding.resetButton.isEnabled = false
    }

    private  fun setIntentFinish() {
        binding.stopWatchReturnButton.setOnClickListener { finish() }
    }

    private fun setOnClickListener() {
        //スタート
        binding.startButton.setOnClickListener {
            handler.post(timer)//handlerでTimerを追加して、実行
            binding.startButton.isEnabled = false//スタートボタンの無効化
            binding.stopButton.isEnabled = true//ストップボタンの有効化
            binding.resetButton.isEnabled = true
        }
        //ストップ
        binding.stopButton.setOnClickListener {
            handler.removeCallbacks(timer)//timerの実行を削除
            binding.startButton.isEnabled = true//スタートボタンの有効化
            binding.stopButton.isEnabled = false//ストップボタンの無効化
        }
        //ラップ
        binding.lapButton.setOnClickListener {
            if(time >= 100) {
                binding.lapView.text = dataFormat.format(time)
                binding.lapText.setText(R.string.stop_watch_lap)
                editor.putLong("Time" , time)
                editor.apply()
            }
        }
        //リセット
        binding.resetButton.setOnClickListener {
            handler.removeCallbacks(timer)//timerの実行を削除
            binding.startButton.isEnabled = true
            binding.stopButton.isEnabled = false//ストップボタンの無効化
            binding.resetButton.isEnabled = false
            time = 0L
            binding.stopWatchText.text = dataFormat.format(time)
        }
        //LAPタイム読み込み
        binding.lapSaveButton.setOnClickListener {
            binding.lapText.setText(R.string.stop_watch_lap)
            val lapTime = sharedPreferences.getLong("Time" ,0)
            binding.lapView.text = dataFormat.format(lapTime).toString()
        }
    }
}