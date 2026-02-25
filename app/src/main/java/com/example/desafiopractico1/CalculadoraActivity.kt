package com.example.desafiopractico1

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlin.math.pow
import kotlin.math.sqrt

class CalculadoraActivity : AppCompatActivity() {

    private lateinit var etNum1: EditText
    private lateinit var etNum2: EditText
    private lateinit var tvResultado: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calculadora)

        etNum1 = findViewById(R.id.etNum1)
        etNum2 = findViewById(R.id.etNum2)
        tvResultado = findViewById(R.id.tvResultado)

        findViewById<Button>(R.id.btnSuma).setOnClickListener { calcular("+") }
        findViewById<Button>(R.id.btnResta).setOnClickListener { calcular("-") }
        findViewById<Button>(R.id.btnMulti).setOnClickListener { calcular("*") }
        findViewById<Button>(R.id.btnDiv).setOnClickListener { calcular("/") }
        findViewById<Button>(R.id.btnPotencia).setOnClickListener { calcular("^") }
        findViewById<Button>(R.id.btnRaiz).setOnClickListener { calcular("SQRT") }

        findViewById<Button>(R.id.btnBack).setOnClickListener { finish() }
    }

    private fun calcular(operador: String) {
        val s1 = etNum1.text.toString()
        val s2 = etNum2.text.toString()

        if (s1.isEmpty()) {
            etNum1.error = "Ingrese un número"
            return
        }

        val n1 = s1.toDouble()
        var resultado = 0.0

        when (operador) {
            "+" -> if (validarN2(s2)) resultado = n1 + s2.toDouble() else return
            "-" -> if (validarN2(s2)) resultado = n1 - s2.toDouble() else return
            "*" -> if (validarN2(s2)) resultado = n1 * s2.toDouble() else return
            "/" -> {
                if (validarN2(s2)) {
                    val n2 = s2.toDouble()
                    if (n2 == 0.0) {
                        etNum2.error = "División por cero"
                        return
                    }
                    resultado = n1 / n2
                } else return
            }
            "^" -> if (validarN2(s2)) resultado = n1.pow(s2.toDouble()) else return
            "SQRT" -> {
                if (n1 < 0) {
                    etNum1.error = "No existe raíz de negativos"
                    return
                }
                resultado = sqrt(n1)
            }
        }
        mostrarResultado(resultado)
    }

    private fun validarN2(s2: String): Boolean {
        if (s2.isEmpty()) {
            etNum2.error = "Ingrese el segundo número"
            return false
        }
        return true
    }

    private fun mostrarResultado(res: Double) {
        val format = if (res % 1 == 0.0) res.toLong().toString() else "%.4f".format(res)
        tvResultado.text = "Resultado: $format"
    }
}