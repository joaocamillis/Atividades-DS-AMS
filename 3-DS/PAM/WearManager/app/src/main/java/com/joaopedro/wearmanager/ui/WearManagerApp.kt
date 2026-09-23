package com.joaopedro.wearmanager.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.joaopedro.wearmanager.model.Alarme
import com.joaopedro.wearmanager.model.Aplicativo
import com.joaopedro.wearmanager.model.Smartwatch
import com.joaopedro.wearmanager.repository.FirestoreRepository

private enum class Tela {
    HOME,
    SMARTWATCHES,
    APLICATIVOS,
    ALARMES
}

@Composable
fun WearManagerApp() {

    var telaAtual by remember {
        mutableStateOf(Tela.HOME)
    }

    when (telaAtual) {

        Tela.HOME -> HomeScreen(
            abrirSmartwatches = {
                telaAtual = Tela.SMARTWATCHES
            },
            abrirAplicativos = {
                telaAtual = Tela.APLICATIVOS
            },
            abrirAlarmes = {
                telaAtual = Tela.ALARMES
            }
        )

        Tela.SMARTWATCHES -> SmartwatchScreen(
            voltar = {
                telaAtual = Tela.HOME
            }
        )

        Tela.APLICATIVOS -> AplicativoScreen(
            voltar = {
                telaAtual = Tela.HOME
            }
        )

        Tela.ALARMES -> AlarmeScreen(
            voltar = {
                telaAtual = Tela.HOME
            }
        )
    }
}

// =============================================================
// HOME
// =============================================================

@Composable
fun HomeScreen(
    abrirSmartwatches: () -> Unit,
    abrirAplicativos: () -> Unit,
    abrirAlarmes: () -> Unit
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center
    ) {

        Text(
            text = "WearManager",
            style = MaterialTheme.typography.headlineLarge,
            fontWeight = FontWeight.Bold
        )

        Spacer(
            modifier = Modifier.height(8.dp)
        )

        Text(
            text = "Gerencie seus dispositivos vestíveis em um só lugar.",
            style = MaterialTheme.typography.bodyLarge
        )

        Spacer(
            modifier = Modifier.height(40.dp)
        )

        Button(
            onClick = abrirSmartwatches,
            modifier = Modifier.fillMaxWidth()
        ) {

            Column(
                modifier = Modifier.padding(8.dp)
            ) {

                Text(
                    text = "⌚ Smartwatches",
                    fontWeight = FontWeight.Bold
                )

                Text(
                    text = "Gerencie seus relógios"
                )
            }
        }

        Spacer(
            modifier = Modifier.height(16.dp)
        )

        Button(
            onClick = abrirAplicativos,
            modifier = Modifier.fillMaxWidth()
        ) {

            Column(
                modifier = Modifier.padding(8.dp)
            ) {

                Text(
                    text = "Aplicativos",
                    fontWeight = FontWeight.Bold
                )

                Text(
                    text = "Apps compatíveis com seus dispositivos"
                )
            }
        }

        Spacer(
            modifier = Modifier.height(16.dp)
        )

        Button(
            onClick = abrirAlarmes,
            modifier = Modifier.fillMaxWidth()
        ) {

            Column(
                modifier = Modifier.padding(8.dp)
            ) {

                Text(
                    text = "⏰ Alarmes",
                    fontWeight = FontWeight.Bold
                )

                Text(
                    text = "Configure seus alarmes"
                )
            }
        }
    }
}

// =============================================================
// SMARTWATCHES
// =============================================================

