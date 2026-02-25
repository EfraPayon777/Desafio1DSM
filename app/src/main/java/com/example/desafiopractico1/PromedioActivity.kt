package com.example.desafiopractico1

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import java.text.DecimalFormat

class PromedioActivity : AppCompatActivity() {

    private val df = DecimalFormat("0.00")
    private var nota1Progress = 0.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_promedio)

        val etNombre = findViewById<EditText>(R.id.etNombreEstudiante)
        val sbNota1 = findViewById<SeekBar>(R.id.sbNota1)
        val tvLabelNota1 = findViewById<TextView>(R.id.tvLabelNota1)

        val etNotes = arrayOf(
            findViewById<EditText>(R.id.etNota2),
            findViewById<EditText>(R.id.etNota3),
            findViewById<EditText>(R.id.etNota4),
            findViewById<EditText>(R.id.etNota5)
        )

        val tvResultado = findViewById<TextView>(R.id.tvResultadoPromedio)
        val swResaltar = findViewById<Switch>(R.id.swAprobarMinimo)

        // Configuración de SeekBar para Nota 1
        sbNota1.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                nota1Progress = progress / 10.0
                tvLabelNota1.text = "Nota 1 (15%): $nota1Progress"
            }
            override fun onStartTrackingTouch(seekBar: SeekBar?) {}
            override fun onStopTrackingTouch(seekBar: SeekBar?) {}
        })

        findViewById<Button>(R.id.btnCalcularPromedio).setOnClickListener {
            val nombre = etNombre.text.toString()
            if (nombre.isEmpty()) {
                etNombre.error = "Ingrese nombre"
                return@setOnClickListener
            }

            try {
                // Validación de campos vacíos y rangos
                val n2 = etNotes[0].text.toString().toDouble()
                val n3 = etNotes[1].text.toString().toDouble()
                val n4 = etNotes[2].text.toString().toDouble()
                val n5 = etNotes[3].text.toString().toDouble()

                if (arrayOf(n2, n3, n4, n5).any { it < 0 || it > 10 }) {
                    Toast.makeText(this, "Notas deben ser entre 0 y 10", Toast.LENGTH_SHORT).show()
                    return@setOnClickListener
                }

                // Cálculo ponderado (15%, 15%, 20%, 25%, 25%)
                val promedio = (nota1Progress * 0.15) + (n2 * 0.15) + (n3 * 0.20) + (n4 * 0.25) + (n5 * 0.25)

                val estado = if (promedio >= 6.0) "APROBADO" else "REPROBADO"
                tvResultado.text = "ESTUDIANTE: $nombre\nPROMEDIO: ${df.format(promedio)}\nESTADO: $estado"

                // UI Feedback basado en el Switch
                if (swResaltar.isChecked) {
                    tvResultado.setBackgroundColor(if (promedio >= 6.0) 0xFF4CAF50.toInt() else 0xFFE57373.toInt())
                } else {
                    tvResultado.setBackgroundColor(0xFF263238.toInt())
                }

            } catch (e: Exception) {
                Toast.makeText(this, "Complete todas las notas", Toast.LENGTH_SHORT).show()
            }
        }

        findViewById<Button>(R.id.btnBack).setOnClickListener { finish() }
    }
}