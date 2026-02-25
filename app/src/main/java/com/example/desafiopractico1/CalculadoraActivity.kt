package com.example.desafiopractico1

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class CalculadoraActivity : AppCompatActivity() {

    private lateinit var etNum1: EditText
    private lateinit var etNum2: EditText
    private lateinit var tvResultado: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calculadora)

        // Inicializar vistas
        etNum1 = findViewById(R.id.etNum1)
        etNum2 = findViewById(R.id.etNum2)
        tvResultado = findViewById(R.id.tvResultado)

        val btnSuma = findViewById<Button>(R.id.btnSuma)
        val btnResta = findViewById<Button>(R.id.btnResta)
        val btnMulti = findViewById<Button>(R.id.btnMulti)
        val btnDiv = findViewById<Button>(R.id.btnDiv)
        val btnBack = findViewById<Button>(R.id.btnBack)

        // Operaciones
        btnSuma.setOnClickListener { calcular("+") }
        btnResta.setOnClickListener { calcular("-") }
        btnMulti.setOnClickListener { calcular("*") }
        btnDiv.setOnClickListener { calcular("/") }

        btnBack.setOnClickListener { finish() }
    }

    private fun calcular(operador: String) {
        val n1Str = etNum1.text.toString()
        val n2Str = etNum2.text.toString()


        if (n1Str.isEmpty()) {
            etNum1.error = "Ingrese un número"
            return
        }
        if (n2Str.isEmpty()) {
            etNum2.error = "Ingrese un número"
            return
        }

        val n1 = n1Str.toDouble()
        val n2 = n2Str.toDouble()
        var resultado = 0.0

        when (operador) {
            "+" -> resultado = n1 + n2
            "-" -> resultado = n1 - n2
            "*" -> resultado = n1 * n2
            "/" -> {
                if (n2 == 0.0) {
                    etNum2.error = "No se puede dividir por cero"
                    Toast.makeText(this, "Error: División por 0", Toast.LENGTH_SHORT).show()
                    return
                }
                resultado = n1 / n2
            }
        }

        mostrarResultado(resultado)
    }

    private fun mostrarResultado(res: Double) {

        val format = if (res % 1 == 0.0) res.toInt().toString() else res.toString()
        tvResultado.text = "Resultado: $format"
    }
}