package io.github.takusan23.androidrustjni

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
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import io.github.takusan23.androidrustjni.ui.theme.AndroidRustJniTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AndroidRustJniTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    // Rust のカニさんが挨拶してくれる
                    Text(
                        text = greeting("Hello Android + JNI + Rust"),
                        modifier = Modifier.padding(innerPadding),
                        fontFamily = FontFamily.Monospace
                    )
                }
            }
        }
    }

    // Rust 側の実装
    private external fun greeting(message: String): String

    // Rust でビルドした共有ライブラリをロード
    companion object {
        init {
            System.loadLibrary("android_rust_jni")
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    AndroidRustJniTheme {
        Greeting("Android")
    }
}