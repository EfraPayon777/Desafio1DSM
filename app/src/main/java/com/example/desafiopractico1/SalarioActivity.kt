package com.example.desafiopractico1

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.text.DecimalFormat

class SalarioActivity : AppCompatActivity() {

    private val df = DecimalFormat("$#,##0.00")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_salario)

        val etNombre = findViewById<EditText>(R.id.etNombreEmpleado)
        val etSalarioBase = findViewById<EditText>(R.id.etSalarioBase)
        val btnCalcular = findViewById<Button>(R.id.btnCalcularSalario)

        // Vistas de resultados
        val tvDetalle = findViewById<TextView>(R.id.tvDetalleEmpleado)
        val tvISSS = findViewById<TextView>(R.id.tvISSS)
        val tvAFP = findViewById<TextView>(R.id.tvAFP)
        val tvRenta = findViewById<TextView>(R.id.tvRenta)
        val tvLiquido = findViewById<TextView>(R.id.tvSalarioLiquido)

        btnCalcular.setOnClickListener {
            val nombre = etNombre.text.toString()
            val salarioStr = etSalarioBase.text.toString()

            if (nombre.isEmpty()) {
                etNombre.error = "Ingrese nombre"
                return@setOnClickListener
            }
            if (salarioStr.isEmpty() || salarioStr.toDouble() <= 0) {
                etSalarioBase.error = "Ingrese un salario positivo"
                return@setOnClickListener
            }

            val salarioBase = salarioStr.toDouble()

            // 1. Calcular deducciones fijas
            val isss = salarioBase * 0.03
            val afp = salarioBase * 0.0725

            // 2. Monto imponible para Renta (Salario Base - ISSS - AFP)
            val montoImponible = salarioBase - isss - afp

            // 3. Calcular Renta usando la función de tramos
            val renta = calcularRenta(montoImponible)

            // 4. Salario Líquido Final
            val salarioLiquido = montoImponible - renta

            // Mostrar resultados
            tvDetalle.text = "Empleado: $nombre"
            tvISSS.text = "ISSS (3%): ${df.format(isss)}"
            tvAFP.text = "AFP (7.25%): ${df.format(afp)}"
            tvRenta.text = "Renta: ${df.format(renta)}"
            tvLiquido.text = "Líquido: ${df.format(salarioLiquido)}"
        }

        findViewById<Button>(R.id.btnBack).setOnClickListener { finish() }
    }

    /**
     * Lógica de tramos de Renta 
     */
    private fun calcularRenta(monto: Double): Double {
        return when {
            monto <= 472.00 -> 0.0
            monto <= 895.24 -> (monto - 472.00) * 0.10 + 17.67
            monto <= 2038.10 -> (monto - 895.24) * 0.20 + 60.00
            else -> (monto - 2038.10) * 0.30 + 288.57
        }
    }
}