@Composable
fun SmartwatchScreen(
    voltar: () -> Unit
) {

    val repository = remember {
        FirestoreRepository()
    }

    var lista by remember {
        mutableStateOf<List<Smartwatch>>(emptyList())
    }

    var erro by remember {
        mutableStateOf("")
    }

    var mostrarFormulario by remember {
        mutableStateOf(false)
    }

    var editando by remember {
        mutableStateOf<Smartwatch?>(null)
    }

    DisposableEffect(Unit) {

        val listener = repository.observarSmartwatches(
            onChange = {
                lista = it
            },
            onError = {
                erro = it.message ?: "Erro ao carregar smartwatches."
            }
        )

        onDispose {
            listener.remove()
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp)
    ) {

        Cabecalho(
            titulo = "Smartwatches",
            voltar = voltar
        )

        Spacer(
            modifier = Modifier.height(20.dp)
        )

        Button(
            onClick = {
                editando = null
                mostrarFormulario = true
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("+ Novo smartwatch")
        }

        if (erro.isNotBlank()) {

            Spacer(
                modifier = Modifier.height(12.dp)
            )

            Text(
                text = erro,
                color = MaterialTheme.colorScheme.error
            )
        }

        Spacer(
            modifier = Modifier.height(16.dp)
        )

        if (lista.isEmpty()) {

            Text(
                text = "Nenhum smartwatch cadastrado."
            )

        } else {

            LazyColumn(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {

                items(
                    items = lista,
                    key = {
                        it.id
                    }
                ) { smartwatch ->

                    Card(
                        modifier = Modifier.fillMaxWidth()
                    ) {

                        Column(
                            modifier = Modifier.padding(16.dp)
                        ) {

                            Text(
                                text = smartwatch.nome,
                                style = MaterialTheme.typography.titleLarge,
                                fontWeight = FontWeight.Bold
                            )

                            Spacer(
                                modifier = Modifier.height(4.dp)
                            )

                            Text(
                                text = "${smartwatch.marca} • ${smartwatch.modelo}"
                            )

                            Text(
                                text = "Sistema: ${smartwatch.sistema}"
                            )

                            Text(
                                text = "Bateria: ${smartwatch.bateria}"
                            )

                            Spacer(
                                modifier = Modifier.height(12.dp)
                            )

                            Row(
                                horizontalArrangement = Arrangement.spacedBy(8.dp)
                            ) {

                                Button(
                                    onClick = {
                                        editando = smartwatch
                                        mostrarFormulario = true
                                    }
                                ) {
                                    Text("Editar")
                                }

                                OutlinedButton(
                                    onClick = {

                                        repository.excluirSmartwatch(
                                            id = smartwatch.id,
                                            onError = {
                                                erro =
                                                    it.message ?: "Erro ao excluir."
                                            }
                                        )
                                    }
                                ) {
                                    Text("Excluir")
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    if (mostrarFormulario) {

        SmartwatchDialog(
            smartwatch = editando,
            fechar = {
                mostrarFormulario = false
            },
            salvar = {

                repository.salvarSmartwatch(
                    smartwatch = it,
                    onSuccess = {
                        mostrarFormulario = false
                        erro = ""
                    },
                    onError = { exception ->
                        erro =
                            exception.message ?: "Erro ao salvar smartwatch."
                    }
                )
            }
        )
    }
}

@Composable
fun SmartwatchDialog(
    smartwatch: Smartwatch?,
    fechar: () -> Unit,
    salvar: (Smartwatch) -> Unit
) {

    var nome by remember(smartwatch?.id) {
        mutableStateOf(smartwatch?.nome.orEmpty())
    }

    var marca by remember(smartwatch?.id) {
        mutableStateOf(smartwatch?.marca.orEmpty())
    }

    var modelo by remember(smartwatch?.id) {
        mutableStateOf(smartwatch?.modelo.orEmpty())
    }

    var sistema by remember(smartwatch?.id) {
        mutableStateOf(smartwatch?.sistema.orEmpty())
    }

    var bateria by remember(smartwatch?.id) {
        mutableStateOf(smartwatch?.bateria.orEmpty())
    }

    AlertDialog(
        onDismissRequest = fechar,
        title = {

            Text(
                if (smartwatch == null) {
                    "Novo smartwatch"
                } else {
                    "Editar smartwatch"
                }
            )
        },
        text = {

            Column(
                modifier = Modifier.verticalScroll(
                    rememberScrollState()
                )
            ) {

                OutlinedTextField(
                    value = nome,
                    onValueChange = {
                        nome = it
                    },
                    label = {
                        Text("Nome")
                    },
                    modifier = Modifier.fillMaxWidth()
                )

                OutlinedTextField(
                    value = marca,
                    onValueChange = {
                        marca = it
                    },
                    label = {
                        Text("Marca")
                    },
                    modifier = Modifier.fillMaxWidth()
                )

                OutlinedTextField(
                    value = modelo,
                    onValueChange = {
                        modelo = it
                    },
                    label = {
                        Text("Modelo")
                    },
                    modifier = Modifier.fillMaxWidth()
                )

                OutlinedTextField(
                    value = sistema,
                    onValueChange = {
                        sistema = it
                    },
                    label = {
                        Text("Sistema operacional")
                    },
                    modifier = Modifier.fillMaxWidth()
                )

                OutlinedTextField(
                    value = bateria,
                    onValueChange = {
                        bateria = it
                    },
                    label = {
                        Text("Bateria")
                    },
                    modifier = Modifier.fillMaxWidth()
                )
            }
        },
        confirmButton = {

            Button(
                onClick = {

                    salvar(
                        Smartwatch(
                            id = smartwatch?.id.orEmpty(),
                            nome = nome,
                            marca = marca,
                            modelo = modelo,
                            sistema = sistema,
                            bateria = bateria
                        )
                    )
                },
                enabled = nome.isNotBlank()
            ) {
                Text("Salvar")
            }
        },
        dismissButton = {

            OutlinedButton(
                onClick = fechar
            ) {
                Text("Cancelar")
            }
        }
    )
}

// =============================================================
// APLICATIVOS
// =============================================================

@Composable
fun AplicativoScreen(
    voltar: () -> Unit
) {

    val repository = remember {
        FirestoreRepository()
    }

    var lista by remember {
        mutableStateOf<List<Aplicativo>>(emptyList())
    }

    var erro by remember {
        mutableStateOf("")
    }

    var mostrarFormulario by remember {
        mutableStateOf(false)
    }

    var editando by remember {
        mutableStateOf<Aplicativo?>(null)
    }

    DisposableEffect(Unit) {

        val listener = repository.observarAplicativos(
            onChange = {
                lista = it
            },
            onError = {
                erro = it.message ?: "Erro ao carregar aplicativos."
            }
        )

        onDispose {
            listener.remove()
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp)
    ) {

        Cabecalho(
            titulo = "Aplicativos",
            voltar = voltar
        )

        Spacer(
            modifier = Modifier.height(20.dp)
        )

        Button(
            onClick = {
                editando = null
                mostrarFormulario = true
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("+ Novo aplicativo")
        }

        if (erro.isNotBlank()) {

            Spacer(
                modifier = Modifier.height(12.dp)
            )

            Text(
                text = erro,
                color = MaterialTheme.colorScheme.error
            )
        }

        Spacer(
            modifier = Modifier.height(16.dp)
        )

        if (lista.isEmpty()) {

            Text(
                text = "Nenhum aplicativo cadastrado."
            )

        } else {

            LazyColumn(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {

                items(
                    items = lista,
                    key = {
                        it.id
                    }
                ) { aplicativo ->

                    Card(
                        modifier = Modifier.fillMaxWidth()
                    ) {

                        Column(
                            modifier = Modifier.padding(16.dp)
                        ) {

                            Text(
                                text = aplicativo.nome,
                                style = MaterialTheme.typography.titleLarge,
                                fontWeight = FontWeight.Bold
                            )

                            Text(
                                text = "Categoria: ${aplicativo.categoria}"
                            )

                            Text(
                                text = "Compatibilidade: ${aplicativo.compatibilidade}"
                            )

                            Text(
                                text = "Versão: ${aplicativo.versao}"
                            )

                            Spacer(
                                modifier = Modifier.height(12.dp)
                            )

                            Row(
                                horizontalArrangement = Arrangement.spacedBy(8.dp)
                            ) {

                                Button(
                                    onClick = {
                                        editando = aplicativo
                                        mostrarFormulario = true
                                    }
                                ) {
                                    Text("Editar")
                                }

                                OutlinedButton(
                                    onClick = {

                                        repository.excluirAplicativo(
                                            id = aplicativo.id,
                                            onError = {
                                                erro =
                                                    it.message ?: "Erro ao excluir."
                                            }
                                        )
                                    }
                                ) {
                                    Text("Excluir")
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    if (mostrarFormulario) {

        AplicativoDialog(
            aplicativo = editando,
            fechar = {
                mostrarFormulario = false
            },
            salvar = {

                repository.salvarAplicativo(
                    aplicativo = it,
                    onSuccess = {
                        mostrarFormulario = false
                        erro = ""
                    },
                    onError = { exception ->
                        erro =
                            exception.message ?: "Erro ao salvar aplicativo."
                    }
                )
            }
        )
    }
}

@Composable
fun AplicativoDialog(
    aplicativo: Aplicativo?,
    fechar: () -> Unit,
    salvar: (Aplicativo) -> Unit
) {

    var nome by remember(aplicativo?.id) {
        mutableStateOf(aplicativo?.nome.orEmpty())
    }

    var categoria by remember(aplicativo?.id) {
        mutableStateOf(aplicativo?.categoria.orEmpty())
    }

    var compatibilidade by remember(aplicativo?.id) {
        mutableStateOf(aplicativo?.compatibilidade.orEmpty())
    }

    var versao by remember(aplicativo?.id) {
        mutableStateOf(aplicativo?.versao.orEmpty())
    }

    AlertDialog(
        onDismissRequest = fechar,
        title = {

            Text(
                if (aplicativo == null) {
                    "Novo aplicativo"
                } else {
                    "Editar aplicativo"
                }
            )
        },
        text = {

            Column {

                OutlinedTextField(
                    value = nome,
                    onValueChange = {
                        nome = it
                    },
                    label = {
                        Text("Nome")
                    },
                    modifier = Modifier.fillMaxWidth()
                )

                OutlinedTextField(
                    value = categoria,
                    onValueChange = {
                        categoria = it
                    },
                    label = {
                        Text("Categoria")
                    },
                    modifier = Modifier.fillMaxWidth()
                )

                OutlinedTextField(
                    value = compatibilidade,
                    onValueChange = {
                        compatibilidade = it
                    },
                    label = {
                        Text("Compatibilidade")
                    },
                    modifier = Modifier.fillMaxWidth()
                )

                OutlinedTextField(
                    value = versao,
                    onValueChange = {
                        versao = it
                    },
                    label = {
                        Text("Versão")
                    },
                    modifier = Modifier.fillMaxWidth()
                )
            }
        },
        confirmButton = {

            Button(
                onClick = {

                    salvar(
                        Aplicativo(
                            id = aplicativo?.id.orEmpty(),
                            nome = nome,
                            categoria = categoria,
                            compatibilidade = compatibilidade,
                            versao = versao
                        )
                    )
                },
                enabled = nome.isNotBlank()
            ) {
                Text("Salvar")
            }
        },
        dismissButton = {

            OutlinedButton(
                onClick = fechar
            ) {
                Text("Cancelar")
            }
        }
    )
}

// =============================================================
// ALARMES
// =============================================================

@Composable
fun AlarmeScreen(
    voltar: () -> Unit
) {

    val repository = remember {
        FirestoreRepository()
    }

    var lista by remember {
        mutableStateOf<List<Alarme>>(emptyList())
    }

    var erro by remember {
        mutableStateOf("")
    }

    var mostrarFormulario by remember {
        mutableStateOf(false)
    }

    var editando by remember {
        mutableStateOf<Alarme?>(null)
    }

    DisposableEffect(Unit) {

        val listener = repository.observarAlarmes(
            onChange = {
                lista = it
            },
            onError = {
                erro = it.message ?: "Erro ao carregar alarmes."
            }
        )

        onDispose {
            listener.remove()
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp)
    ) {

        Cabecalho(
            titulo = "Alarmes",
            voltar = voltar
        )

        Spacer(
            modifier = Modifier.height(20.dp)
        )

        Button(
            onClick = {
                editando = null
                mostrarFormulario = true
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("+ Novo alarme")
        }

        if (erro.isNotBlank()) {

            Spacer(
                modifier = Modifier.height(12.dp)
            )

            Text(
                text = erro,
                color = MaterialTheme.colorScheme.error
            )
        }

        Spacer(
            modifier = Modifier.height(16.dp)
        )

        if (lista.isEmpty()) {

            Text(
                text = "Nenhum alarme cadastrado."
            )

        } else {

            LazyColumn(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {

                items(
                    items = lista,
                    key = {
                        it.id
                    }
                ) { alarme ->

                    Card(
                        modifier = Modifier.fillMaxWidth()
                    ) {

                        Column(
                            modifier = Modifier.padding(16.dp)
                        ) {

                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {

                                Column {

                                    Text(
                                        text = alarme.titulo,
                                        style = MaterialTheme.typography.titleLarge,
                                        fontWeight = FontWeight.Bold
                                    )

                                    Text(
                                        text = alarme.horario
                                    )
                                }

                                Text(
                                    if (alarme.ativo) {
                                        "Ativo"
                                    } else {
                                        "Desativado"
                                    }
                                )
                            }

                            Text(
                                text = "Dias: ${alarme.dias}"
                            )

                            Spacer(
                                modifier = Modifier.height(12.dp)
                            )

                            Row(
                                horizontalArrangement = Arrangement.spacedBy(8.dp)
                            ) {

                                Button(
                                    onClick = {
                                        editando = alarme
                                        mostrarFormulario = true
                                    }
                                ) {
                                    Text("Editar")
                                }

                                OutlinedButton(
                                    onClick = {

                                        repository.excluirAlarme(
                                            id = alarme.id,
                                            onError = {
                                                erro =
                                                    it.message ?: "Erro ao excluir."
                                            }
                                        )
                                    }
                                ) {
                                    Text("Excluir")
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    if (mostrarFormulario) {

        AlarmeDialog(
            alarme = editando,
            fechar = {
                mostrarFormulario = false
            },
            salvar = {

                repository.salvarAlarme(
                    alarme = it,
                    onSuccess = {
                        mostrarFormulario = false
                        erro = ""
                    },
                    onError = { exception ->
                        erro =
                            exception.message ?: "Erro ao salvar alarme."
                    }
                )
            }
        )
    }
}

@Composable
fun AlarmeDialog(
    alarme: Alarme?,
    fechar: () -> Unit,
    salvar: (Alarme) -> Unit
) {

    var titulo by remember(alarme?.id) {
        mutableStateOf(alarme?.titulo.orEmpty())
    }

    var horario by remember(alarme?.id) {
        mutableStateOf(alarme?.horario.orEmpty())
    }

    var dias by remember(alarme?.id) {
        mutableStateOf(alarme?.dias.orEmpty())
    }

    var ativo by remember(alarme?.id) {
        mutableStateOf(alarme?.ativo ?: true)
    }

    AlertDialog(
        onDismissRequest = fechar,
        title = {

            Text(
                if (alarme == null) {
                    "Novo alarme"
                } else {
                    "Editar alarme"
                }
            )
        },
        text = {

            Column {

                OutlinedTextField(
                    value = titulo,
                    onValueChange = {
                        titulo = it
                    },
                    label = {
                        Text("Título")
                    },
                    modifier = Modifier.fillMaxWidth()
                )

                OutlinedTextField(
                    value = horario,
                    onValueChange = {
                        horario = it
                    },
                    label = {
                        Text("Horário - Ex: 07:30")
                    },
                    modifier = Modifier.fillMaxWidth()
                )

                OutlinedTextField(
                    value = dias,
                    onValueChange = {
                        dias = it
                    },
                    label = {
                        Text("Dias - Ex: Seg, Qua, Sex")
                    },
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(
                    modifier = Modifier.height(16.dp)
                )

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {

                    Text("Alarme ativo")

                    Switch(
                        checked = ativo,
                        onCheckedChange = {
                            ativo = it
                        }
                    )
                }
            }
        },
        confirmButton = {

            Button(
                onClick = {

                    salvar(
                        Alarme(
                            id = alarme?.id.orEmpty(),
                            titulo = titulo,
                            horario = horario,
                            dias = dias,
                            ativo = ativo
                        )
                    )
                },
                enabled = titulo.isNotBlank()
            ) {
                Text("Salvar")
            }
        },
        dismissButton = {

            OutlinedButton(
                onClick = fechar
            ) {
                Text("Cancelar")
            }
        }
    )
}

// =============================================================
// COMPONENTE DE CABEÇALHO
// =============================================================

@Composable
fun Cabecalho(
    titulo: String,
    voltar: () -> Unit
) {

    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {

        OutlinedButton(
            onClick = voltar
        ) {
            Text("←")
        }

        Spacer(
            modifier = Modifier.padding(6.dp)
        )

        Text(
            text = titulo,
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold
        )
    }
}