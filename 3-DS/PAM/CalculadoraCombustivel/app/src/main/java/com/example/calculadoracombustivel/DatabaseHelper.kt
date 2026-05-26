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
        private const val DATABASE_VERSION = 1

        private const val TABLE_NAME = "calculos"
        private const val COLUMN_ID = "id"
        private const val COLUMN_DISTANCIA = "distancia"
        private const val COLUMN_CONSUMO = "consumo"
        private const val COLUMN_PRECO = "preco"
        private const val COLUMN_LITROS = "litros"
        private const val COLUMN_CUSTO = "custo"
    }

    override fun onCreate(db: SQLiteDatabase) {
        val createTable = """
            CREATE TABLE $TABLE_NAME (
                $COLUMN_ID INTEGER PRIMARY KEY AUTOINCREMENT,
                $COLUMN_DISTANCIA REAL NOT NULL,
                $COLUMN_CONSUMO REAL NOT NULL,
                $COLUMN_PRECO REAL NOT NULL,
                $COLUMN_LITROS REAL NOT NULL,
                $COLUMN_CUSTO REAL NOT NULL
            )
        """.trimIndent()

        db.execSQL(createTable)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        onCreate(db)
    }

    fun salvarCalculo(calculo: CalculoCombustivel): Boolean {
        val db = writableDatabase

        val valores = ContentValues().apply {
            put(COLUMN_DISTANCIA, calculo.distancia)
            put(COLUMN_CONSUMO, calculo.consumo)
            put(COLUMN_PRECO, calculo.preco)
            put(COLUMN_LITROS, calculo.litros)
            put(COLUMN_CUSTO, calculo.custo)
        }

        val resultado = db.insert(TABLE_NAME, null, valores)
        db.close()

        return resultado != -1L
    }

    fun listarCalculos(): List<CalculoCombustivel> {
        val lista = mutableListOf<CalculoCombustivel>()
        val db = readableDatabase

        val cursor = db.rawQuery(
            "SELECT * FROM $TABLE_NAME ORDER BY $COLUMN_ID DESC",
            null
        )

        if (cursor.moveToFirst()) {
            do {
                val calculo = CalculoCombustivel(
                    id = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_ID)),
                    distancia = cursor.getDouble(cursor.getColumnIndexOrThrow(COLUMN_DISTANCIA)),
                    consumo = cursor.getDouble(cursor.getColumnIndexOrThrow(COLUMN_CONSUMO)),
                    preco = cursor.getDouble(cursor.getColumnIndexOrThrow(COLUMN_PRECO)),
                    litros = cursor.getDouble(cursor.getColumnIndexOrThrow(COLUMN_LITROS)),
                    custo = cursor.getDouble(cursor.getColumnIndexOrThrow(COLUMN_CUSTO))
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
        db.delete(TABLE_NAME, null, null)
        db.close()
    }
}