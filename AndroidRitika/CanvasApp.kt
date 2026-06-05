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
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.CompositingStrategy
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.dp
@OptIn(ExperimentalComposeUiApi::class)
fun main() {
    ComposeViewport(document.body!!) {
        App()
    }
}

@Composable
fun App() {
    MaterialTheme {
        // State to track which tool is currently selected
        var currentTool by remember { mutableStateOf(DrawTool.PENCIL) }
        Column(modifier = Modifier.fillMaxSize()) {
            // Control panel layout
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            ) {
                Button(
                    onClick = { currentTool = DrawTool.PENCIL },
                    modifier = Modifier.padding(end = 8.dp),
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = if (currentTool == DrawTool.PENCIL) Color.DarkGray else Color.LightGray
                    )
                ) {
                    Text("Pencil", color = Color.White)
                }

                Button(
                    onClick = { currentTool = DrawTool.ERASER },
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = if (currentTool == DrawTool.ERASER) Color.DarkGray else Color.LightGray
                    )
                ) {
                    Text("Eraser", color = Color.White)
                }
            }

            // Interactive canvas area
            DrawableCanvas(
                currentTool = currentTool,
                modifier = Modifier.weight(1f)
            )
        }
    }
}

// Define the tools available to the user
enum class DrawTool {
    PENCIL, ERASER
}

// Data class to store information about each individual stroke line
data class LinePath(
    val path: Path,
    val tool: DrawTool,
    val color: Color = Color.Black,
    val strokeWidth: Float = 10f
)

@Composable
fun DrawableCanvas(
    currentTool: DrawTool,
    modifier: Modifier = Modifier) {
    // 1. Maintain a list of paths representing completed and active lines
    val lines = remember { mutableStateListOf<LinePath>() }

    Canvas(
        modifier = modifier
            .fillMaxSize()
            .background(Color.White) // Gives the canvas a clean base layer
            // CRITICAL: Isolates transparent clearing so it doesn't break the background
            .graphicsLayer(compositingStrategy = CompositingStrategy.Offscreen)
            .pointerInput(currentTool) { // Restart tracking when tool changes
                detectDragGestures(
                    onDragStart = { startOffset ->
                        val newPath = Path().apply {
                            moveTo(startOffset.x, startOffset.y)
                        }
                        // Assign tool properties (Eraser lines usually look better thicker)
                        val width = if (currentTool == DrawTool.ERASER) 40f else 10f
                        lines.add(LinePath(path = newPath, tool = currentTool, strokeWidth = width))
                    },
                    onDrag = { change, _ ->
                        val currentPosition = change.position
                        lines.lastOrNull()?.path?.lineTo(currentPosition.x, currentPosition.y)
                        
                        val lastLine = lines.removeAt(lines.lastIndex)
                        lines.add(lastLine)
                    }
                )
            }
    ) {
        lines.forEach { linePath ->
            // If it is an eraser, swap the blend mode to clear pixels down to white canvas base
            val isEraser = linePath.tool == DrawTool.ERASER
            
            drawPath(
                path = linePath.path,
                color = if (isEraser) Color.Transparent else linePath.color,
                style = Stroke(
                    width = linePath.strokeWidth,
                    cap = StrokeCap.Round,
                    join = StrokeJoin.Round
                ),
                blendMode = if (isEraser) BlendMode.Clear else BlendMode.SrcOver
            )
        }
    }
}