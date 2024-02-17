package com.example.project1

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ComposableInferredTarget
import androidx.compose.runtime.ComposableOpenTarget
import androidx.compose.runtime.ComposableTarget
import androidx.compose.runtime.ComposableTargetMarker
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Cyan
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
@OptIn(ExperimentalMaterial3Api::class)
//@Composable
//fun Course(course: String, onValueChange: (TextFieldValue) -> Unit) {
//    Text(
//        value = TextFieldValue(course),
//        onValueChange = { onValueChange(it) },
//        modifier = Modifier.fillMaxWidth(),
//        colors = TextFieldDefaults.textFieldColors(
//            containerColor = Color.Transparent,
//            focusedIndicatorColor = Color.Transparent,
//            unfocusedIndicatorColor = Color.Transparent
//        ),
//        textStyle = TextStyle(fontSize = 20.sp)
//    )
//}
@Composable
fun Course(
    course: TextFieldValue,
    onValueChange: (TextFieldValue) -> Unit,
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        TextField(
            value = course,
            onValueChange = onValueChange,
            label = { Text(text = "Enter course code") },
            modifier = Modifier
                .fillMaxWidth(),
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Next
            ),
//            colors = TextFieldDefaults.textFieldColors(
//                focusedIndicatorColor = Color.Transparent,
//                unfocusedIndicatorColor = Color.Transparent,
//                containerColor = Color.Yellow
//            ),
            singleLine = true,

            )
    }
}
//@Preview(showBackground = true)
//@Composable
//fun UserInputTextFieldPreview() {
//    Project1Theme {
//        val textValue = remember { mutableStateOf(TextFieldValue()) }
//
//        Course(
//            value = textValue.value,
//            onValueChange = { newValue -> textValue.value = newValue },
//            label = "Enter your text here"
//        )
//    }
//}




