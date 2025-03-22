package com.rpla.marvelherosrepo.home.remote.response

import com.google.gson.annotations.SerializedName
import com.rpla.marvelherosrepo.home.domain.entity.CharacterResultsEntity

data class CharacterListResponse(
    @SerializedName("code")
    val code: Int,
    @SerializedName("status")
    val status: String,
    @SerializedName("data")
    val data: DataContainer
)

data class DataContainer(
    @SerializedName("results") val results:  List<CharacterShortInfo>
)

data class CharacterShortInfo(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("thumbnail")
    val thumbnail: Thumbnail,
    @SerializedName("comics")
    val comics: ComicList
)

data class Thumbnail(
    @SerializedName("path") val path: String,
    @SerializedName("extension") val extension: String
)

data class ComicList(
    @SerializedName("items") val items: List<ComicSummary>,
)

data class ComicSummary(
    @SerializedName("name") val name: String
)

fun CharacterShortInfo.toEntity() = CharacterResultsEntity(
    id = id,
    name = name,
    thumbnail = ensureHttpsUrl(thumbnail.path).plus("/portrait_fantastic.".plus(thumbnail.extension)),
    comicsListSize = comics.items.size
)

fun List<CharacterShortInfo>.toEntity() = map { it.toEntity() }

private fun ensureHttpsUrl(url: String): String {
    return if (url.startsWith("http://")) {
        url.replaceFirst("http://", "https://")
    } else {
        url
    }
}