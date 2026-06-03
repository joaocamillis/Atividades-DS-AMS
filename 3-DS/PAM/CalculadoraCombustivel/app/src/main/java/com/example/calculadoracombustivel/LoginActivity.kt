package com.example.calculadoracombustivel

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.ComponentActivity

class LoginActivity : ComponentActivity() {

    private lateinit var inputEmailLogin: EditText
    private lateinit var inputSenhaLogin: EditText
    private lateinit var buttonEntrar: Button
    private lateinit var buttonIrCadastro: Button

    private lateinit var databaseHelper: DatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        databaseHelper = DatabaseHelper(this)

        inputEmailLogin = findViewById(R.id.inputEmailLogin)
        inputSenhaLogin = findViewById(R.id.inputSenhaLogin)
        buttonEntrar = findViewById(R.id.buttonEntrar)
        buttonIrCadastro = findViewById(R.id.buttonIrCadastro)

        buttonEntrar.setOnClickListener {
            fazerLogin()
        }

        buttonIrCadastro.setOnClickListener {
            val intent = Intent(this, CadastroActivity::class.java)
            startActivity(intent)
        }
    }

    private fun fazerLogin() {
        val email = inputEmailLogin.text.toString().trim()
        val senha = inputSenhaLogin.text.toString().trim()

        if (email.isEmpty() || senha.isEmpty()) {
            Toast.makeText(this, "Preencha todos os campos", Toast.LENGTH_SHORT).show()
            return
        }

        val loginValido = databaseHelper.verificarLogin(email, senha)

        if (loginValido) {
            Toast.makeText(this, "Login realizado com sucesso", Toast.LENGTH_SHORT).show()

            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        } else {
            Toast.makeText(this, "E-mail ou senha incorretos", Toast.LENGTH_SHORT).show()
        }
    }
}