package com.kevin.kotlinspring.domain.avenger

data class AvengerModel(
    val id: Long? = null,
    val nick: String,
    val person: String,
    val description: String?,
    val history: String?
)
