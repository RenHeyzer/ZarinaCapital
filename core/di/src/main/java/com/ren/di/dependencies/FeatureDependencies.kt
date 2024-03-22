package com.ren.di.dependencies

import androidx.fragment.app.Fragment

interface FeatureDependencies

typealias FeatureDependenciesProvider = Map<Class<out FeatureDependencies>, @JvmSuppressWildcards FeatureDependencies>

interface HasFeatureDependencies {
    val featureDependencies: FeatureDependenciesProvider
}

inline fun <reified T : FeatureDependencies> Fragment.findComponentDependencies(): T {
    return findFeatureDependenciesProvider()[T::class.java] as T
}

fun Fragment.findFeatureDependenciesProvider(): FeatureDependenciesProvider {
    var current: Fragment? = parentFragment
    while (current !is HasFeatureDependencies?) {
        current = current?.parentFragment
    }

    val hasFeatureDependencies = current ?: when {
        activity is HasFeatureDependencies -> activity as HasFeatureDependencies
        activity?.application is HasFeatureDependencies -> {
            activity?.application as HasFeatureDependencies
        }
        else -> {
            throw IllegalStateException(
                "Can not find suitable feature dependencies provider for $this"
            )
        }
    }
    return hasFeatureDependencies.featureDependencies
}