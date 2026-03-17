package com.example.mvicounter

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

// 1. State. Я этот кусок понял примерно так: "Наш state хранится в виде data class и называется CounterState.
// По умолчанию значение этого  0 и хранится оно в виде целого числа.
data class CounterState(
    val count: Int = 0
)

// 2. Intent. Тут у нас получается используются "пустые" команды, которые передают факт нажатия.
sealed interface CounterIntent {
    data object Increment : CounterIntent
    data object Decrement : CounterIntent
    data object Reset : CounterIntent
}

class CounterViewModel : ViewModel() {

    // 3. Храним состояние.Создаётся приватная неизменяемая переменная, которая хранит ссылку на
    // изменяемый поток данных + создаётся начальное состояние.
    // На вторую строчку смотрит view. StateFlow это поток только для чтения а
    private val _state = MutableStateFlow(CounterState())
    val state: StateFlow<CounterState> = _state.asStateFlow()

    // 4. Reducer
    fun accept(intent: CounterIntent) {
        _state.update { currentState ->
            when (intent) {
                CounterIntent.Increment -> currentState.copy(count = currentState.count + 1)
                CounterIntent.Decrement -> currentState.copy(count = currentState.count - 1)
                CounterIntent.Reset -> currentState.copy(count = 0)
            }
        }
    }
}
