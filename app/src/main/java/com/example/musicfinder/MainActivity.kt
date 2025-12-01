package com.example.musicfinder

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        // ----------------------------------------------------
        // LÓGICA DE NAVEGACIÓN: Pantalla 1 a Pantalla 2
        // Usamos el ID de botón: button4
        // ----------------------------------------------------

        // 1. Conexión del Botón: Buscamos el elemento visual con el ID 'button4'
        val goToDetailsButton: Button = findViewById(R.id.button4)

        // 2. Definir la Acción al Hacer Clic (OnClickListener):
        goToDetailsButton.setOnClickListener {
            // Creamos un Intent para ir a la Activity de Destino (Pantalla 2)
            val intent = Intent(this, ArtistDetailActivity::class.java)

            // Iniciamos la nueva Activity (la Pantalla 2)
            startActivity(intent)
        }

        // ----------------------------------------------------
        // FIN DE LÓGICA DE NAVEGACIÓN
        // ----------------------------------------------------

        // Configuración de bordes de ventana (Edge-to-Edge)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}