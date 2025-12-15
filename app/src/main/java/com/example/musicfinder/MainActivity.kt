package com.example.musicfinder

import android.content.Intent
import android.os.Bundle
import android.widget.SearchView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.musicfinder.network.RetrofitClient
import com.example.musicfinder.ui.ArtistAdapter
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var artistAdapter: ArtistAdapter
    private lateinit var searchView: SearchView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        // ---------------- RecyclerView ----------------
        recyclerView = findViewById(R.id.recyclerView)

        // ✅ Adapter con click que abre Pantalla 2
        artistAdapter = ArtistAdapter(emptyList()) { artist ->
            val intent = Intent(this, ArtistDetailActivity::class.java)
            intent.putExtra("ARTIST_ID", artist.id)
            intent.putExtra("ARTIST_NAME", artist.name)
            startActivity(intent)
        }

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = artistAdapter

        // ---------------- SearchView ----------------
        searchView = findViewById(R.id.searchView)
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                if (!query.isNullOrBlank()) {
                    searchArtists(query)
                }
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                if (!newText.isNullOrBlank()) {
                    searchArtists(newText)
                }
                return true
            }
        })

        // ---------------- Edge to Edge ----------------
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    // ---------------- MusicBrainz ----------------
    private fun searchArtists(query: String) {
        lifecycleScope.launch {
            try {
                val response = RetrofitClient.api.searchArtist(query)
                artistAdapter.updateData(response.artists)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}