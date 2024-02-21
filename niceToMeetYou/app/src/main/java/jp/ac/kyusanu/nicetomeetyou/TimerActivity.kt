package jp.ac.kyusanu.nicetomeetyou

import android.content.Context
import android.content.SharedPreferences
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import jp.ac.kyusanu.nicetomeetyou.databinding.ActivityTimerBinding
import java.text.SimpleDateFormat
import java.util.Locale

class TimerActivity : AppCompatActivity() {

    private lateinit var binding : ActivityTimerBinding

    private lateinit var alarm : MediaPlayer
    private lateinit var timer : CountDownTimer//CountDownTimer用のtimer宣言,値は後で
    private var remainingTime = 0L//残り時間を保持
    var time = SimpleDateFormat("mm:ss.S" , Locale.getDefault())//値を日時形式に変換する
    private lateinit var sharedPreferences : SharedPreferences
    private var startTime : Long = 0
    private var state = false

    override fun onCreate(savedInstanceState : Bundle?) {
        super.onCreate(savedInstanceState)

        setup()
        setIntentFinish()
        setTimer()

    }

    private fun setup() {
        binding = ActivityTimerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        sharedPreferences = getSharedPreferences("Datastore" , Context.MODE_PRIVATE)

        //ボタンの有効、無効の指定
        binding.countRestart.isEnabled = false
        binding.countStop.isEnabled = false
    }

    private fun setIntentFinish() {
        binding.timerReturnButton.setOnClickListener {
            if(state) timer.cancel()
            Log.d("debug" , state.toString())
            finish()
        }
    }

    private fun setTimer() {
        //スタートボタンを押したらカウントダウン
        binding.countStart.setOnClickListener {
            if(startTime != 0L) {
                timer = countDownTimer(startTime).start()

                binding.countStart.isEnabled = false
                binding.countStop.isEnabled = true
                binding.countRestart.isEnabled = false
                binding.readTimeButton.isEnabled = false

                binding.plusTenSecond.isEnabled = false
                binding.plusMinute.isEnabled = false
                binding.plusFiveMinute.isEnabled = false
                state = true
            }
        }

        //ストップボタンを押した時の処理
        binding.countStop.setOnClickListener {
            timer.cancel()//カウントダウンをキャンセル

            binding.countStart.isEnabled = true
            binding.countStop.isEnabled = false
            binding.countRestart.isEnabled = true
            state = false
        }

        //リスタート
        binding.countRestart.setOnClickListener {
            timer = countDownTimer(remainingTime).start()//remainingTimeの値でcountDownTimerをスタート

            binding.countStart.isEnabled = false
            binding.countStop.isEnabled = true
            binding.countRestart.isEnabled = false
            state = true
        }

        //カウントリセット
        binding.countReset.setOnClickListener {
            if(!binding.timerReturnButton.isEnabled) {
                alarm.stop()//アラームストップ
                alarm.release()//alarmのリソースを解放 メモリの圧迫対策
                binding.timerReturnButton.isEnabled = true
            }
            if(state) { timer.cancel() }
            startTime = 0L
            binding.timerText.text = time.format(startTime)

            binding.countStart.isEnabled =true
            binding.countStop.isEnabled = false
            binding.countRestart.isEnabled = false
            binding.readTimeButton.isEnabled = true

            binding.plusTenSecond.isEnabled = true
            binding.plusMinute.isEnabled = true
            binding.plusFiveMinute.isEnabled = true
        }

        binding.plusTenSecond.setOnClickListener {
            startTime += 10000L//10s
            binding.timerText.text = time.format(startTime)
        }

        binding.plusMinute.setOnClickListener {
            startTime += 60000L//60s
            binding.timerText.text = time.format(startTime)
        }

        binding.plusFiveMinute.setOnClickListener {
            startTime += 300000L//300s
            binding.timerText.text = time.format(startTime)
        }

        binding.readTimeButton.setOnClickListener {
            startTime = sharedPreferences.getLong("Time" , 0)
            binding.timerText.text = time.format(startTime)
        }
    }

    private fun countDownTimer(timer : Long) : CountDownTimer {
        return object : CountDownTimer(timer,100) {//timerはカウントダウンが終了してonFinish()が呼び出されるまでのミリ秒数
        // 100はコールバック（OnTick)を受信する間隔　0.1秒ごとに受信
            override fun onTick(remaining : Long) {
                binding.timerText.text = time.format(remaining)
                remainingTime = remaining//残り時間を代入
            }

            override fun onFinish() {//時間切れになったときに発生する処理
                binding.countStart.isEnabled = false
                binding.countStop.isEnabled = false
                binding.countRestart.isEnabled = false
                binding.readTimeButton.isEnabled = false
                binding.timerReturnButton.isEnabled = false

                alarm = MediaPlayer.create(this@TimerActivity , R.raw.alarm)
                alarm.isLooping = true//ループする
                alarm.start()//スタート
            }
        }
    }
}