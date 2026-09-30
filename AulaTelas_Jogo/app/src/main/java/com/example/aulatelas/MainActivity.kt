package com.example.aulatelas

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.RadioGroup
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val edtNome = findViewById<EditText>(R.id.edtNome)
        val rgLimite = findViewById<RadioGroup>(R.id.rgLimite)
        val btnAbrir = findViewById<Button>(R.id.btnAbrir)

        btnAbrir.setOnClickListener {
            val nome = edtNome.text.toString().trim()

            if (nome.isEmpty()) {
                edtNome.error = "Digite seu nome"
                return@setOnClickListener
            }

            val maximo = when (rgLimite.checkedRadioButtonId) {
                R.id.rb10 -> 10
                R.id.rb100 -> 100
                else -> 50
            }

            val intent = Intent(this, JogoActivity::class.java)
            intent.putExtra("nome", nome)
            intent.putExtra("maximo", maximo)
            startActivity(intent)
        }
    }
}
