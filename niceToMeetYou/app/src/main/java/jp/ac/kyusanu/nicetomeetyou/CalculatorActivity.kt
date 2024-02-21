package jp.ac.kyusanu.nicetomeetyou

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import jp.ac.kyusanu.nicetomeetyou.databinding.ActivityCalculatorBinding
import kotlin.math.sqrt
import java.text.DecimalFormat

class CalculatorActivity : AppCompatActivity() {

    private  lateinit var binding : ActivityCalculatorBinding
    //小数点をまとめる
    private val decimalFormat = DecimalFormat("#.########")
    //新しく数値を入れていいかのフラグ
    private var clear = false
    //演算子を押した後に計算を行うかを判断するフラグ
    private var calculate = false
    //実行予定の演算子を保持しておく変数
    private var operator = "no" //operator = 演算子
    //小数点　decimal point
    private var decimal = false
    //数値を保持する変数,値
    private var value = 0.0

    override fun onCreate(savedInstanceState : Bundle?) {
        super.onCreate(savedInstanceState)

        setup()
        setOnClickListener()
    }

    private fun setup() {
        binding = ActivityCalculatorBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    private fun preview(previewText : String) {
        binding.preview.text = previewText
    }

    private fun setOnClickListener() {
        binding.calculatorReturnButton.setOnClickListener { finish() }

        //0
        binding.zero.setOnClickListener  { numberButtonAction(0) }

        //1
        binding.one.setOnClickListener   { numberButtonAction(1) }

        //2
        binding.two.setOnClickListener   { numberButtonAction(2) }

        //3
        binding.three.setOnClickListener { numberButtonAction(3) }

        //4
        binding.four.setOnClickListener  { numberButtonAction(4) }

        //5
        binding.five.setOnClickListener  { numberButtonAction(5) }

        //6
        binding.six.setOnClickListener   { numberButtonAction(6) }

        //7
        binding.seven.setOnClickListener { numberButtonAction(7) }

        //8
        binding.eight.setOnClickListener { numberButtonAction(8) }

        //9
        binding.nine.setOnClickListener  { numberButtonAction(9) }

        //calcButtonAction行き
        //+
        binding.addition.setOnClickListener { calculateButtonAction("+") }

        //-
        binding.subtraction.setOnClickListener { calculateButtonAction("-") }

        //×
        binding.multiplication.setOnClickListener { calculateButtonAction("×") }

        //÷
        binding.division.setOnClickListener { calculateButtonAction("÷") }

        //^
        binding.exponetion.setOnClickListener { calculateButtonAction("^") }

        //その他
        //√
        binding.sqrt.setOnClickListener { sqrtButtonAction() }

        //.
        binding.dot.setOnClickListener { dotButtonAction() }

        //+/-
        binding.state.setOnClickListener { plusMinusButtonAction() }

        //=
        binding.equal.setOnClickListener { equalButtonAction() }

        //AC
        binding.allClear.setOnClickListener { allClearButtonAction() }
    }

    //関数の宣言

    //数字ボタンを押した時の関数
    private fun numberButtonAction(num : Int) {
        //acが無効かつテキストが０以外であれば
        binding.result.text = if(binding.result.text.toString() != "0" && !clear) {//clear == false
            binding.result.text.toString() + num.toString()
        } else {
            clear = false
            decimal = false
            num.toString()
        }
        calculate = true
    }

    //計算を実行する際の演算子を確認する
    private fun calculation(operator : String) : Double {
        // else 以外の場所は全て演算子を一回は押したときに運ばれてくる場所
        decimal = true
        return when (operator) {
            "+" -> { value + binding.result.text.toString().toDouble() }
            "-" -> { value - binding.result.text.toString().toDouble() }
            "×" -> { value * binding.result.text.toString().toDouble() }
            "÷" -> { value / binding.result.text.toString().toDouble() }
            "^" -> {
                val x = binding.result.text.toString().toInt()
                val y = value
                for(i in 1 until x) {
                    value *= y
                }
                value//計算後のvalueを返す
                //最初に演算子を選択したときに数値が運ばれてくる場所
            } else -> { binding.result.text.toString().toDouble() }
        }
    }

    //計算の行う時の処理
    private fun calculateButtonAction(personalOperator : String) {
        if (calculate) { //calc == true
            value = calculation(operator)
            binding.result.text = decimalFormat.format(value).toString()
            clear = true
            calculate = false
        }
        operator = personalOperator
        preview(personalOperator)
    }

    //+/-が押されたとき値の正と負を入れ替え
    private fun plusMinusButtonAction() {
        if(binding.result.text.toString().toDouble() != 0.0) {//テキストが０以外であれば
            val change = -binding.result.text.toString().toDouble()
            binding.result.text = decimalFormat.format(change).toString()
        }
    }

    //.が押されたとき
    private fun dotButtonAction() {
        binding.result.text = if(!decimal) {
            binding.result.text.toString() + "."
        } else {
            binding.result.text
        }
        decimal = true
        calculate = true
    }

    //√ボタンを押した時の処理
    private fun sqrtButtonAction() {//sqrt関数　ルート計算する
        if(binding.result.text.toString().toDouble() > 0) {
            val sqrtNum: Double = sqrt(binding.result.text.toString().toDouble())
            binding.result.text = decimalFormat.format(sqrtNum).toString()
            clear = true
        }
    }

    //イコールボタンを押した時の関数
    private fun equalButtonAction() {
        if (calculate) { //calc == true
            value = calculation(operator)
            binding.result.text = decimalFormat.format(value).toString()
            preview("")
            clear = true
            calculate = false
            operator = "no" //演算子を未選択
        }
    }

    //ACを押した時の関数
    private fun allClearButtonAction() {
        binding.result.text = "0"
        preview("")
        value = 0.0
        clear = false
        decimal = false
        calculate = false
        operator = "no" //演算子を未選択
    }
}