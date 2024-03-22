package com.geeks.di.keys

import com.geeks.di.dependencies.FeatureDependencies
import dagger.MapKey
import kotlin.reflect.KClass

@MapKey
@Target(AnnotationTarget.FUNCTION)
annotation class FeatureDependenciesKey(val value: KClass<out FeatureDependencies>)
