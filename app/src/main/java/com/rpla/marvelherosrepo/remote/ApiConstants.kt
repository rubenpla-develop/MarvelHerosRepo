package com.rpla.marvelherosrepo.remote

object ApiConstants {
    const val BASE_URL = "https://gateway.marvel.com/v1/public/"
    const val PUBLIC_KEY = "public_key"
    const val PRIVATE_KEY = "private_key"
    const val TIMEOUT = 30L
    const val PAGE_SIZE = 20

    const val STATUS_CODE_500_INTERNAL_ERROR = 500
    const val STATUS_CODE_409_MISSING_QUERY_PARAM = 409
    const val STATUS_CODE_401_INVALID_REFERRER_OR_HASH = 401
    const val STATUS_CODE_405_METHOD_NOT_ALLOWED = 405
    const val STATUS_CODE_403_FORBIDDEN = 403
}
