package com.joaopedro.geovista.pages

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.joaopedro.geovista.AuthState
import com.joaopedro.geovista.AuthViewModel
import androidx.compose.foundation.clickable

@Composable
fun HomePage(
    navController: NavController,
    authViewModel: AuthViewModel
) {

    val authState by authViewModel.authState.collectAsState()

    val verdeEscuro = Color(0xFF1B5E4B)
    val verdeMedio = Color(0xFF2E7D5B)
    val azulGeo = Color(0xFF1F6F8B)
    val fundo = Color(0xFFF4F7F5)

    LaunchedEffect(authState) {

        if (authState is AuthState.Unauthenticated) {

            navController.navigate("login") {

                popUpTo("home") {
                    inclusive = true
                }
            }
        }
    }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(fundo),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        item {

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        brush = Brush.verticalGradient(
                            colors = listOf(
                                verdeEscuro,
                                azulGeo
                            )
                        )
                    )
                    .padding(
                        start = 24.dp,
                        end = 24.dp,
                        top = 45.dp,
                        bottom = 30.dp
                    )
            ) {

                Text(
                    text = "GEOVISTA",
                    color = Color.White,
                    fontSize = 32.sp,
                    fontWeight = FontWeight.Bold
                )

                Spacer(
                    modifier = Modifier.height(6.dp)
                )

                Text(
                    text = "Geografia Humana Vista de Cima",
                    color = Color.White,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.SemiBold
                )

                Spacer(
                    modifier = Modifier.height(10.dp)
                )

                Text(
                    text = "Fotografias aéreas mostrando padrões de assentamento, agricultura e uso da terra.",
                    color = Color.White.copy(alpha = 0.85f),
                    fontSize = 14.sp
                )
            }
        }

        item {

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp)
            ) {

                Text(
                    text = "Explore o território",
                    color = verdeEscuro,
                    fontSize = 25.sp,
                    fontWeight = FontWeight.Bold
                )

                Text(
                    text = "Observe como as atividades humanas transformam e organizam o espaço geográfico.",
                    color = Color.DarkGray,
                    fontSize = 14.sp,
                    modifier = Modifier.padding(top = 5.dp)
                )

                Spacer(
                    modifier = Modifier.height(22.dp)
                )

                GeoCard(
                    emoji = "🏙️",
                    titulo = "Assentamentos Urbanos",
                    descricao = "Vista de cima, as cidades revelam diferentes padrões de ocupação, ruas, bairros e concentração de construções.",
                    cor = verdeMedio,
                    onClick = {
                        navController.navigate("detail/assentamentos")
                    }
                )

                Spacer(
                    modifier = Modifier.height(15.dp)
                )

                GeoCard(
                    emoji = "🌾",
                    titulo = "Agricultura",
                    descricao = "As fotografias aéreas evidenciam plantações, divisão de propriedades e diferentes formas de organização da produção agrícola.",
                    cor = verdeMedio,
                    onClick = {
                        navController.navigate("detail/agricultura")
                    }
                )

                Spacer(
                    modifier = Modifier.height(15.dp)
                )

                GeoCard(
                    emoji = "🗺️",
                    titulo = "Uso da Terra",
                    descricao = "A paisagem permite identificar áreas urbanizadas, rurais, agrícolas, vegetadas e outros usos do território.",
                    cor = azulGeo,
                    onClick = {
                        navController.navigate("detail/uso_da_terra")
                    }
                )

                Spacer(
                    modifier = Modifier.height(15.dp)
                )

                GeoCard(
                    emoji = "🏘️",
                    titulo = "Ocupação Rural",
                    descricao = "A distribuição de propriedades, estradas e áreas produtivas ajuda a compreender a organização do espaço rural.",
                    cor = verdeMedio,
                    onClick = {
                        navController.navigate("detail/ocupacao_rural")
                    }
                )

                Spacer(
                    modifier = Modifier.height(15.dp)
                )

                GeoCard(
                    emoji = "🌱",
                    titulo = "Paisagem Agrícola",
                    descricao = "Padrões geométricos de cultivos podem ser observados do alto e revelam diferentes técnicas de uso do solo.",
                    cor = verdeMedio,
                    onClick = {
                        navController.navigate("detail/paisagem_agricola")
                    }
                )

                Spacer(
                    modifier = Modifier.height(15.dp)
                )

                GeoCard(
                    emoji = "🏗️",
                    titulo = "Expansão Urbana",
                    descricao = "A visão aérea permite acompanhar o crescimento das cidades e a transformação de áreas rurais em espaços urbanos.",
                    cor = azulGeo,
                    onClick = {
                        navController.navigate("detail/expansao_urbana")
                    }
                )

                Spacer(
                    modifier = Modifier.height(28.dp)
                )

                Button(
                    onClick = {
                        authViewModel.signout()
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(52.dp),
                    shape = RoundedCornerShape(14.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = verdeEscuro
                    )
                ) {

                    Text(
                        text = "SAIR DA CONTA",
                        fontWeight = FontWeight.Bold,
                        fontSize = 15.sp
                    )
                }

                Spacer(
                    modifier = Modifier.height(20.dp)
                )

                Text(
                    text = "GeoVista • João Pedro Rodrigues",
                    color = Color.Gray,
                    fontSize = 12.sp,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(
                    modifier = Modifier.height(20.dp)
                )
            }
        }
    }
}

@Composable
fun GeoCard(
    emoji: String,
    titulo: String,
    descricao: String,
    cor: Color,
    onClick: () -> Unit
) {

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                onClick()
            },
        shape = RoundedCornerShape(18.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 4.dp
        )
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(18.dp),
            verticalAlignment = Alignment.Top,
            horizontalArrangement = Arrangement.spacedBy(15.dp)
        ) {

            Text(
                text = emoji,
                fontSize = 32.sp
            )

            Column(
                modifier = Modifier.weight(1f)
            ) {

                Text(
                    text = titulo,
                    color = cor,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )

                Spacer(
                    modifier = Modifier.height(6.dp)
                )

                Text(
                    text = descricao,
                    color = Color.DarkGray,
                    fontSize = 14.sp,
                    lineHeight = 20.sp
                )
            }
        }
    }
}