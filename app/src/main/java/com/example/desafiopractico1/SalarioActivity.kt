package com.example.desafiopractico1

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.text.DecimalFormat

class SalarioActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_salario)

        val etNombre = findViewById<EditText>(R.id.etNombreEmpleado)
        val etSalario = findViewById<EditText>(R.id.etSalarioBase)
        val btnCalcular = findViewById<Button>(R.id.btnCalcularSalario)
        val tvDetalle = findViewById<TextView>(R.id.tvDetalleSalario)
        val btnRegresar = findViewById<Button>(R.id.btnRegresar)

        btnCalcular.setOnClickListener {
            val baseStr = etSalario.text.toString()
            if (baseStr.isNotEmpty()) {
                val salarioBase = baseStr.toDouble()
                if (salarioBase > 0) {
                    procesarSalario(etNombre.text.toString(), salarioBase, tvDetalle)
                } else {
                    etSalario.error = "El salario debe ser positivo"
                }
            }
        }

        btnRegresar.setOnClickListener { finish() }
    }

    private fun procesarSalario(nombre: String, base: Double, vista: TextView) {
        val isss = base * 0.03
        val afp = base * 0.0725
        val mImponible = base - isss - afp
        val renta = calcularRenta(mImponible)
        val liquido = mImponible - renta

        val df = DecimalFormat("$#,##0.00")
        vista.text = """
            Empleado: $nombre
            Salario Base: ${df.format(base)}
            (-) ISSS (3%): ${df.format(isss)}
            (-) AFP (7.25%): ${df.format(afp)}
            --------------------------
            Monto Gravable: ${df.format(mImponible)}
            (-) Renta: ${df.format(renta)}
            --------------------------
            SALARIO LÍQUIDO: ${df.format(liquido)}
        """.trimIndent()
    }

    private fun calcularRenta(monto: Double): Double {
        return when {
            monto <= 472.00 -> 0.0
            monto <= 895.24 -> (monto - 472.00) * 0.10 + 17.67
            monto <= 2038.10 -> (monto - 895.24) * 0.20 + 60.00
            else -> (monto - 2038.10) * 0.30 + 288.57
        }
    }
}