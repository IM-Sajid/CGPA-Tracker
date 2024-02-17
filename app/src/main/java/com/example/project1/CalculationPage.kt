package com.example.project1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.example.project1.ui.theme.Project1Theme


class CalculationPage : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Project1Theme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background

                ) {
                    Column (){
                        CGPA()
                    }
                }
            }
        }
    }
}

@Composable
fun CGPA(){
    var courses by remember { mutableStateOf(listOf<CourseData>()) }
    var result by remember { mutableStateOf(Double) }
    var showDialog by remember { mutableStateOf(false) }
    LazyColumn(){
        item {
            Column(modifier = Modifier
                .fillMaxSize()
                .padding(10.dp)) {
                Text(text = "CGPA Calculator\n", modifier = Modifier.fillMaxWidth(),
                    style = TextStyle(fontSize = 25.sp,
                        textAlign = TextAlign.Center,
                        color = Color.Black)
                )
                courses=AddNewCourse()
                calculate(courses = courses)
                SaveResult{showDialog=true}
            }
        }
    }
    //Connect SaveResult to SaveDialogue
    if (showDialog) {
        SaveDialog(
            onDismiss = {
                showDialog = false
            }
        )
    }
}

data class CourseData(
    var courseValue: TextFieldValue = TextFieldValue(""),
    var gradeValue: TextFieldValue = TextFieldValue("0.0"),
    var creditValue: TextFieldValue = TextFieldValue("0.0")
)
@Preview(showBackground = true)
@Composable
fun CalculationPagePreview() {
    Project1Theme {
        CGPA()
    }
}

