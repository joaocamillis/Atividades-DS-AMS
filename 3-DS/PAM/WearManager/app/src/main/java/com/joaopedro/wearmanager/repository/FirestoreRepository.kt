package com.joaopedro.wearmanager.repository

import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ListenerRegistration
import com.joaopedro.wearmanager.model.Alarme
import com.joaopedro.wearmanager.model.Aplicativo
import com.joaopedro.wearmanager.model.Smartwatch

class FirestoreRepository {

    private val db = FirebaseFirestore.getInstance()

    // =========================================================
    // SMARTWATCHES
    // =========================================================

    fun observarSmartwatches(
        onChange: (List<Smartwatch>) -> Unit,
        onError: (Exception) -> Unit
    ): ListenerRegistration {

        return db.collection("smartwatches")
            .addSnapshotListener { snapshot, erro ->

                if (erro != null) {
                    onError(erro)
                    return@addSnapshotListener
                }

                val lista = snapshot?.documents?.map { documento ->

                    Smartwatch(
                        id = documento.id,
                        nome = documento.getString("nome").orEmpty(),
                        marca = documento.getString("marca").orEmpty(),
                        modelo = documento.getString("modelo").orEmpty(),
                        sistema = documento.getString("sistema").orEmpty(),
                        bateria = documento.getString("bateria").orEmpty()
                    )

                } ?: emptyList()

                onChange(lista)
            }
    }

    fun salvarSmartwatch(
        smartwatch: Smartwatch,
        onSuccess: () -> Unit,
        onError: (Exception) -> Unit
    ) {

        val dados = hashMapOf(
            "nome" to smartwatch.nome,
            "marca" to smartwatch.marca,
            "modelo" to smartwatch.modelo,
            "sistema" to smartwatch.sistema,
            "bateria" to smartwatch.bateria
        )

        val referencia =
            if (smartwatch.id.isBlank()) {
                db.collection("smartwatches").document()
            } else {
                db.collection("smartwatches").document(smartwatch.id)
            }

        referencia.set(dados)
            .addOnSuccessListener {
                onSuccess()
            }
            .addOnFailureListener {
                onError(it)
            }
    }

    fun excluirSmartwatch(
        id: String,
        onSuccess: () -> Unit = {},
        onError: (Exception) -> Unit = {}
    ) {

        db.collection("smartwatches")
            .document(id)
            .delete()
            .addOnSuccessListener {
                onSuccess()
            }
            .addOnFailureListener {
                onError(it)
            }
    }

    // =========================================================
    // APLICATIVOS
    // =========================================================

    fun observarAplicativos(
        onChange: (List<Aplicativo>) -> Unit,
        onError: (Exception) -> Unit
    ): ListenerRegistration {

        return db.collection("aplicativos")
            .addSnapshotListener { snapshot, erro ->

                if (erro != null) {
                    onError(erro)
                    return@addSnapshotListener
                }

                val lista = snapshot?.documents?.map { documento ->

                    Aplicativo(
                        id = documento.id,
                        nome = documento.getString("nome").orEmpty(),
                        categoria = documento.getString("categoria").orEmpty(),
                        compatibilidade = documento.getString("compatibilidade").orEmpty(),
                        versao = documento.getString("versao").orEmpty()
                    )

                } ?: emptyList()

                onChange(lista)
            }
    }

    fun salvarAplicativo(
        aplicativo: Aplicativo,
        onSuccess: () -> Unit,
        onError: (Exception) -> Unit
    ) {

        val dados = hashMapOf(
            "nome" to aplicativo.nome,
            "categoria" to aplicativo.categoria,
            "compatibilidade" to aplicativo.compatibilidade,
            "versao" to aplicativo.versao
        )

        val referencia =
            if (aplicativo.id.isBlank()) {
                db.collection("aplicativos").document()
            } else {
                db.collection("aplicativos").document(aplicativo.id)
            }

        referencia.set(dados)
            .addOnSuccessListener {
                onSuccess()
            }
            .addOnFailureListener {
                onError(it)
            }
    }

    fun excluirAplicativo(
        id: String,
        onSuccess: () -> Unit = {},
        onError: (Exception) -> Unit = {}
    ) {

        db.collection("aplicativos")
            .document(id)
            .delete()
            .addOnSuccessListener {
                onSuccess()
            }
            .addOnFailureListener {
                onError(it)
            }
    }

    // =========================================================
    // ALARMES
    // =========================================================

    fun observarAlarmes(
        onChange: (List<Alarme>) -> Unit,
        onError: (Exception) -> Unit
    ): ListenerRegistration {

        return db.collection("alarmes")
            .addSnapshotListener { snapshot, erro ->

                if (erro != null) {
                    onError(erro)
                    return@addSnapshotListener
                }

                val lista = snapshot?.documents?.map { documento ->

                    Alarme(
                        id = documento.id,
                        titulo = documento.getString("titulo").orEmpty(),
                        horario = documento.getString("horario").orEmpty(),
                        dias = documento.getString("dias").orEmpty(),
                        ativo = documento.getBoolean("ativo") ?: true
                    )

                } ?: emptyList()

                onChange(lista)
            }
    }

    fun salvarAlarme(
        alarme: Alarme,
        onSuccess: () -> Unit,
        onError: (Exception) -> Unit
    ) {

        val dados = hashMapOf<String, Any>(
            "titulo" to alarme.titulo,
            "horario" to alarme.horario,
            "dias" to alarme.dias,
            "ativo" to alarme.ativo
        )

        val referencia =
            if (alarme.id.isBlank()) {
                db.collection("alarmes").document()
            } else {
                db.collection("alarmes").document(alarme.id)
            }

        referencia.set(dados)
            .addOnSuccessListener {
                onSuccess()
            }
            .addOnFailureListener {
                onError(it)
            }
    }

    fun excluirAlarme(
        id: String,
        onSuccess: () -> Unit = {},
        onError: (Exception) -> Unit = {}
    ) {

        db.collection("alarmes")
            .document(id)
            .delete()
            .addOnSuccessListener {
                onSuccess()
            }
            .addOnFailureListener {
                onError(it)
            }
    }
}