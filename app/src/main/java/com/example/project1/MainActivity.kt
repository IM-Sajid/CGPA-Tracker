package com.example.project1


import android.content.Intent
import com.google.firebase.FirebaseApp
import android.graphics.Color.rgb
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.project1.ui.theme.Project1Theme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Project1Theme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    LoginScreen()
                }
            }
        }
    }
}
@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun LoginScreen() {
    val context = LocalContext.current
    val viewModel = remember { SignUpViewModel(context) }
    var user by remember { mutableStateOf(User()) }
    var isLoginEnabled by remember { mutableStateOf(false) }
    var passwordVisibility by remember { mutableStateOf(false) }
    var  isLoading by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .background(Color.White),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Card(colors = CardDefaults.cardColors(Color.White), modifier = Modifier.fillMaxWidth()) {
            Image(
                painter = painterResource(id = R.drawable.calculate),
                contentDescription = null,
                modifier = Modifier
                    .size(150.dp)
                    .fillMaxSize()
                    .clip(MaterialTheme.shapes.small)
                    .align(Alignment.CenterHorizontally)
            )
        }
        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = user.email.text,
            onValueChange = {
                user = user.copy(email = TextFieldValue(it))
                isLoginEnabled = it.isNotBlank() && user.password.text.isNotBlank()
            },
            label = { Text(stringResource(id = R.string.email)) },
            singleLine = true,
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            leadingIcon = { Icon(Icons.Default.Email, null) },
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Email,
                imeAction = ImeAction.Next
            ),
        )

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = user.password.text,
            onValueChange = {
                user = user.copy(password = TextFieldValue(it))
                isLoginEnabled = user.email.text.isNotBlank() && it.isNotBlank()
            },
            label = { Text(stringResource(id = R.string.password)) },
            singleLine = true,
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            leadingIcon = { Icon(Icons.Default.Lock, null) },
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Password,
                imeAction = ImeAction.Done
            ),
            visualTransformation = if (passwordVisibility) {
                VisualTransformation.None
            } else {
                PasswordVisualTransformation()
            },
            trailingIcon = {
                IconButton(
                    onClick = {
                        passwordVisibility = !passwordVisibility
                    }
                ) {
                    val icon = if (passwordVisibility) {
                        painterResource(id = R.drawable.visibility)
                    } else {
                        painterResource(id = R.drawable.visibility_off)
                    }
                    Icon(
                        painter = icon,
                        contentDescription = if (passwordVisibility) "Show Password" else "Hide Password"
                    )
                }
            }
        )

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            if (isLoading) {
                CircularProgressIndicator() // Show loading indicator when isLoading is true
            } else {
                FloatingActionButton(
                    onClick = {
                        if (isLoginEnabled) {
                            viewModel.loginUser(user)
                            user = User() // Set isLoading to true when login is initiated
                        } else {
                            Toast.makeText(context, "Fields can't be empty", Toast.LENGTH_SHORT).show()
                        }
                    },
                    modifier = Modifier
                        .width(200.dp)
                        .height(56.dp),
                    containerColor = Color(rgb(173, 216, 230)),
                ) {
                    Text(text = stringResource(id = R.string.login))
                }
            }
        }
        Spacer(modifier = Modifier.height(16.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = stringResource(id = R.string.dont_have_account),
                color = Color.Gray
            )
            Text(
                text = " " + stringResource(id = R.string.sign_up),
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier.clickable {
                    // Handle sign-up navigation or action here
                    // You can use navigation components or start a new activity
                    // For example:
                    // context.startActivity(Intent(context, SignUpActivity::class.java))
                    context.startActivity(Intent(context, SIgnUpActivity::class.java))
                }
            )
        }
    }
}



@Preview(showBackground = true)
@Composable
fun LoginScreenPreview() {
    Project1Theme {
        LoginScreen()
    }
}
