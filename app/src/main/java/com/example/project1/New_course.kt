package com.example.project1

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun New_course() {
    var courses by remember { mutableStateOf(listOf<String>()) }
    var courseval by remember { mutableStateOf(TextFieldValue()) }
    var credit by remember { mutableStateOf(listOf<Double>()) }
    var creditval by remember { mutableStateOf(TextFieldValue()) }
    var grade by remember { mutableStateOf(listOf<Double>()) }
    var gradeval by remember { mutableStateOf(TextFieldValue()) }

    Spacer(modifier = Modifier.padding(start = 50.dp))

    val clickedState = remember { mutableStateOf(false) }

    // Display the list of courses
//    courses.forEach { course ->
//        Text(text = course)
//    }

    if (clickedState.value) {
            Course(course = courseval, onValueChange = { newValue -> courseval = newValue })
            Spacer(modifier = Modifier.padding(top = 5.dp))
            Grade(grade = gradeval) { newValue -> gradeval = newValue }
        Spacer(modifier = Modifier.padding(top = 5.dp))
            Credit(credit = creditval, onValueChange = { newValue -> creditval = newValue })
            Spacer(modifier = Modifier.padding(top = 5.dp))

        // Add the entered course, credit, and grade to the respective lists
        val newCourse = courseval.text
        val newCredit = creditval.text.toDoubleOrNull()
        val newGrade = gradeval.text.toDoubleOrNull()

        if (newCourse.isNotEmpty() && newCredit != null && newGrade != null) {
            courses = listOf(newCourse) + courses
            credit = listOf(newCredit) + credit
            grade = listOf(newGrade) + grade
        }

        // Reset values but do not set clickedState back to false
//        courseval = TextFieldValue()
//        creditval = TextFieldValue()
//        gradeval = TextFieldValue()
    }

    FloatingActionButton(
        onClick = {
            clickedState.value = true
        },
        modifier = Modifier
            .padding(start = 130.dp, top = 10.dp)
            .width(120.dp)
    ) {
        Text(text = "ADD COURSE", textAlign = TextAlign.Center, color = Color.White)
    }
}
data class info(
    val code: String,
    val credit: Double,
    val grade:Double ,
)







