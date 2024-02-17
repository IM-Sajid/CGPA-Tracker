package com.example.project1

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue

@SuppressLint("InvalidColorHexValue")
@Composable
fun Grade(
    grade: TextFieldValue,
    onValueChange: (TextFieldValue) -> Unit,
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        TextField(
            value = grade,
            onValueChange = onValueChange,
            label = { Text(text = "Enter course grade") },
            modifier = Modifier
                .fillMaxWidth(),
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Next
            ),
            singleLine = true,

        )
    }
}

