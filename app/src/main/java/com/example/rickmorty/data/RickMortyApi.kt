package com.example.rickmorty.data

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Url

interface RickMortyApi {
    @GET("episode")
    suspend fun getEpisodes(): EpisodeResponseDTO

    @GET("episode/{id}")
    suspend fun getEpisodeById(
        @Path("id") id: Int
    ): EpisodeDTO

    @GET
    suspend fun getCharacterByUrl(@Url url: String): CharacterDTO
}
