package com.rpla.marvelherosrepo.domain.entity.base

sealed class ErrorRecord {
    data object ClientError: ErrorRecord()
    data object ServerError: ErrorRecord()
    data object NetworkError: ErrorRecord()
    data object GenericError: ErrorRecord()
}
