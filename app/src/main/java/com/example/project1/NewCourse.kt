package com.example.project1

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp


@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun AddNewCourse(): List<CourseData> {
    var courses by remember { mutableStateOf(listOf<CourseData>()) }
    val keyboardController = LocalSoftwareKeyboardController.current

    Column(
        modifier = Modifier.fillMaxHeight(),
        verticalArrangement = Arrangement.Center
    ) {
        // Display existing courses
        courses.forEachIndexed { index, courseData ->
            Course(
                course = courseData.courseValue,
                onValueChange = { newValue ->
                    courses = courses.toMutableList().apply {
                        this[index] = courseData.copy(courseValue = newValue)
                    }
                },
            )

            Grade(
                grade = courseData.gradeValue,
                onValueChange = { newValue ->
                    courses = courses.toMutableList().apply {
                        this[index] = courseData.copy(gradeValue = newValue)
                    }
                },
            )

            Credit(
                credit = courseData.creditValue,
                onValueChange = { newValue ->
                    courses = courses.toMutableList().apply {
                        this[index] = courseData.copy(creditValue = newValue)
                    }
                },
            )

            IconButton(
                onClick = {
                    keyboardController?.hide()
                    if (index in courses.indices) {
                        courses = courses.toMutableList().apply {
                            removeAt(index)
                        }
                    }
                },
                modifier = Modifier.align(Alignment.End).padding(8.dp)
            ) {
                Icon(imageVector = Icons.Default.Delete, contentDescription = "Remove Course")
            }

        }

        // Floating Action Button to add a new course
        FloatingActionButton(
            onClick = {
                courses = courses + CourseData(
                    courseValue = TextFieldValue(""),
                    gradeValue = TextFieldValue(),
                    creditValue = TextFieldValue()
                )
            },
            modifier = Modifier.padding(16.dp).align(Alignment.CenterHorizontally).fillMaxWidth()
        ) {
            Text(text = "ADD COURSE", textAlign = TextAlign.Center, color = Color.Black)
        }
    }
    return courses
}


