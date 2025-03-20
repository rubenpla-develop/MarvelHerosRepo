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
    thumbnail = thumbnail.path.plus("/landscape_xlarge.".plus(thumbnail.extension))
)
