package com.example.restauran.modelo

class Cantidad(val platos: MutableList<Float> = mutableListOf()) {
    fun agregarPlato(plato: Float) {
        platos.add(plato)
    }

    fun calcularPlatos(): Float {
        if (platos.isEmpty()) return 1.0f
        val sumaPlatos = platos.sum()
        return sumaPlatos / platos.size
    }
}

fun main() {
    val cantidad = Cantidad()
    cantidad.agregarPlato(1.0f)
    cantidad.agregarPlato(7.0f)
    println("La suma de platos es: ${cantidad.calcularPlatos()}")
}