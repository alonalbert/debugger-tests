package com.example.debuggertests

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyGridScope
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.debuggertests.ui.theme.DebuggerTestsTheme
import dalvik.system.DexFile
import dalvik.system.PathClassLoader

class MainActivity : ComponentActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    enableEdgeToEdge()
    setContent {
      DebuggerTestsTheme {
        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
          App(
            modifier = Modifier.padding(innerPadding)
          )
        }
      }
    }
  }

  @Composable
  fun App(modifier: Modifier = Modifier) {
    LazyColumn(modifier = modifier.padding(8.dp)) {
      getTestCases().sorted().forEach {
        button(it.substringBefore(".")) {
          runMain(it)
        }
      }
    }
  }

  private fun runMain(className: String) {
    val testClass = classLoader.loadClass(className)
    try {
      testClass.getDeclaredMethod("main", Array<String>::class.java).invoke(null, emptyArray<String>() as Any)
    } catch (_: Exception) {
      testClass.getDeclaredMethod("main").invoke(null)
    }
  }
}

@Composable
fun SimpleTextButton(text: String, onClick: () -> Unit) {
  Button(onClick = onClick) { Text(text) }
}

private fun LazyListScope.button(text: String, onClick: () -> Unit) {
  item { SimpleTextButton(text, onClick) }
}

