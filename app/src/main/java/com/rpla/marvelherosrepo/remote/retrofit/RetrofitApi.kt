package com.rpla.marvelherosrepo.remote.retrofit

import com.rpla.marvelherosrepo.BuildConfig
import com.rpla.marvelherosrepo.home.remote.response.CharacterListResponse
import com.rpla.marvelherosrepo.profile.remote.response.CharacterComicsListResponse
import com.rpla.marvelherosrepo.profile.remote.response.CharacterDetailResponse
import com.rpla.marvelherosrepo.remote.retrofit.util.RetrofitUtils.generateMD5Hash
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import java.util.Date

interface RetrofitApi {
    @GET("characters")
    suspend fun getCharactersList(
        @Query("ts") timestamp: String? = Date().time.toString(),
        @Query("apikey") key: String? = BuildConfig.PUBLIC_KEY,
        @Query("hash") hash: String? = generateMD5Hash(
            timestamp ?: Date().time.toString(),
            BuildConfig.PRIVATE_KEY,
            BuildConfig.PUBLIC_KEY), //MD5 encoding of ts+privateKey+publicKey
        @Query("offset") offset: Int
    ): CharacterListResponse

    @GET("characters/{characterId}")
    suspend fun getCharacterDetail(
        @Path("characterId") characterId: Int,
        @Query("ts") timestamp: String? = Date().time.toString(),
        @Query("apikey") key: String? = BuildConfig.PUBLIC_KEY,
        @Query("hash") hash: String? = generateMD5Hash(
            timestamp ?: Date().time.toString(),
            BuildConfig.PRIVATE_KEY,
            BuildConfig.PUBLIC_KEY) //MD5 encoding of ts+privateKey+publicKey
    ): CharacterDetailResponse

    @GET("characters/{characterId}/comics")
    suspend fun getCharacterComicsList(
        @Path("characterId") characterId: Int,
        @Query("ts") timestamp: String? = Date().time.toString(),
        @Query("apikey") key: String? = BuildConfig.PUBLIC_KEY,
        @Query("hash") hash: String? = generateMD5Hash(
            timestamp ?: Date().time.toString(),
            BuildConfig.PRIVATE_KEY,
            BuildConfig.PUBLIC_KEY) //MD5 encoding of ts+privateKey+publicKey
    ): CharacterComicsListResponse

}

