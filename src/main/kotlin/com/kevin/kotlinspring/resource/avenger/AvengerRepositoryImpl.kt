package com.kevin.kotlinspring.resource.avenger

import com.kevin.kotlinspring.domain.avenger.Avenger
import com.kevin.kotlinspring.domain.avenger.AvengerRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Component

@Component
class AvengerRepositoryImpl(
    @Autowired private val repo : AvengerEntityRepository
) : AvengerRepository {

    override fun getAvengers(): List<Avenger> =
            repo.findAll().map { it.toAvenger() }

    override fun getDetail(id: Long): Avenger? =
            repo.findByIdOrNull(id)?.let { it.toAvenger() }

    override fun create(avenger: Avenger): Avenger =
            repo.save(AvengerEntity.from(avenger))
                .toAvenger()

    override fun delete(id: Long) = repo.deleteById(id)

    override fun update(avenger: Avenger): Avenger =
            repo.save(AvengerEntity.from(avenger))
                .toAvenger()
}