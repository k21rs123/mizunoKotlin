package jp.ac.kyusanu.myapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import jp.ac.kyusanu.myapp.ui.TextView
import jp.ac.kyusanu.myapp.ui.theme.MyAppTheme


val text = TextView("aaa")
// MainActivity クラスを宣言し、ComponentActivity を継承します。
class MainActivity : ComponentActivity() {




    // onCreate メソッドをオーバーライドしています。これはアクティビティが作成されたときに呼び出されるメソッドです。
    /* アクセシビリティとはソフトウェアやウェブサイト、デバイスなどが、
    身体的、認知的、または感覚的な障害を持つ人々に対してどれだけ利用しやすいかを示す性質です。
    つまり、アクセシビリティが高いとは、障害を持つ人々が同じ情報や機能を同じように利用できるように設計されていることを意味します。
    例えば、画面リーダーを使用する視覚障害者のために、適切なラベル付けやテキストの代替手段を提供することが、アクセシビリティを高める方法の一つです.
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        // 親クラスの onCreate メソッドを呼び出しています。
        super.onCreate(savedInstanceState)
        // enableEdgeToEdge() 関数を呼び出しています。この関数は、画面の端から端までコンテンツを表示するためのシステムUIの設定を行います。
        enableEdgeToEdge()
        // コンテンツを設定します。
        setContent {
            // MyAppTheme を使用してテーマを適用します。
            MyAppTheme {
                // Scaffold を作成し、画面全体を埋めるように修飾子を設定します。また、内部の余白を innerPadding として取得します。

                MainScaffold()
            }
        }
    }// onCreate メソッドの終了。
}

@Composable
fun MainScaffold() {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {

        },
        bottomBar = {
            // ボトムバーのコンテンツ
        },
        floatingActionButton = {
            // フローティングアクションボタンのコンテンツ
        },
        content = { innerPadding ->
            // コンテンツの余白を取得します。
            val paddingValues = Modifier.padding(innerPadding)
            text.renderTextView()
        }
    )
}

// プレビューを設定し、背景を表示するように指定します。
@Preview(showBackground = true)
// GreetingPreview 関数を宣言し、@Composable アノテーションを付けます。
@Composable
// GreetingPreview 関数の中身を表示します。
fun GreetingPreview() {
    // MyAppTheme を使用してテーマを適用します。
    MyAppTheme {
        MainScaffold()
    }
}