package com.kevin.kotlinspring.application.web.resource

import com.kevin.kotlinspring.application.web.resource.request.AvengerRequest
import com.kevin.kotlinspring.application.web.resource.response.AvengerResponse
import com.kevin.kotlinspring.domain.avenger.AvengerModel
import com.kevin.kotlinspring.domain.avenger.AvengerRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.net.URI
import javax.validation.Valid


private const val API_PATH = "/v1/api/avenger"

@RestController
@RequestMapping(value = [API_PATH])
class AvengerResource(
    @Autowired private val repo: AvengerRepository
) {


    @GetMapping
    fun getAvengers() = repo.getAvengers()
        .map { AvengerResponse.from(it) }
        .let {
            ResponseEntity.ok().body(it)
        }

    @GetMapping("{id}")
    fun getAvengersDetails(@PathVariable id: Long) =
        repo.getDetail(id)?.let {
            ResponseEntity.ok().body(AvengerResponse.from(it))
        } ?: ResponseEntity.notFound().build<Void>()

    @PostMapping
    fun createAvenger(@Valid @RequestBody request: AvengerRequest) =
        request.toAvengerModel().run {
            repo.create(this)
        }.let {
            ResponseEntity
                .created(URI("$API_PATH/${it.id}"))
                .body(AvengerResponse.from(it))
        }

    @PutMapping("{id}")
    fun updateAvenger(@Valid @RequestBody request: AvengerRequest,
                      @PathVariable("id") id: Long) =
        repo.getDetail(id)?.let {
            AvengerRequest.to(it.id, request).apply {
                repo.update(this)
            }.let {
                avenger -> ResponseEntity.accepted().body(AvengerResponse.from(avenger))
            }
        } ?: ResponseEntity.notFound().build<Void>()

    @DeleteMapping("{id}")
    fun deleteAvenger(@PathVariable("id") id: Long) =
        repo.delete(id).let {
            ResponseEntity.accepted().build<Void>()
        }

}