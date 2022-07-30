package com.kevin.kotlinspring.domain.avenger

interface AvengerRepository {
    fun getAvengers(): List<AvengerModel>
    fun getDetail(id: Long): AvengerModel?
    fun create(avenger: AvengerModel): AvengerModel
    fun delete(id: Long)
    fun update(avenger: AvengerModel): AvengerModel
}