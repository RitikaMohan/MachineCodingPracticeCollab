//Login Form

//Implement:

//Email field
//Password field
//Login button enabled only when both are valid
//Show password visibility toggle

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.window.ComposeViewport
import kotlinx.browser.document
import androidx.compose.foundation.layout.*
import androidx.compose.ui.unit.dp
import androidx.compose.ui.text.input.*
import androidx.compose.foundation.text.KeyboardOptions

@OptIn(ExperimentalComposeUiApi::class)
fun main() {
    ComposeViewport(document.body!!) {
        App()
    }
}

@Composable
fun App() {
    MaterialTheme {
        var email by remember {mutableStateOf("")}
        var password by remember {mutableStateOf("")}
        LoginForm(
        email = email,
        password = password,
        onEmailChange = {email = it},
        onPasswordChange = {password = it}
        )
    }
}

@Composable
fun LoginForm(email: String,
             password: String,
             onEmailChange: (String) -> Unit,
             onPasswordChange: (String) -> Unit){
    
    val isEnabled = email.isNotEmpty() && password.isNotEmpty()
    var passwordVisible by remember{mutableStateOf(false)}
    
    Column{
        TextField(
            value = email,
            onValueChange = onEmailChange,
            label = {Text("Enter your email")},
            placeholder = {Text("abc@example.com")}
        )
        
        Spacer(modifier = Modifier.width(10.dp))
        
        TextField(
            value = password,
            onValueChange = onPasswordChange,
            label = {Text("Enter your password")},
            placeholder = {Text("password")},
            visualTransformation = if(passwordVisible)
            	VisualTransformation.None else 
            					PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(
                					keyboardType = KeyboardType.Password),
            trailingIcon = {
                //val image = if(passwordVisible)
               // 				Icons.Filled.Visibility
                //			else
                //				Icons.Filled.VisibilityOff
               // val description = if(passwordVisible)
                //					"Hide Password"
                //				  else
                //					"Show Password"
               // IconButton(onClick = {passwordVisible = !passwordVisible}){
               //     Icon(imageVector = image, description)
               // }
                val label = if (passwordVisible) "Hide" else "Show"
    TextButton(onClick = { passwordVisible = !passwordVisible }) {
        Text(label)
    }
            }
        )
        
        Spacer(modifier = Modifier.width(10.dp))
        
        Button(onClick = {},
              enabled = isEnabled){
            Text("Login")
        }
    }
}