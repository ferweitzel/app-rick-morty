package com.example.rickmorty.data

import retrofit2.http.GET

interface RickMortyApi {
    @GET("episode")
    suspend fun getEpisodes(): EpisodeResponse
}
