package com.example.project1

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun SaveResult(onSaveClick: () -> Unit) {
    Column {
        Button(
            onClick = onSaveClick, // Call the provided callback when the button is clicked
            modifier = Modifier
                .padding(16.dp)
                .align(Alignment.CenterHorizontally)
                .fillMaxWidth()
        ) {
            Text(text = "SAVE", textAlign = TextAlign.Center, color = Color.White)
        }
    }
}

@Composable
fun SaveDialog(onDismiss: () -> Unit) {
    val year_sem = arrayOf("1-1", "1-2", "2-1", "2-2", "3-1","3-2","4-1","4-2")
    AlertDialog(
        onDismissRequest = { onDismiss() },
        title = {
            Text(
                text = "Select Semester",
                style = MaterialTheme.typography.bodyMedium,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )
        },
        text = {
            // Your 8 buttons or any other content goes here
            Column {
                repeat(8) { index ->
                    Button(
                        onClick = {
                            handleButtonClick(index + 1) // Pass the button index to distinguish
                            onDismiss()
                        },
                        modifier = Modifier
                            .padding(8.dp)
                            .fillMaxWidth()
                    ) {
                        Text(text = year_sem[index])
                    }
                }
            }
        },
        confirmButton = {
            // Empty confirm button
        },
        dismissButton = {
            // Empty dismiss button
        },
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth()
    )
}
fun handleButtonClick(buttonIndex: Int) {
    // Implement different tasks based on the buttonIndex
    when (buttonIndex) {
        1 -> { /* Task for Button 1 */ }
        2 -> { /* Task for Button 2 */ }
        3 -> { /* Task for Button 2 */ }
        4 -> { /* Task for Button 2 */ }
        5 -> { /* Task for Button 2 */ }
        6 -> { /* Task for Button 2 */ }
        7 -> { /* Task for Button 2 */ }
        8 -> { /* Task for Button 2 */ }
    }
}
