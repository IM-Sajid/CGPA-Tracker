package com.example.project1

import android.content.Intent
import android.graphics.Color.rgb
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthInvalidUserException
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import com.google.firebase.firestore.FirebaseFirestore



@Composable
fun SignUpScreen(onSignUpClicked: (String, String, String) -> Unit) {
    val context = LocalContext.current
    val viewModel = remember { SignUpViewModel(context) }

    var user by remember { mutableStateOf(User()) } // Initialize with an empty User object
    var passwordVisibility by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .background(Color.White),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = "Sign Up Here!",
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.CenterHorizontally)
                .padding(start = 100.dp, top = 100.dp),
            style = MaterialTheme.typography.h4
        )

        OutlinedTextField(
            value = user.name,
            onValueChange = {
                user = user.copy(name = it)
            },
            label = { Text(stringResource(id = R.string.name)) },
            singleLine = true,
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            leadingIcon = { Icon(Icons.Default.AccountCircle, null) },
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Email,
                imeAction = ImeAction.Next
            ),
        )

        Spacer(modifier = Modifier.height(10.dp))

        OutlinedTextField(
            value = user.email,
            onValueChange = {
                user = user.copy(email = it)
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

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = user.password,
            onValueChange = {
                user = user.copy(password = it)
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

        Spacer(modifier = Modifier.height(10.dp))

        FloatingActionButton(
            onClick = {
                val name = user.name.text
                val email = user.email.text
                val password = user.password.text

                if (password.length < 6 && password.isNotEmpty()) {
                    Toast.makeText(context, "Password must be 6 characters or longer", Toast.LENGTH_SHORT).show()
                } else if (name.isNotBlank() && email.isNotBlank() && password.isNotEmpty()) {
                    viewModel.createUser(user)
                } else {
                    Toast.makeText(context, "Please fill all fields", Toast.LENGTH_SHORT).show()
                }
            },
            modifier = Modifier
                .width(200.dp)
                .align(Alignment.CenterHorizontally)
                .height(56.dp),
            containerColor = Color(rgb(173, 216, 230))
        ) {
            Text(text = stringResource(id = R.string.signup))
        }
    }
}





