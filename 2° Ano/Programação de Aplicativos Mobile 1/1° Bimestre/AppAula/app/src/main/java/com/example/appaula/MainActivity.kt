package com.example.appaula

import android.os.Bundle
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
import com.example.appaula.ui.theme.AppAulaTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AppAulaTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    AppAula()
                }
            }
        }
    }
}

@Composable
fun AppAula() {
  Column(
      Modifier
          .fillMaxSize()
  ) {
      Row ( Modifier
          .fillMaxWidth()
          .padding(20.dp, 40.dp, 20.dp,20.dp),
          Arrangement.Start
      ){
Text("App Exemplo")
      }

      Row (
          Modifier
              .fillMaxWidth()
              .padding(20.dp),
          Arrangement.Center
      ){
          TextField(value = "Nome",
              onValueChange = {}
          )
      }

      Row (
          Modifier
              .fillMaxWidth()
              .padding(20.dp),
          Arrangement.Center
      ){
          TextField(value = "Endereço",
              onValueChange = {}
          )
      }

      Row (
          Modifier
              .fillMaxWidth()
              .padding(20.dp),
          Arrangement.Center
      ){
          TextField(value = "Bairro",
              onValueChange = {}
          )
      }

      Row (
          Modifier
              .fillMaxWidth()
              .padding(20.dp),
          Arrangement.Center
      ){
          TextField(value = "CEP",
              onValueChange = {}
          )
      }

      Row (
          Modifier
              .fillMaxWidth()
              .padding(20.dp),
          Arrangement.Center
      ){
          TextField(value = "Cidade",
              onValueChange = {}
          )
      }

      Row (
          Modifier
              .fillMaxWidth()
              .padding(20.dp),


          Arrangement.Center,
      ){
          TextField(value = "Estado",
              onValueChange = {}
          )
      }
Row (
    Modifier
        .padding(60.dp, 30.dp, 0.dp,0.dp),
    Arrangement.Center
){
    Column (
        Modifier
            .padding(0.dp, 0.dp, 60.dp,0.dp),


){
    Button(
        onClick = {}
    ) { Text("Cancelar") }
}

    Column (
        Modifier
            .padding(20.dp, 0.dp, 0.dp,0.dp),

    ){
        Button(
            onClick = {}
        ) { Text("Cadastrar") }
    } }


  }
}

@Preview
@Composable
fun ApppAulaPreview() {
    AppAulaTheme {
       AppAula()
    }
}