package com.example.musicfinder.model

import com.google.gson.annotations.SerializedName

data class MusicBrainzResponse(
    @SerializedName("artists")
    val artists: List<Artist>
)