package com.example.desafiopractico1

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Inicialización de componentes UI
        val btnEjercicio1 = findViewById<Button>(R.id.btnEjercicio1)
        val btnEjercicio2 = findViewById<Button>(R.id.btnEjercicio2)
        val btnEjercicio3 = findViewById<Button>(R.id.btnEjercicio3)

        // Configuración de Listeners para Navegación
        btnEjercicio1.setOnClickListener { navigateToPromedio() }
        btnEjercicio2.setOnClickListener { navigateToSalario() }
        btnEjercicio3.setOnClickListener { navigateToCalculadora() }
    }

    private fun navigateToPromedio() {
        val intent = Intent(this, PromedioActivity::class.java)
        startActivity(intent)
    }

    private fun navigateToSalario() {
        val intent = Intent(this, SalarioActivity::class.java)
        startActivity(intent)
    }

    private fun navigateToCalculadora() {
        val intent = Intent(this, CalculadoraActivity::class.java)
        startActivity(intent)
    }
}