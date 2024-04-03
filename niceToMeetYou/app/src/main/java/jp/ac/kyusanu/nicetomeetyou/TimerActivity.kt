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

    private lateinit var binding: ActivityTimerBinding

    private lateinit var alarm: MediaPlayer
    private lateinit var timer: CountDownTimer//CountDownTimer用のtimer宣言,値は後で
    private var remainingTime = 0L//残り時間を保持
    val time = SimpleDateFormat("mm:ss.S", Locale.getDefault())//値を日時形式に変換する
    private lateinit var sharedPreferences: SharedPreferences
    private var startTime: Long = 0
    private var state = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setup()
        setIntentFinish()
        setTimer()

    }

    private fun setup() {
        binding = ActivityTimerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        sharedPreferences = getSharedPreferences("Datastore", Context.MODE_PRIVATE)

        //ボタンの有効、無効の指定
        binding.countRestart.isEnabled = false
        binding.countStop.isEnabled = false
    }

    private fun setIntentFinish() {
        binding.timerReturnButton.setOnClickListener {
            if (state) timer.cancel()
            Log.d("debug", state.toString())
            finish()
        }
    }

    private fun setTimer() {
        with(binding) {
            //スタートボタンを押したらカウントダウン
            countStart.setOnClickListener {
                if (startTime != 0L) {
                    timer = countDownTimer(startTime).start()

                    countStart.isEnabled = false
                    countStop.isEnabled = true
                    countRestart.isEnabled = false
                    readTimeButton.isEnabled = false

                    plusTenSecond.isEnabled = false
                    plusMinute.isEnabled = false
                    plusFiveMinute.isEnabled = false
                    state = true
                }
            }

            //ストップボタンを押した時の処理
            countStop.setOnClickListener {
                timer.cancel()//カウントダウンをキャンセル

                countStart.isEnabled = true
                countStop.isEnabled = false
                countRestart.isEnabled = true
                state = false
            }

            //リスタート
            countRestart.setOnClickListener {
                timer = countDownTimer(remainingTime).start()//remainingTimeの値でcountDownTimerをスタート

                countStart.isEnabled = false
                countStop.isEnabled = true
                countRestart.isEnabled = false
                state = true
            }

            //カウントリセット
            countReset.setOnClickListener {
                if (!timerReturnButton.isEnabled) {
                    alarm.stop()//アラームストップ
                    alarm.release()//alarmのリソースを解放 メモリの圧迫対策
                    timerReturnButton.isEnabled = true
                }
                if (state) timer.cancel()
                startTime = 0L
                timerText.text = time.format(startTime)

                countStart.isEnabled = true
                countStop.isEnabled = false
                countRestart.isEnabled = false
                readTimeButton.isEnabled = true

                plusTenSecond.isEnabled = true
                plusMinute.isEnabled = true
                plusFiveMinute.isEnabled = true
            }

            plusTenSecond.setOnClickListener {
                startTime += 10000L//10s
                timerText.text = time.format(startTime)
            }

            plusMinute.setOnClickListener {
                startTime += 60000L//60s
                timerText.text = time.format(startTime)
            }

            plusFiveMinute.setOnClickListener {
                startTime += 300000L//300s
                timerText.text = time.format(startTime)
            }

            readTimeButton.setOnClickListener {
                startTime = sharedPreferences.getLong("Time", 0)
                timerText.text = time.format(startTime)
            }
        }
    }

    private fun countDownTimer(timer: Long): CountDownTimer {
        return object : CountDownTimer(timer, 100) {
            //timerはカウントダウンが終了してonFinish()が呼び出されるまでのミリ秒数
            // 100はコールバック（OnTick)を受信する間隔　0.1秒ごとに受信
            override fun onTick(remaining: Long) {
                binding.timerText.text = time.format(remaining)
                remainingTime = remaining//残り時間を代入
            }

            override fun onFinish() {//時間切れになったときに発生する処理
                binding.countStart.isEnabled = false
                binding.countStop.isEnabled = false
                binding.countRestart.isEnabled = false
                binding.readTimeButton.isEnabled = false
                binding.timerReturnButton.isEnabled = false

                alarm = MediaPlayer.create(this@TimerActivity, R.raw.alarm)
                alarm.isLooping = true//ループする
                alarm.start()//スタート
            }
        }
    }
}