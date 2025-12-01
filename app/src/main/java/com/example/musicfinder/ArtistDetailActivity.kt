package com.example.musicfinder

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class ArtistDetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_artist_detail) // Layout de la Pantalla 2

        // ----------------------------------------------------
        // LÓGICA DE NAVEGACIÓN: Regresar (P1) y Avanzar (P3)
        // ----------------------------------------------------

        // 1. Botón para REGRESAR a Pantalla 1 (ID: button2)
        val backButton: Button = findViewById(R.id.button2)
        backButton.setOnClickListener {
            // finish() cierra esta actividad y regresa automáticamente a la anterior (MainActivity)
            finish()
        }

        // 2. Botón para AVANZAR a Pantalla 3 de Favoritos (ID: button3)
        val favoritesButton: Button = findViewById(R.id.button3)
        favoritesButton.setOnClickListener {
            // Crea el Intent hacia la Activity de Destino (FavoritesActivity)
            val intent = Intent(this, FavoritesActivity::class.java)
            startActivity(intent)
        }

        // ----------------------------------------------------
        // FIN DE LÓGICA DE NAVEGACIÓN
        // ----------------------------------------------------

        // Configuración de bordes de ventana
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}