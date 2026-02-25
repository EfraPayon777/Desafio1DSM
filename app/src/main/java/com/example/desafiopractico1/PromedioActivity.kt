package com.example.desafiopractico1

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.switchmaterial.SwitchMaterial
import java.text.DecimalFormat

class PromedioActivity : AppCompatActivity() {

    private var nota1Progress = 0.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_promedio)


        val etNombre = findViewById<EditText>(R.id.etNombreEstudiante)
        val sbNota1 = findViewById<SeekBar>(R.id.sbNota1)
        val tvLabelNota1 = findViewById<TextView>(R.id.tvLabelNota1)
        val etNota2 = findViewById<EditText>(R.id.etNota2)
        val etNota3 = findViewById<EditText>(R.id.etNota3)
        val etNota4 = findViewById<EditText>(R.id.etNota4)
        val etNota5 = findViewById<EditText>(R.id.etNota5)
        val swResaltar = findViewById<SwitchMaterial>(R.id.swAprobarMinimo)
        val btnCalcular = findViewById<Button>(R.id.btnCalcularPromedio)
        val tvResultado = findViewById<TextView>(R.id.tvResultadoPromedio)
        val btnBack = findViewById<Button>(R.id.btnBack)


        sbNota1.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                nota1Progress = progress / 10.0
                tvLabelNota1.text = "Nota 1 (15%): $nota1Progress"
            }
            override fun onStartTrackingTouch(seekBar: SeekBar?) {}
            override fun onStopTrackingTouch(seekBar: SeekBar?) {}
        })

        btnCalcular.setOnClickListener {
            val nombre = etNombre.text.toString()
            if (nombre.isEmpty()) {
                etNombre.error = "Ingrese nombre"
                return@setOnClickListener
            }

            try {
                val n2 = etNota2.text.toString().toDouble()
                val n3 = etNota3.text.toString().toDouble()
                val n4 = etNota4.text.toString().toDouble()
                val n5 = etNota5.text.toString().toDouble()

                if (n2 !in 0.0..10.0 || n3 !in 0.0..10.0 || n4 !in 0.0..10.0 || n5 !in 0.0..10.0) {
                    Toast.makeText(this, "Notas deben estar entre 0 y 10", Toast.LENGTH_SHORT).show()
                    return@setOnClickListener
                }

                val promedio = (nota1Progress * 0.15) + (n2 * 0.15) + (n3 * 0.20) + (n4 * 0.25) + (n5 * 0.25)
                val df = DecimalFormat("0.00")
                val estado = if (promedio >= 6.0) "APROBADO" else "REPROBADO"

                tvResultado.text = "Estudiante: $nombre\nPromedio: ${df.format(promedio)}\nEstado: $estado"

                if (swResaltar.isChecked) {
                    val color = if (promedio >= 6.0) 0xFF4CAF50.toInt() else 0xFFE57373.toInt()
                    tvResultado.setBackgroundColor(color)
                } else {
                    tvResultado.setBackgroundColor(0xFF263238.toInt())
                }

            } catch (e: Exception) {
                Toast.makeText(this, "Por favor complete todas las notas", Toast.LENGTH_SHORT).show()
            }
        }

        btnBack.setOnClickListener { finish() }
    }
}