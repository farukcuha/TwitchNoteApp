package com.farukcuha.twitchnoteapp.domain.model

data class Note(
    val id: Int? = null,
    var title: String?,
    val body: String?,
    val time: Long?
)