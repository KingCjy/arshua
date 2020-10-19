package io.github.kingcjy.bean

import java.lang.reflect.Executable
import kotlin.reflect.KAnnotatedElement
import kotlin.reflect.KClass
import kotlin.reflect.KFunction


/**
 *  Created by KingCjy on 2020/10/19
 *  Github: https://github.com/KingCjy
 */
data class MethodParameter(
    val function: KFunction<*>,
    val type: KClass<*>,
    val annotations: List<Annotation>,
    val name: String
) {

    fun <T: Annotation> getAnnotation(requireType: KClass<T>) = annotations.first { requireType == it.annotationClass }
}