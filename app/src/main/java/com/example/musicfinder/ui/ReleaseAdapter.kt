package com.example.musicfinder.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.musicfinder.R
import com.squareup.picasso.Picasso

data class Release(
    val title: String,
    val imageUrl: String,
    val artistId: String? = null
)

class ReleaseAdapter(
    private var releases: List<Release>,
    private val isFavoriteList: Boolean = false, // ⚡ Nuevo parámetro
    private val onItemClick: ((Release) -> Unit)? = null
) : RecyclerView.Adapter<ReleaseAdapter.ReleaseViewHolder>() {

    inner class ReleaseViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val title: TextView = if (isFavoriteList) view.findViewById(R.id.favArtistName)
        else view.findViewById(R.id.releaseTitle)
        val image: ImageView = if (isFavoriteList) view.findViewById(R.id.favArtistImage)
        else view.findViewById(R.id.releaseImage)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReleaseViewHolder {
        val layoutId = if (isFavoriteList) R.layout.item_favorite else R.layout.item_release
        val view = LayoutInflater.from(parent.context).inflate(layoutId, parent, false)
        return ReleaseViewHolder(view)
    }

    override fun onBindViewHolder(holder: ReleaseViewHolder, position: Int) {
        val release = releases[position]
        holder.title.text = release.title
        if (release.imageUrl.isNotEmpty()) {
            Picasso.get().load(release.imageUrl).into(holder.image)
        } else {
            holder.image.visibility = View.GONE
        }

        holder.itemView.setOnClickListener {
            onItemClick?.invoke(release)
        }
    }

    override fun getItemCount(): Int = releases.size

    fun getFirstReleaseImage(): String = if (releases.isNotEmpty()) releases[0].imageUrl else ""

    fun getReleases(): List<Release> = releases
}