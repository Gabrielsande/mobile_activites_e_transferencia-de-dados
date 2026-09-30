package com.example.aulatelas

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import kotlin.math.abs
import kotlin.random.Random

class JogoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_jogo)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val txtBoasVindas = findViewById<TextView>(R.id.txtBoasVindas)
        val edtPalpite = findViewById<EditText>(R.id.edtPalpite)
        val btnChutar = findViewById<Button>(R.id.btnChutar)
        val txtDica = findViewById<TextView>(R.id.txtDica)
        val txtTentativas = findViewById<TextView>(R.id.txtTentativas)
        val txtResultado = findViewById<TextView>(R.id.txtResultado)
        val btnJogarDeNovo = findViewById<Button>(R.id.btnJogarDeNovo)

        // Recebe os dados enviados pela MainActivity
        val nome = intent.getStringExtra("nome") ?: "Jogador"
        val maximo = intent.getIntExtra("maximo", 50)

        txtBoasVindas.text = "$nome, pensei num número de 1 a $maximo!"

        // Sorteia o número secreto
        val secreto = Random.nextInt(1, maximo + 1)
        var tentativas = 0

        btnChutar.setOnClickListener {
            val palpite = edtPalpite.text.toString().toIntOrNull()

            if (palpite == null) {
                edtPalpite.error = "Digite um número"
                return@setOnClickListener
            }

            tentativas++
            txtTentativas.text = "Tentativas: $tentativas"

            if (palpite == secreto) {
                txtDica.text = "Acertou!"
                val palavra = if (tentativas == 1) "tentativa" else "tentativas"
                txtResultado.text = "$nome acertou em $tentativas $palavra!"
                txtResultado.visibility = View.VISIBLE
                btnJogarDeNovo.visibility = View.VISIBLE
                btnChutar.isEnabled = false
            } else {
                val diferenca = abs(palpite - secreto)
                val temperatura = if (diferenca <= maximo / 10) "Quente" else "Frio"
                val direcao = if (secreto > palpite) "MAIOR" else "MENOR"
                txtDica.text = "$temperatura! O número é $direcao."
            }

            edtPalpite.text.clear()
        }

        // Volta para a primeira tela
        btnJogarDeNovo.setOnClickListener {
            finish()
        }
    }
}
