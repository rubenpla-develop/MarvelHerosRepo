package com.rpla.marvelherosrepo.domain.entity.base

data class Record<out R>(
    val data: R?,
    val error: ErrorRecord?
)
