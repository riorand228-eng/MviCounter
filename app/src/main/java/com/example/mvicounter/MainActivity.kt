
package com.example.mvicounter

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel

//Создаётся экран через ComponenActivity. Через savedIstance "вспоминается" инфа которая была до закрытия.
//set content заставляет работать приложение через Jetpack, а не XML. Ну а дальше задаётся визуал и вызывается функция.
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                CounterScreen()
            }
        }
    }
}

@Composable
fun CounterScreen(viewModel: CounterViewModel = viewModel()) {
    val state by viewModel.state.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = state.count.toString(),
            style = MaterialTheme.typography.displayLarge
        )

        Spacer(modifier = Modifier.height(32.dp))

        Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
            Button(onClick = { viewModel.accept(CounterIntent.Decrement) }) {
                Text("−")
            }
            Button(onClick = { viewModel.accept(CounterIntent.Increment) }) {
                Text("+")
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = { viewModel.accept(CounterIntent.Reset) }) {
            Text("Сбросить")
        }
    }
}