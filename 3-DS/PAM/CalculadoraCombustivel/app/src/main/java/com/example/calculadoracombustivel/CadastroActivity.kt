package com.example.calculadoracombustivel

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.ComponentActivity

class CadastroActivity : ComponentActivity() {

    private lateinit var inputNomeCadastro: EditText
    private lateinit var inputEmailCadastro: EditText
    private lateinit var inputSenhaCadastro: EditText
    private lateinit var buttonCadastrar: Button
    private lateinit var buttonVoltarLogin: Button

    private lateinit var databaseHelper: DatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cadastro)

        databaseHelper = DatabaseHelper(this)

        inputNomeCadastro = findViewById(R.id.inputNomeCadastro)
        inputEmailCadastro = findViewById(R.id.inputEmailCadastro)
        inputSenhaCadastro = findViewById(R.id.inputSenhaCadastro)
        buttonCadastrar = findViewById(R.id.buttonCadastrar)
        buttonVoltarLogin = findViewById(R.id.buttonVoltarLogin)

        buttonCadastrar.setOnClickListener {
            cadastrarUsuario()
        }

        buttonVoltarLogin.setOnClickListener {
            finish()
        }
    }

    private fun cadastrarUsuario() {
        val nome = inputNomeCadastro.text.toString().trim()
        val email = inputEmailCadastro.text.toString().trim()
        val senha = inputSenhaCadastro.text.toString().trim()

        if (nome.isEmpty() || email.isEmpty() || senha.isEmpty()) {
            Toast.makeText(this, "Preencha todos os campos", Toast.LENGTH_SHORT).show()
            return
        }

        if (senha.length < 4) {
            Toast.makeText(this, "A senha deve ter pelo menos 4 caracteres", Toast.LENGTH_SHORT).show()
            return
        }

        if (databaseHelper.emailJaCadastrado(email)) {
            Toast.makeText(this, "Este e-mail já está cadastrado", Toast.LENGTH_SHORT).show()
            return
        }

        val cadastrou = databaseHelper.cadastrarUsuario(nome, email, senha)

        if (cadastrou) {
            Toast.makeText(this, "Cadastro realizado com sucesso", Toast.LENGTH_SHORT).show()

            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        } else {
            Toast.makeText(this, "Erro ao cadastrar usuário", Toast.LENGTH_SHORT).show()
        }
    }
}