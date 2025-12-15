package com.example.musicfinder.network

import com.example.musicfinder.model.MusicBrainzResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface MusicBrainzApi {

    @GET("artist")
    suspend fun searchArtist(
        @Query("query") query: String,
        @Query("limit") limit: Int = 20,
        @Query("fmt") format: String = "json"
    ): MusicBrainzResponse
}