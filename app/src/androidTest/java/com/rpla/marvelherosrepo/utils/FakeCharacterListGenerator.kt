package com.rpla.marvelherosrepo.utils

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.rpla.marvelherosrepo.home.remote.response.CharacterListResponse
import com.rpla.marvelherosrepo.home.remote.response.CharacterShortInfo
import com.rpla.marvelherosrepo.home.remote.response.DataContainer
import java.io.InputStreamReader

object FakeCharacterListGenerator {
    fun loadCharactersList(context: Context): CharacterListResponse {

        // Leer el archivo JSON desde assets
        val inputStream = context.assets.open("characters_list_template_json.json")
        val reader = InputStreamReader(inputStream)

        // Obtener el nodo "data" y luego "results"
        val jsonObject = Gson().fromJson(reader, Map::class.java)
        val data = jsonObject["data"] as Map<*, *>
        val resultsJson = Gson().toJson(data["results"])

        // Deserializar la lista de personajes
        val listType = object : TypeToken<List<CharacterShortInfo>>() {}.type
        val characters: List<CharacterShortInfo> = Gson().fromJson(resultsJson, listType)

        // Devolver la lista envuelta en el nuevo data class
        return CharacterListResponse(
            status = "OK",
            code = 200,
            data = DataContainer(characters)
        )
    }
}