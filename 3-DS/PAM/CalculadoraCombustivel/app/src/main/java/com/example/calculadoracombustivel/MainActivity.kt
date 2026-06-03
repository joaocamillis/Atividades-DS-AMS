package com.example.calculadoracombustivel

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.text.NumberFormat
import java.util.Locale

class MainActivity : AppCompatActivity() {

    private lateinit var inputDistancia: EditText
    private lateinit var inputConsumo: EditText
    private lateinit var inputPreco: EditText
    private lateinit var buttonCalcular: Button
    private lateinit var buttonLimpar: Button
    private lateinit var textResultado: TextView
    private lateinit var textHistorico: TextView

    private lateinit var databaseHelper: DatabaseHelper

    private val formatoMoeda = NumberFormat.getCurrencyInstance(Locale("pt", "BR"))

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        databaseHelper = DatabaseHelper(this)

        inputDistancia = findViewById(R.id.inputDistancia)
        inputConsumo = findViewById(R.id.inputConsumo)
        inputPreco = findViewById(R.id.inputPreco)
        buttonCalcular = findViewById(R.id.buttonCalcular)
        buttonLimpar = findViewById(R.id.buttonLimpar)
        textResultado = findViewById(R.id.textResultado)
        textHistorico = findViewById(R.id.textHistorico)

        buttonCalcular.setOnClickListener {
            calcularCombustivel()
        }

        buttonLimpar.setOnClickListener {
            databaseHelper.limparHistorico()
            atualizarHistorico()
            textResultado.text = "Resultado aparecerá aqui"
            Toast.makeText(this, "Histórico limpo", Toast.LENGTH_SHORT).show()
        }

        atualizarHistorico()
    }

    private fun calcularCombustivel() {
        val distanciaTexto = inputDistancia.text.toString()
        val consumoTexto = inputConsumo.text.toString()
        val precoTexto = inputPreco.text.toString()

        if (distanciaTexto.isEmpty() || consumoTexto.isEmpty() || precoTexto.isEmpty()) {
            Toast.makeText(this, "Preencha todos os campos", Toast.LENGTH_SHORT).show()
            return
        }

        val distancia = distanciaTexto.toDoubleOrNull()
        val consumo = consumoTexto.toDoubleOrNull()
        val preco = precoTexto.toDoubleOrNull()

        if (distancia == null || consumo == null || preco == null) {
            Toast.makeText(this, "Digite apenas números válidos", Toast.LENGTH_SHORT).show()
            return
        }

        if (distancia <= 0 || consumo <= 0 || preco <= 0) {
            Toast.makeText(this, "Os valores devem ser maiores que zero", Toast.LENGTH_SHORT).show()
            return
        }

        val litrosNecessarios = distancia / consumo
        val custoTotal = litrosNecessarios * preco

        val calculo = CalculoCombustivel(
            distancia = distancia,
            consumo = consumo,
            preco = preco,
            litros = litrosNecessarios,
            custo = custoTotal
        )

        val salvou = databaseHelper.salvarCalculo(calculo)

        if (salvou) {
            Toast.makeText(this, "Cálculo salvo no SQLite", Toast.LENGTH_SHORT).show()
        }

        textResultado.text = """
            Litros necessários: ${"%.2f".format(litrosNecessarios)} L
            Custo total: ${formatoMoeda.format(custoTotal)}
        """.trimIndent()

        atualizarHistorico()
    }

    private fun atualizarHistorico() {
        val calculos = databaseHelper.listarCalculos()

        if (calculos.isEmpty()) {
            textHistorico.text = "Nenhum cálculo salvo ainda."
            return
        }

        val historico = StringBuilder()

        for (calculo in calculos) {
            historico.append(
                """
                ID: ${calculo.id}
                Distância: ${calculo.distancia} km
                Consumo: ${calculo.consumo} km/L
                Preço: ${formatoMoeda.format(calculo.preco)}
                Litros: ${"%.2f".format(calculo.litros)} L
                Custo: ${formatoMoeda.format(calculo.custo)}
                
                """.trimIndent()
            )

            historico.append("\n-----------------------\n")
        }

        textHistorico.text = historico.toString()
    }

}