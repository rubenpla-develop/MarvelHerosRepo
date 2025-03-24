package com.rpla.marvelherosrepo.home.data.repository

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.rpla.marvelherosrepo.data.DataSource
import com.rpla.marvelherosrepo.home.data.mapper.CharactersMapper
import com.rpla.marvelherosrepo.data.mapper.ErrorMapper
import com.rpla.marvelherosrepo.domain.entity.base.Record
import com.rpla.marvelherosrepo.home.domain.entity.CharactersEntity
import com.rpla.marvelherosrepo.home.domain.repository.CharacterRepository
import com.rpla.marvelherosrepo.home.remote.request.GetAllCharactersRequest
import com.rpla.marvelherosrepo.home.remote.response.CharacterListResponse
import com.rpla.marvelherosrepo.home.remote.response.CharacterShortInfo
import com.rpla.marvelherosrepo.home.remote.response.DataContainer
import com.rpla.marvelherosrepo.remote.RemoteException
import java.io.InputStreamReader
import javax.inject.Inject
import kotlin.random.Random

class CharacterRepositoryImpl @Inject constructor(private val context: Context,
    private val dataSource: DataSource) :
    CharacterRepository {

    private val charactersMapper = CharactersMapper()
    private val errorMapper = ErrorMapper()


    override suspend fun getCharacters(nextPage: Int): Record<CharactersEntity> {
        return try {
            dataSource.api().restApi()
                .getCharacters(getAllCharactersRequest = GetAllCharactersRequest(nextPage)).run {
                    charactersMapper.mapCharactersResponse(this)
                }
        } catch (error : RemoteException) {
            charactersMapper.mapCharactersResponse(loadRandomizedHeroes(context))
            //errorMapper.mapErrorRecord(error)
        }
    }
}

fun loadRandomizedHeroes(context: Context): CharacterListResponse {

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
        data = DataContainer(characters.shuffled(Random(System.currentTimeMillis())))
    )
}