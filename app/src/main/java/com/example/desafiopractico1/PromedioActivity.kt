package com.example.desafiopractico1

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.text.DecimalFormat

class PromedioActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_promedio)

        val etNombre = findViewById<EditText>(R.id.etNombreEstudiante)
        val etN1 = findViewById<EditText>(R.id.etNota1)
        val etN2 = findViewById<EditText>(R.id.etNota2)
        val etN3 = findViewById<EditText>(R.id.etNota3)
        val etN4 = findViewById<EditText>(R.id.etNota4)
        val etN5 = findViewById<EditText>(R.id.etNota5)
        val btnCalcular = findViewById<Button>(R.id.btnCalcular)
        val tvRes = findViewById<TextView>(R.id.tvResultado)
        val btnRegresar = findViewById<Button>(R.id.btnRegresar)

        btnCalcular.setOnClickListener {
            if (validarCampos(etN1, etN2, etN3, etN4, etN5)) {
                val n1 = etN1.text.toString().toDouble()
                val n2 = etN2.text.toString().toDouble()
                val n3 = etN3.text.toString().toDouble()
                val n4 = etN4.text.toString().toDouble()
                val n5 = etN5.text.toString().toDouble()

                if (validarRango(n1, n2, n3, n4, n5)) {
                    val promedio = calcularPromedio(n1, n2, n3, n4, n5)
                    val df = DecimalFormat("#.##")
                    val estado = if (promedio >= 6.0) "APROBADO" else "REPROBADO"
                    tvRes.text = "Promedio: ${df.format(promedio)}\nEstado: $estado"
                } else {
                    Toast.makeText(this, "Las notas deben estar entre 0 y 10", Toast.LENGTH_SHORT).show()
                }
            }
        }

        btnRegresar.setOnClickListener { finish() }
    }

    private fun calcularPromedio(n1: Double, n2: Double, n3: Double, n4: Double, n5: Double): Double {
        return (n1 * 0.15) + (n2 * 0.15) + (n3 * 0.20) + (n4 * 0.25) + (n5 * 0.25)
    }

    private fun validarCampos(vararg editTexts: EditText): Boolean {
        for (et in editTexts) {
            if (et.text.isEmpty()) {
                et.error = "Campo requerido"
                return false
            }
        }
        return true
    }

    private fun validarRango(vararg notas: Double): Boolean {
        return notas.all { it in 0.0..10.0 }
    }
}