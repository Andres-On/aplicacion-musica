package com.example.musicfinder

import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class FavoritesActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_favorites) // Layout de la Pantalla 3

        // ----------------------------------------------------
        // LÓGICA DE NAVEGACIÓN: Regresar a Pantalla 2
        // ----------------------------------------------------

        // Conectamos el botón de regreso (ID: button)
        val backButton: Button = findViewById(R.id.button)
        backButton.setOnClickListener {
            // finish() cierra esta actividad y regresa a ArtistDetailActivity (P2)
            finish()
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