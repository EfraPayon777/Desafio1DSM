package com.example.desafiopractico1

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlin.math.pow
import kotlin.math.sqrt

class CalculadoraActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calculadora)

        val etN1 = findViewById<EditText>(R.id.etNum1)
        val etN2 = findViewById<EditText>(R.id.etNum2)
        val tvRes = findViewById<TextView>(R.id.tvResultadoCalc)


        findViewById<Button>(R.id.btnSuma).setOnClickListener { operar(etN1, etN2, tvRes, "+") }
        findViewById<Button>(R.id.btnResta).setOnClickListener { operar(etN1, etN2, tvRes, "-") }
        findViewById<Button>(R.id.btnMulti).setOnClickListener { operar(etN1, etN2, tvRes, "*") }
        findViewById<Button>(R.id.btnDiv).setOnClickListener { operar(etN1, etN2, tvRes, "/") }


        findViewById<Button>(R.id.btnExp).setOnClickListener { operar(etN1, etN2, tvRes, "pow") }
        findViewById<Button>(R.id.btnRaiz).setOnClickListener { operar(etN1, etN2, tvRes, "sqrt") }

        findViewById<Button>(R.id.btnRegresar).setOnClickListener { finish() }
    }

    private fun operar(et1: EditText, et2: EditText, tv: TextView, op: String) {
        val s1 = et1.text.toString()

        if (s1.isEmpty()) {
            et1.error = "Ingrese el primer número"
            return
        }

        val n1 = s1.toDouble()


        if (op == "sqrt") {
            if (n1 < 0) {
                et1.error = "No existe raíz de números negativos"
            } else {
                tv.text = "Resultado: ${sqrt(n1)}"
            }
            return
        }


        val s2 = et2.text.toString()
        if (s2.isEmpty()) {
            et2.error = "Ingrese el segundo número"
            return
        }
        val n2 = s2.toDouble()

        val res = when (op) {
            "+" -> n1 + n2
            "-" -> n1 - n2
            "*" -> n1 * n2
            "/" -> if (n2 != 0.0) n1 / n2 else {
                et2.error = "No se puede dividir entre 0"
                null
            }
            "pow" -> n1.pow(n2)
            else -> 0.0
        }

        if (res != null) {
            tv.text = "Resultado: $res"
        }
    }
}