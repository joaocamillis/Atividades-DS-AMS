package com.example.calculadoracombustivel

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DatabaseHelper(context: Context) : SQLiteOpenHelper(
    context,
    DATABASE_NAME,
    null,
    DATABASE_VERSION
) {

    companion object {
        private const val DATABASE_NAME = "combustivel.db"
        private const val DATABASE_VERSION = 2

        private const val TABLE_CALCULOS = "calculos"
        private const val TABLE_USUARIOS = "usuarios"
    }

    override fun onCreate(db: SQLiteDatabase) {
        val criarTabelaCalculos = """
            CREATE TABLE $TABLE_CALCULOS (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                distancia REAL NOT NULL,
                consumo REAL NOT NULL,
                preco REAL NOT NULL,
                litros REAL NOT NULL,
                custo REAL NOT NULL
            )
        """.trimIndent()

        val criarTabelaUsuarios = """
            CREATE TABLE $TABLE_USUARIOS (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                nome TEXT NOT NULL,
                email TEXT NOT NULL UNIQUE,
                senha TEXT NOT NULL
            )
        """.trimIndent()

        db.execSQL(criarTabelaCalculos)
        db.execSQL(criarTabelaUsuarios)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS $TABLE_CALCULOS")
        db.execSQL("DROP TABLE IF EXISTS $TABLE_USUARIOS")
        onCreate(db)
    }

    fun cadastrarUsuario(nome: String, email: String, senha: String): Boolean {
        val db = writableDatabase

        val valores = ContentValues().apply {
            put("nome", nome)
            put("email", email)
            put("senha", senha)
        }

        val resultado = db.insert(TABLE_USUARIOS, null, valores)
        db.close()

        return resultado != -1L
    }

    fun verificarLogin(email: String, senha: String): Boolean {
        val db = readableDatabase

        val cursor = db.rawQuery(
            "SELECT * FROM $TABLE_USUARIOS WHERE email = ? AND senha = ?",
            arrayOf(email, senha)
        )

        val existeUsuario = cursor.count > 0

        cursor.close()
        db.close()

        return existeUsuario
    }

    fun emailJaCadastrado(email: String): Boolean {
        val db = readableDatabase

        val cursor = db.rawQuery(
            "SELECT * FROM $TABLE_USUARIOS WHERE email = ?",
            arrayOf(email)
        )

        val existe = cursor.count > 0

        cursor.close()
        db.close()

        return existe
    }

    fun salvarCalculo(calculo: CalculoCombustivel): Boolean {
        val db = writableDatabase

        val valores = ContentValues().apply {
            put("distancia", calculo.distancia)
            put("consumo", calculo.consumo)
            put("preco", calculo.preco)
            put("litros", calculo.litros)
            put("custo", calculo.custo)
        }

        val resultado = db.insert(TABLE_CALCULOS, null, valores)
        db.close()

        return resultado != -1L
    }

    fun listarCalculos(): List<CalculoCombustivel> {
        val lista = mutableListOf<CalculoCombustivel>()
        val db = readableDatabase

        val cursor = db.rawQuery(
            "SELECT * FROM $TABLE_CALCULOS ORDER BY id DESC",
            null
        )

        if (cursor.moveToFirst()) {
            do {
                val calculo = CalculoCombustivel(
                    id = cursor.getInt(cursor.getColumnIndexOrThrow("id")),
                    distancia = cursor.getDouble(cursor.getColumnIndexOrThrow("distancia")),
                    consumo = cursor.getDouble(cursor.getColumnIndexOrThrow("consumo")),
                    preco = cursor.getDouble(cursor.getColumnIndexOrThrow("preco")),
                    litros = cursor.getDouble(cursor.getColumnIndexOrThrow("litros")),
                    custo = cursor.getDouble(cursor.getColumnIndexOrThrow("custo"))
                )

                lista.add(calculo)
            } while (cursor.moveToNext())
        }

        cursor.close()
        db.close()

        return lista
    }

    fun limparHistorico() {
        val db = writableDatabase
        db.delete(TABLE_CALCULOS, null, null)
        db.close()
    }
}