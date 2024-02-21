package jp.ac.kyusanu.nicetomeetyou

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import jp.ac.kyusanu.nicetomeetyou.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setup()
        selectIntent()
    }

    private fun setup() {
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)//Activity上に画面を構成する要素(binding.root)を表示する
    }

    private fun selectIntent() = with(binding) {
        introductionButton.setOnClickListener { start(IntroduceActivity::class.java) }
        stopWatchButton.setOnClickListener { start(StopWatchActivity::class.java) }
        counterButton.setOnClickListener { start(CounterActivity::class.java) }//指定した遷移先に移動する
        calculatorButton.setOnClickListener { start(CalculatorActivity::class.java) }
        toDoListButton.setOnClickListener { start(ToDoListActivity::class.java) }
        timerButton.setOnClickListener { start(TimerActivity::class.java) }
    }

    private fun start(activity: Class<out Activity>) = startActivity(Intent(this, activity))
}