package jp.ac.kyusanu.nicetomeetyou

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import jp.ac.kyusanu.nicetomeetyou.databinding.ActivityCounterBinding

class CounterActivity : AppCompatActivity() {

    private lateinit var binding : ActivityCounterBinding
    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var editor : SharedPreferences.Editor
    private var count = 0

    override fun onCreate(savedInstanceState : Bundle?) {
        super.onCreate(savedInstanceState)

        setup()
        setIntentFinish()
        setCounter()
    }

    private fun setup() {
        binding = ActivityCounterBinding.inflate(layoutInflater)
        setContentView(binding.root)
        sharedPreferences = getSharedPreferences("Datastore" , Context.MODE_PRIVATE)
        editor = sharedPreferences.edit()
        count = sharedPreferences.getInt("Count" , 0)
    }

    private fun setIntentFinish() {
        binding.counterReturnButton.setOnClickListener { finish() }
    }

    private fun setCounter() {
        binding.countText.text = count.toString()
        reload(count)

        binding.plusButton.setOnClickListener {
            count ++
            editor.putInt("Count" , count)
            editor.apply()
            binding.countText.text = count.toString()
            reload(count)
        }

        binding.minusButton.setOnClickListener {
            count --
            editor.putInt("Count" , count)
            editor.apply()
            binding.countText.text = count.toString()
            reload(count)
        }

        binding.clearButton.setOnClickListener {
            count = 0
            editor.putInt("Count" , count)
            editor.apply()
            binding.countText.text = count.toString()
            reload(count)
        }
    }

    //カウントの値によってカウントテキストの色を変更
    private fun reload(count : Int) {
        if(count < 0) {binding.countText.setTextColor(resources.getColor(R.color.red,theme))
        } else {
            binding.countText.setTextColor(resources.getColor(R.color.white,theme))
        }
    }
}