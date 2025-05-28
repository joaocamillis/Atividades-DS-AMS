package com.example.appaula_4

import android.os.Bundle
import android.renderscript.Sampler.Value
import android.widget.NumberPicker.OnValueChangeListener
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.appaula_4.ui.theme.AppAula_4Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AppAula_4Theme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    appLogin()
                }
            }
        }
    }
}

@Composable
fun appLogin(){
    Column (
        Modifier
        .fillMaxSize()

    ){
        Row(
            Modifier
                .fillMaxWidth()
                .padding(50.dp),
            Arrangement.Center
        ) {
            Text("App Login")
        }

        Row(
            Modifier
                .fillMaxWidth(),
            Arrangement.Center
        ) {
            TextField(
                "Usuário: ",
                onValueChange = {}
            )
        }
        Row (
            Modifier
                .fillMaxWidth()
                .padding(20.dp),
            Arrangement.Center
        ){
            TextField(
                "Senha: ",
                onValueChange = {}
            )
        }
        Row (
            Modifier
                .fillMaxWidth(),
            Arrangement.Center
        ){
            Button(
                onClick = {}

            ) {
                Text("Cadastrar")
            }
        }
    }


}

private fun Any.TextField(s: String, OnValueChange: () -> Unit) {

}


@Preview
@Composable
fun AppAulaPreview(){
    appLogin()
}