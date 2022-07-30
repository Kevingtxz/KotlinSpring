package com.kevin.kotlinspring.application.web.resource.response

import com.kevin.kotlinspring.domain.avenger.AvengerModel

data class AvengerResponse(
    val id: Long?,
    val nick: String,
    val person: String,
    val description: String?,
    val history: String?
) {
    companion object {
        fun from(avenger: AvengerModel) = AvengerResponse(
            id = avenger.id,
            nick = avenger.nick,
            person = avenger.person,
            description = avenger.description,
            history = avenger.history,
        )
    }
}
