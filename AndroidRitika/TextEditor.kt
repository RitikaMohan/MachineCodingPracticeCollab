/* Problem: Undo/Redo Mini Text Editor
Problem Statement
You are required to implement a mini text editor screen using Jetpack Compose.
The editor should support the following features:
A TextField where the user can type freely.
An Undo button that restores the text to its immediately previous state.
A Redo button that reapplies a change that was undone.
Undo and Redo should behave like a history navigation system:
Every time the text changes, the current state should be saved.
Undo moves one step backward in the history (if available).
Redo moves one step forward in the history (if available).
If Undo or Redo cannot be performed (e.g., there is no older or newer state), the corresponding button should be disabled.
The solution should be efficient, and the history must remain consistent after multiple undo/redo actions.

Example Scenarios
Scenario 1: Simple Typing and Undo/Redo
User types "h" → editor shows h.
User types "he" → editor shows he.
User types "hel" → editor shows hel.
User types "hell" → editor shows hell.
User types "hello" → editor shows hello.
Undo actions:
Press Undo → editor goes back to hell.
Press Undo again → editor goes back to hel.
Redo actions:
Press Redo → editor goes forward to hell.
Press Redo again → editor goes forward to hello.
Scenario 2: New Input After Undo (Redo Reset)
Starting from "hello", press Undo → editor shows "hell".
Now type "hello!" (instead of pressing Redo).
The Redo history should be cleared, because a new edit invalidates the redo path.
From here, pressing Redo should do nothing, but Undo should take the editor back step by step ("hell" → "hel" → …).
*/

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
        TextEditor()
        
    }
}

@Composable
fun TextEditor(){
    
    var currentText by remember{mutableStateOf("")}
    
    val undoStack = remember{mutableListOf<String>()}
    val redoStack = remember{mutableListOf<String>()}
    
    
    Column{
        TextField(
        value = currentText,
        onValueChange = {newText ->
        	undoStack.add(currentText)
        	currentText = newText
        	redoStack.clear()
        },
        placeholder = {(Text("Enter your text"))},
        label = {(Text("input here"))})
        
        Row{
            Button(onClick = {
                	if(undoStack.isNotEmpty()){
                        redoStack.add(currentText)
                		currentText = undoStack.removeLast()
                    }
            	},
                 	enabled = undoStack.isNotEmpty()
            ){
                Text("Undo")
            }

            Button(onClick = {
                    if(redoStack.isNotEmpty()){
                        undoStack.add(currentText)
                        currentText = redoStack.removeLast()
                    }
            	},
                   enabled = redoStack.isNotEmpty()
            ){
                Text("Redo")
            }
    	}
    }
    
    
}