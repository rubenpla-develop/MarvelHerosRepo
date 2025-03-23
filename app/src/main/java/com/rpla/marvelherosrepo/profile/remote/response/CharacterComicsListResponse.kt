package com.rpla.marvelherosrepo.profile.remote.response

import com.google.gson.annotations.SerializedName
import com.rpla.marvelherosrepo.profile.domain.entity.ComicsListResultsEntity

data class CharacterComicsListResponse(
    @SerializedName("code")
    val code: Int,
    @SerializedName("status")
    val status: String,
    @SerializedName("data")
    val data: ComicsListDataContainer
)

data class ComicsListDataContainer(
    @SerializedName("results") val results:  List<ComicsListShortInfo>
)

data class ComicsListShortInfo(
    @SerializedName("id")
    val id: Int,
    @SerializedName("title")
    val name: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("modified")
    val modified: String,
    @SerializedName("thumbnail")
    val thumbnail: ComicsListThumbnail
)

data class ComicsListThumbnail(
    @SerializedName("path") val path: String,
    @SerializedName("extension") val extension: String
)

fun ComicsListShortInfo.toEntity() = ComicsListResultsEntity(
    id = id,
    name = name,
    description = description,
    modified = modified,
    thumbnail = ensureHttpsUrl(thumbnail.path)
        .plus("/portrait_fantastic.")
        .plus(thumbnail.extension)
)

fun List<ComicsListShortInfo>.toEntity() = map { it.toEntity() }

private fun ensureHttpsUrl(url: String): String {
    return if (url.startsWith("http://")) {
        url.replaceFirst("http://", "https://")
    } else {
        url
    }
}