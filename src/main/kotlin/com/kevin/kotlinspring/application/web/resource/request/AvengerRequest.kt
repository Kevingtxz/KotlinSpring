package com.kevin.kotlinspring.application.web.resource.request

import com.kevin.kotlinspring.domain.avenger.AvengerModel
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotEmpty
import javax.validation.constraints.NotNull


data class AvengerRequest(
    @field:NotNull
    @field:NotBlank
    @field:NotEmpty
    val nick: String,
    @field:NotNull
    @field:NotBlank
    @field:NotEmpty
    val person: String,
    val description: String? = "",
    val history: String? = ""
) {
    fun toAvengerModel() = AvengerModel(
        nick = nick,
        person = person,
        description = description,
        history = history
    )

    companion object {
        fun to(id: Long?, request: AvengerRequest) = AvengerModel(
            id = id,
            nick = request.nick,
            person = request.person,
            description = request.description,
            history = request.history
        )
    }

}

