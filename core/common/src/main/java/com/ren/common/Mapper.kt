package com.ren.common

interface Mappable

interface Mapper<F : Mappable, T : Mappable> {
    fun to(model: F): T
    fun from(model: T): F
}