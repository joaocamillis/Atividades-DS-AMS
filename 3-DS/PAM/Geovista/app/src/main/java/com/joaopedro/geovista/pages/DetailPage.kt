package com.joaopedro.geovista.pages

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

data class GeoDetail(
    val emoji: String,
    val titulo: String,
    val categoria: String,
    val descricao: String,
    val observacao: String
)

@Composable
fun DetailPage(
    navController: NavController,
    itemId: String
) {

    val detalhe = getGeoDetail(itemId)

    val verdeEscuro = Color(0xFF1B5E4B)
    val azulGeo = Color(0xFF1F6F8B)
    val fundo = Color(0xFFF4F7F5)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(fundo)
            .verticalScroll(rememberScrollState())
    ) {

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
                ),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Text(
                text = detalhe.emoji,
                fontSize = 60.sp
            )

            Spacer(
                modifier = Modifier.height(12.dp)
            )

            Text(
                text = detalhe.titulo,
                color = Color.White,
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )

            Spacer(
                modifier = Modifier.height(6.dp)
            )

            Text(
                text = detalhe.categoria,
                color = Color.White.copy(alpha = 0.8f),
                fontSize = 15.sp
            )
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp)
        ) {

            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(18.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Color.White
                ),
                elevation = CardDefaults.cardElevation(
                    defaultElevation = 4.dp
                )
            ) {

                Column(
                    modifier = Modifier.padding(20.dp)
                ) {

                    Text(
                        text = "Sobre",
                        color = verdeEscuro,
                        fontSize = 21.sp,
                        fontWeight = FontWeight.Bold
                    )

                    Spacer(
                        modifier = Modifier.height(10.dp)
                    )

                    Text(
                        text = detalhe.descricao,
                        color = Color.DarkGray,
                        fontSize = 15.sp,
                        lineHeight = 22.sp
                    )
                }
            }

            Spacer(
                modifier = Modifier.height(18.dp)
            )

            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(18.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Color.White
                ),
                elevation = CardDefaults.cardElevation(
                    defaultElevation = 4.dp
                )
            ) {

                Column(
                    modifier = Modifier.padding(20.dp)
                ) {

                    Text(
                        text = "O que observar de cima?",
                        color = azulGeo,
                        fontSize = 21.sp,
                        fontWeight = FontWeight.Bold
                    )

                    Spacer(
                        modifier = Modifier.height(10.dp)
                    )

                    Text(
                        text = detalhe.observacao,
                        color = Color.DarkGray,
                        fontSize = 15.sp,
                        lineHeight = 22.sp
                    )
                }
            }

            Spacer(
                modifier = Modifier.height(25.dp)
            )

            Button(
                onClick = {
                    navController.popBackStack()
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
                    text = "VOLTAR",
                    fontWeight = FontWeight.Bold
                )
            }

            Spacer(
                modifier = Modifier.height(25.dp)
            )
        }
    }
}

fun getGeoDetail(itemId: String): GeoDetail {

    return when (itemId) {

        "assentamentos" -> GeoDetail(
            emoji = "🏙️",
            titulo = "Assentamentos Urbanos",
            categoria = "Assentamentos",
            descricao = "Os assentamentos urbanos representam a forma como a população ocupa e organiza o espaço das cidades.",
            observacao = "A visão aérea permite identificar ruas, bairros, concentração de construções, áreas residenciais e diferentes padrões de crescimento urbano."
        )

        "agricultura" -> GeoDetail(
            emoji = "🌾",
            titulo = "Agricultura",
            categoria = "Agricultura",
            descricao = "A agricultura transforma grandes extensões do território para a produção de alimentos e matérias-primas.",
            observacao = "Do alto, é possível observar plantações, limites de propriedades, diferentes culturas agrícolas e padrões geométricos formados pelo cultivo."
        )

        "uso_da_terra" -> GeoDetail(
            emoji = "🗺️",
            titulo = "Uso da Terra",
            categoria = "Uso do território",
            descricao = "O uso da terra mostra como diferentes áreas são destinadas às atividades humanas e à preservação ambiental.",
            observacao = "É possível diferenciar áreas urbanizadas, agrícolas, rurais, vegetadas e outros tipos de ocupação do território."
        )

        "ocupacao_rural" -> GeoDetail(
            emoji = "🏘️",
            titulo = "Ocupação Rural",
            categoria = "Espaço Rural",
            descricao = "A ocupação rural envolve propriedades, pequenas comunidades, estradas e áreas destinadas à produção.",
            observacao = "A perspectiva aérea evidencia a distribuição das propriedades, vias de acesso e a relação entre áreas produtivas e naturais."
        )

        "paisagem_agricola" -> GeoDetail(
            emoji = "🌱",
            titulo = "Paisagem Agrícola",
            categoria = "Agricultura",
            descricao = "As paisagens agrícolas são resultado das técnicas utilizadas pelo ser humano para produzir e organizar o espaço rural.",
            observacao = "Do alto, cultivos podem formar linhas, círculos, faixas e outros padrões que revelam diferentes métodos de produção."
        )

        "expansao_urbana" -> GeoDetail(
            emoji = "🏗️",
            titulo = "Expansão Urbana",
            categoria = "Urbanização",
            descricao = "A expansão urbana ocorre quando as cidades crescem e passam a ocupar novas áreas do território.",
            observacao = "Fotografias aéreas ajudam a observar novos bairros, loteamentos, vias e a transformação de antigas áreas rurais em espaços urbanos."
        )

        else -> GeoDetail(
            emoji = "🌍",
            titulo = "GeoVista",
            categoria = "Geografia Humana",
            descricao = "Conteúdo não encontrado.",
            observacao = "Retorne para a página inicial e escolha outra categoria."
        )
    }
}