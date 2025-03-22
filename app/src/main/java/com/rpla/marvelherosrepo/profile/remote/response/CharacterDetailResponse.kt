package com.rpla.marvelherosrepo.profile.remote.response

import com.google.gson.annotations.SerializedName
import com.rpla.marvelherosrepo.profile.domain.entity.CharacterDetailEntity

data class CharacterDetailResponse(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("thumbnail")
    val thumbnail: Thumbnail
)

data class Thumbnail(
    @SerializedName("path") val path: String,
    @SerializedName("extension") val extension: String
)

fun CharacterDetailResponse.toEntity() = CharacterDetailEntity(
    id = id,
    name = name,
    description = description,
    thumbnail = ensureHttpsUrl(thumbnail.path)
        .plus("/standard_amazing.")
        .plus(thumbnail.extension)
)

private fun ensureHttpsUrl(url: String): String {
    return if (url.startsWith("http://")) {
        url.replaceFirst("http://", "https://")
    } else {
        url
    }
}