package com.example.musicfinder.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.musicfinder.R
import com.example.musicfinder.model.Artist

class ArtistAdapter(
    private var artists: List<Artist>,
    private val onItemClick: (Artist) -> Unit
) : RecyclerView.Adapter<ArtistAdapter.ArtistViewHolder>() {

    inner class ArtistViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val name: TextView = view.findViewById(R.id.artistName)
        val description: TextView = view.findViewById(R.id.artistDesc)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArtistViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_artist, parent, false)
        return ArtistViewHolder(view)
    }

    override fun onBindViewHolder(holder: ArtistViewHolder, position: Int) {
        val artist = artists[position]
        holder.name.text = artist.name
        holder.description.text = artist.disambiguation ?: ""
        holder.itemView.setOnClickListener { onItemClick(artist) }
    }

    override fun getItemCount(): Int = artists.size

    fun updateData(newArtists: List<Artist>) {
        artists = newArtists
        notifyDataSetChanged()
    }
}
