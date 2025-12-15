package com.example.musicfinder

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.musicfinder.ui.FavoritesManager
import com.example.musicfinder.ui.Release
import com.example.musicfinder.ui.ReleaseAdapter
import com.squareup.picasso.Picasso
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.json.JSONObject
import java.net.URL

class ArtistDetailActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var releaseAdapter: ReleaseAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_artist_detail)

        val nameTextView: TextView = findViewById(R.id.textView)
        val bioTextView: TextView = findViewById(R.id.textView3)
        val artistImage: ImageView = findViewById(R.id.artistImage)
        recyclerView = findViewById(R.id.recyclerView2)

        val backButton: Button = findViewById(R.id.button2)
        val goFavoritesButton: Button = findViewById(R.id.button3)
        val addFavoriteButton: Button = findViewById(R.id.button5)

        backButton.setOnClickListener { finish() }
        goFavoritesButton.setOnClickListener {
            startActivity(Intent(this, FavoritesActivity::class.java))
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // ----------------- Recibir datos -----------------
        val artistId = intent.getStringExtra("ARTIST_ID") ?: ""
        val artistName = intent.getStringExtra("ARTIST_NAME") ?: "Artista"
        nameTextView.text = artistName

        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        releaseAdapter = ReleaseAdapter(emptyList())
        recyclerView.adapter = releaseAdapter

        // ----------------- Biografía -----------------
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val url =
                    "https://en.wikipedia.org/api/rest_v1/page/summary/${artistName.replace(" ", "_")}"
                val responseText = URL(url).readText()
                val json = JSONObject(responseText)
                val summary = json.optString("extract", "Biografía no disponible")

                withContext(Dispatchers.Main) {
                    bioTextView.text = summary
                }
            } catch (e: Exception) {
                e.printStackTrace()
                withContext(Dispatchers.Main) {
                    bioTextView.text = "Error al cargar información"
                }
            }
        }

        // ----------------- Cargar releases -----------------
        if (artistId.isNotEmpty()) {
            CoroutineScope(Dispatchers.IO).launch {
                try {
                    val releaseUrl =
                        "https://musicbrainz.org/ws/2/release-group?artist=$artistId&fmt=json&limit=50"
                    val releaseJson = JSONObject(URL(releaseUrl).readText())
                    val releaseArray = releaseJson.optJSONArray("release-groups")
                    val releaseList = mutableListOf<Release>()

                    if (releaseArray != null && releaseArray.length() > 0) {
                        for (i in 0 until releaseArray.length()) {
                            val releaseObj = releaseArray.getJSONObject(i)
                            val releaseId = releaseObj.getString("id")
                            val title = releaseObj.getString("title")
                            val imageUrl = "https://coverartarchive.org/release-group/$releaseId/front-250"
                            releaseList.add(Release(title, imageUrl, artistId))
                        }

                        withContext(Dispatchers.Main) {
                            if (releaseList.isNotEmpty()) {
                                Picasso.get().load(releaseList[0].imageUrl).into(artistImage)
                            }
                            releaseAdapter = ReleaseAdapter(releaseList)
                            recyclerView.adapter = releaseAdapter
                        }
                    }
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        }

        // ----------------- Botón Agregar a Favoritos -----------------
        addFavoriteButton.setOnClickListener {
            val mainImageUrl = releaseAdapter.getFirstReleaseImage()
            val favoriteArtist = Release(title = artistName, imageUrl = mainImageUrl, artistId = artistId)
            FavoritesManager.addFavorite(favoriteArtist)
        }
    }
}
