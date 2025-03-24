package com.rpla.marvelherosrepo.remote

import com.rpla.marvelherosrepo.home.remote.request.GetAllCharactersRequest
import com.rpla.marvelherosrepo.home.remote.response.CharacterListResponse
import com.rpla.marvelherosrepo.profile.remote.request.GetCharacterDetailRequest
import com.rpla.marvelherosrepo.profile.remote.response.CharacterDetailResponse
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Test
import java.net.HttpURLConnection


class RestApiTest: BaseTest() {

    @Test
    fun should_get_all_characters_when_server_gives_success_response() = runBlocking {
        val expectedResponse = getExpectedResponse("characters_list_template_json.json", CharacterListResponse::class.java)
        getResponse("characters_list_template_json.json", HttpURLConnection.HTTP_OK)
        val result = restApi.getCharacters(GetAllCharactersRequest(1))
        Assert.assertEquals(expectedResponse.data.results.size, result.data.results.size)
    }

    @Test
    fun should_get_character_details_when_server_gives_success_response() = runBlocking {
        val expectedResponse = getExpectedResponse("character_by_id_template_json.json", CharacterDetailResponse::class.java)
        getResponse("character_by_id_template_json.json", HttpURLConnection.HTTP_OK)
        val result = restApi.getCharacterDetail(GetCharacterDetailRequest(1))
        Assert.assertEquals(expectedResponse.data.results.first().id, result.data.results.first().id)
    }

    @Test
    fun should_throw_client_exception_when_server_sends_4xx_response() {
        Assert.assertThrows(RemoteException.ClientError::class.java) {
            runBlocking {
                getResponse("characters_list_template_json.json", HttpURLConnection.HTTP_BAD_REQUEST)
                restApi.getCharacters(GetAllCharactersRequest(1))
            }
        }
    }

    @Test
    fun should_throw_server_exception_when_server_sends_5xx_response() {
        Assert.assertThrows(RemoteException.ServerError::class.java) {
            runBlocking {
                getResponse("characters_list_template_json.json", HttpURLConnection.HTTP_BAD_GATEWAY)
                restApi.getCharacters(GetAllCharactersRequest(1))
            }
        }
    }

    @Test
    fun should_throw_no_network_exception_in_case_of_timeout() {
        Assert.assertThrows(RemoteException.NoNetworkError::class.java) {
            runBlocking {
                getTimeout()
                restApi.getCharacters(GetAllCharactersRequest(1))
            }
        }
    }
}