package com.example.project1

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun show_result(result: Double){
    Column {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Column(
                modifier = Modifier
                    .padding(16.dp)
            ) {
                Text(text = "Your CGPA ")
                //Spacer(modifier = Modifier.height(8.dp))
                Text(text = "%.2f".format(result), fontWeight = FontWeight.Bold)
                compliment(result = result)
            }
        }
    }

}
@Composable
fun compliment(result: Double){
    if(result>=3.8) Text(text = "WTF!!!")
    else if(result > 3.5) Text(text = "Shera.")
    else if(result > 3.0) Text(text = "Good Job")
    else if(result > 2.5) Text(text = "Keep Trying")
    else if(result > 2.0) Text(text = "Are ya Wining son?")
    else if(result > 0 && result <=2.00) Text(text = "CG doesn't matter")
}