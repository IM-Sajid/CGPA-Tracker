package com.example.project1

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.project1.ui.theme.Project1Theme
var clicked:Boolean = false
//@Composable
//fun CalculateButton(courses: List<CourseData>) {
//        Spacer(modifier = Modifier.padding(start = 50.dp))
//        Button(onClick = {
//                     clicked=true
//        }, modifier = Modifier.padding(start = 130.dp,top = 10.dp)) {
//            Text(text = "CALCULATE", textAlign = TextAlign.Center)
//            if(clicked==true) calculate(courses = courses)
//    }
//}


@Composable
fun calculate(courses: List<CourseData>){
    Column {
        val totalCredits = courses.sumOf { it.creditValue.text.toIntOrNull() ?: 0 }

        var totalGradePoints = 0.0
        courses.forEach { courseData ->
            val grade = courseData.gradeValue.text.toDoubleOrNull()
            val credit = courseData.creditValue.text.toIntOrNull()

            if (grade != null && credit != null) {
                totalGradePoints += grade * credit
            }
        }

        val cgpa:Double = if (totalCredits > 0) {
            totalGradePoints / totalCredits
        } else {
            0.0
        }
        show_result(result = cgpa)


//    Surface(
//        modifier = Modifier
//            .padding(16.dp)
//            .fillMaxWidth(),
//        color = Color.Gray,
//        //elevation = 4.dp
//    ) {
//        Column(
//            modifier = Modifier
//                .padding(16.dp)
//                .fillMaxWidth()
//                .wrapContentHeight(),
//            horizontalAlignment = Alignment.CenterHorizontally
//        ) {
//            Text(
//                text = "Total CGPA: %.2f".format(cgpa),
//                textAlign = TextAlign.Center,
//                color = Color.White
//            )
//        }
//    }
    }

}

