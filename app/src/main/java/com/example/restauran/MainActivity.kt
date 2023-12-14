package com.example.restauran

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Switch
import android.view.View

class MainActivity : AppCompatActivity() {
    private val precioPlato1 = 4.500 // Precio del Plato 1
    private val precioPlato2 = 5.500 // Precio del Plato 2
    private var etPlato1: EditText? = null
    private var etPlato2: EditText? = null
    private var tvCalcular: TextView? = null
    private var switchPropina: Switch? = null
    private var botonCalcularPlato: Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        etPlato1 = findViewById(R.id.etPlato1)
        etPlato2 = findViewById(R.id.etPlato2)
        tvCalcular = findViewById(R.id.tvCalcular)
        switchPropina = findViewById(R.id.switchCalcularAutomaticamente)
        botonCalcularPlato = findViewById(R.id.btnCalcularPlato)

        botonCalcularPlato?.visibility = View.GONE // Oculta el botón

        switchPropina?.setOnCheckedChangeListener { _, isChecked ->
            calcularTotal(isChecked)
        }

        etPlato1?.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                calcularTotal()
            }
            override fun afterTextChanged(s: Editable?) {}
        })

        etPlato2?.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                calcularTotal()
            }
            override fun afterTextChanged(s: Editable?) {}
        })
    }

    private fun calcularTotal(aplicarPropina: Boolean = false) {
        val cantidadPlato1 = etPlato1?.text.toString().toFloatOrNull() ?: 0f
        val cantidadPlato2 = etPlato2?.text.toString().toFloatOrNull() ?: 0f

        val subtotal = (cantidadPlato1 * precioPlato1) + (cantidadPlato2 * precioPlato2)
        var total = subtotal

        if (aplicarPropina) {
            val propina = subtotal * 0.10 // Calcula el 10% de propina
            total += propina
        }

        val totalFormateado = "%.3f".format(total) // Formatea el total a tres decimales
        tvCalcular?.text = "Total: $totalFormateado"
    }
}
