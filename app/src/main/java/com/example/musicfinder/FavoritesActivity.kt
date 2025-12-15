package com.example.musicfinder

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.musicfinder.ui.FavoritesManager
import com.example.musicfinder.ui.Release
import com.example.musicfinder.ui.ReleaseAdapter

class FavoritesActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var releaseAdapter: ReleaseAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_favorites)

        val backButton: Button = findViewById(R.id.button)
        backButton.setOnClickListener { finish() }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        recyclerView = findViewById(R.id.recyclerView3)
        recyclerView.layoutManager = LinearLayoutManager(this) // Vertical

        // ✅ Adapter con item_favorite.xml
        releaseAdapter = ReleaseAdapter(
            releases = FavoritesManager.getFavorites(),
            isFavoriteList = true
        ) { release ->
            // Click en favorito: abrir ArtistDetailActivity
            val intent = Intent(this, ArtistDetailActivity::class.java)
            intent.putExtra("ARTIST_ID", release.artistId ?: "")
            intent.putExtra("ARTIST_NAME", release.title)
            startActivity(intent)
        }

        recyclerView.adapter = releaseAdapter
    }

    override fun onResume() {
        super.onResume()
        // Actualiza la lista de favoritos al regresar a esta pantalla
        releaseAdapter = ReleaseAdapter(
            releases = FavoritesManager.getFavorites(),
            isFavoriteList = true
        ) { release ->
            val intent = Intent(this, ArtistDetailActivity::class.java)
            intent.putExtra("ARTIST_ID", release.artistId ?: "")
            intent.putExtra("ARTIST_NAME", release.title)
            startActivity(intent)
        }
        recyclerView.adapter = releaseAdapter
    }
}