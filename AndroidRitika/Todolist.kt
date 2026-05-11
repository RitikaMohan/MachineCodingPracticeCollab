//Todo List
//Each todo item has:
//title
//completed state

//Implement:
//fun toggleTask(taskId: String): TodoList
//UI should recompose correctly.

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.window.ComposeViewport
import kotlinx.browser.document
import androidx.compose.foundation.layout.*
import androidx.compose.ui.unit.dp
import androidx.compose.ui.text.input.*
import androidx.compose.foundation.text.KeyboardOptions

@OptIn(ExperimentalComposeUiApi::class)
fun main() {
    ComposeViewport(document.body!!) {
        App()
    }
}

@Composable
fun App() {
    MaterialTheme {
        TodoScreen()
    }
}

data class Todo(
    val id: String,
    val title: String,
    val isCompleted: Boolean = false
)

class TodoViewModel {
    private val _todoList = mutableStateListOf<Todo>()
    val todoList: List<Todo> = _todoList
    
    init {
        _todoList.add(Todo("1", "Do Leetcode"))
        _todoList.add(Todo("2", "Do Machine Code", true))
    }
    
    fun toggleTask(taskId: String) {
        val index = _todoList.indexOfFirst { it.id == taskId }
        if (index != -1) {
            val item = _todoList[index]
            _todoList[index] = item.copy(isCompleted = !item.isCompleted)
        }   
    }  
}

@Composable
fun TodoScreen(){
    // Use remember instead of the viewModel() delegate
    val viewModel = remember { TodoViewModel() }
    Column{
        viewModel.todoList.forEach { task ->
            TodoItemRow(
                task = task,
                onToggle = { viewModel.toggleTask(task.id) }
            )
        }
    }
}

@Composable
fun TodoItemRow(task: Todo, onToggle: () -> Unit){
    Row{
        Checkbox(
            checked = task.isCompleted,
            onCheckedChange = { onToggle() }
        )
        Text(
            text = task.title,
            modifier = Modifier.padding(start = 8.dp)
        )
    }
}
