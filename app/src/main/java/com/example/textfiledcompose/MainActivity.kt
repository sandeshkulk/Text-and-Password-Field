package com.example.textfiledcompose

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Email
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.tooling.preview.Preview
import com.example.textfiledcompose.ui.theme.TextFiledComposeTheme
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TextFiledComposeTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    Greeting()
                }
            }
        }
    }
}

@Composable
fun Greeting(){
    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center) {
        //for typing text
        var  text by remember {
            mutableStateOf("Type Here") }
        OutlinedTextField(
            value = text, onValueChange = {newText->
                text=newText
            },
            label = { Text(text = "Email")}, //shows title over tct field
            // singleLine = true     //hzl scrolling occurs ,max line do not work
            //enabled = false,  //disabled input
            //readOnly = true  //can only read or copy

            leadingIcon = {     //Starting of txt field
                IconButton(onClick = {  Log.d("Email","Add a valid Email address")}) {
                    Icon(imageVector = Icons.Default.Email, contentDescription = "Email Icon")
                }
            },
            //Keyboard option and action
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email,
                imeAction = ImeAction.Go),
            keyboardActions = KeyboardActions(
                onGo = {
                    Log.d("ImeAction","Go")
                }
            )
        )
        Spacer(modifier = Modifier.padding(5.dp))

        //password filed
        var password by rememberSaveable{ mutableStateOf("") }
        var passowrdvisibility by remember { mutableStateOf(false)}
        var icon =  if(passowrdvisibility)
            painterResource(id = R.drawable.design_ic_visibility)
        else
            painterResource(id = R.drawable.design_ic_visibility_off)

        OutlinedTextField(value = password, onValueChange = {
            password=it
        },
            placeholder = { Text(text = "Type Here")},
            label = {Text(text = "Password")},
            trailingIcon ={
                IconButton(onClick = {
                    passowrdvisibility=!passowrdvisibility
                }) {
                    Icon(painter = icon,
                        contentDescription ="Visibility Icon" )
                }
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Password
            ),
            //    visualTransformation = if (passowrdvisibility) VisualTransformation.None
            //  else VisualTransformation()

        )
    }
}
@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    TextFiledComposeTheme {
        Greeting()
    }
}