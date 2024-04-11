package jp.ac.kyusanu.nicetomeetyou

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import jp.ac.kyusanu.nicetomeetyou.databinding.ActivityToDoListBinding
import org.json.JSONArray

class ToDoListActivity : AppCompatActivity() {

    private lateinit var binding: ActivityToDoListBinding
    private lateinit var adapter: ArrayAdapter<String>
    private lateinit var sharedPreferences: SharedPreferences
    private var toDoArrayList: ArrayList<String> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setup()
        setIntentFinish()
        setToDoList()
    }

    private fun setup() {
        binding = ActivityToDoListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        sharedPreferences = this.getPreferences(Context.MODE_PRIVATE)
        toDoArrayList = loadArrayList()
        adapter = ArrayAdapter(//Contextクラスのオブジェクトの指定、テキストのリソースの指定、中に入れる配列の指定
            this,
            android.R.layout.simple_list_item_1,
            toDoArrayList
        )
    }

    private fun setIntentFinish() {
        binding.todoReturnButton.setOnClickListener { finish() }
    }

    private fun setToDoList() {
        //リストビューにアダプターを代入
        binding.listView.adapter = adapter

        //＋ボタンを押した時の処理
        binding.addButton.setOnClickListener {
            //入力欄を作成　EditTextを当アクティビティに新規作成
            EditText(this).apply {
                AlertDialog.Builder(this@ToDoListActivity)//デフォルトのアラートダイアログテーマを使用するアラートダイアログのビルダーを作成
                    //アラートダイアログのレイアウト設定
                    .setTitle("リストの追加")//タイトル
                    .setMessage("予定の入力")//メッセージ
                    .setView(this)//作成したeditTextを使用して入力欄表示

                    //確定ボタン（アクションボタン）の作成    ダイアログのアイテムがクリックされたときに処理を実行出来る
                    .setPositiveButton("追加") { _, _ ->
                        //val newToDo = editText.text.toString()//入力欄で入力した文字列を保存する変数
                        //保存された文字列をアダプターの中に追加
                        adapter.add(this.text.toString())
                        saveArrayList(toDoArrayList)
                    }
                    //キャンセルボタン　押されたら（null）何もしない
                    .setNegativeButton("キャンセル", null).show()//上のダイアログを表示
            }

        }

        //リストビューをクリックした時の処理
        binding.listView.setOnItemClickListener { _, _, i, _ ->
            //アラートダイアログを表示
            AlertDialog.Builder(this)
                .setTitle("削除しますか？")//タイトル
                //確定ボタンの作成　押されたら選択したリストを削除
                .setPositiveButton("削除") { _, _ ->//使いたくない引数が指定される（ℹ︎）ので＿にリネーム
                    adapter.remove(adapter.getItem(i))//選択された配列のi番目のリストを削除
                    saveArrayList(toDoArrayList)
                }
                .setNegativeButton("キャンセル", null).show()//上のダイアログを表示
        }
    }

    private fun saveArrayList(arrayList: ArrayList<String>) {
        val sharedPreferences = this.getPreferences(Context.MODE_PRIVATE)
        with(sharedPreferences.edit()) {
            val jsonArray = JSONArray(arrayList)
            putString("todo", jsonArray.toString()).apply()
        }
    }

    private fun loadArrayList(): ArrayList<String> {
        val jsonArray = JSONArray(sharedPreferences.getString("todo", "[]"))
        val arrayList: ArrayList<String> = ArrayList()

        for (i in 0 until jsonArray.length()) {
            arrayList.add(jsonArray.get(i) as String)
        }
        return arrayList
    }
}