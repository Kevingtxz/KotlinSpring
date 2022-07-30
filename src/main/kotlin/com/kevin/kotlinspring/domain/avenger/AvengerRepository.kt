package com.kevin.kotlinspring.domain.avenger

interface AvengerRepository {
    fun getAvengers(): List<Avenger>
    fun getDetail(id: Long): Avenger?
    fun create(avenger: Avenger): Avenger
    fun delete(id: Long)
    fun update(avenger: Avenger): Avenger
}