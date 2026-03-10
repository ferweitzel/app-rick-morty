package com.example.rickmorty.data

import com.squareup.moshi.Json

data class EpisodeResponseDTO(
    @Json(name = "results") val results: List<EpisodeDTO>
)

data class EpisodeDTO(
    @Json(name = "id") val id: Int,
    @Json(name = "name") val name: String,
    @Json(name = "air_date") val airDate: String,
    @Json(name = "episode") val episode: String,
    @Json(name = "characters") val characters: List<String>


)
