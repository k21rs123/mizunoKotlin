package jp.ac.kyusanu.nicetomeetyou

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import jp.ac.kyusanu.nicetomeetyou.databinding.ActivityIntroduceBinding

class IntroduceActivity : AppCompatActivity() {

    private lateinit var binding : ActivityIntroduceBinding
    override fun onCreate(savedInstanceState : Bundle?) {
        super.onCreate(savedInstanceState)

        setup()
        setOnClickListeners()
    }


    private fun setup() {
        binding = ActivityIntroduceBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    private fun setOnClickListeners() {
        binding.introduceReturnButton.setOnClickListener { finish() }
    }
}