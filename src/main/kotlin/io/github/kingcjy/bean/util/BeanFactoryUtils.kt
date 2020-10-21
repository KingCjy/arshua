package io.github.kingcjy.bean.util

import io.github.kingcjy.annotations.PrimaryConstructor
import io.github.kingcjy.bean.BeanFactory
import io.github.kingcjy.bean.MethodParameter
import kotlin.reflect.KClass
import kotlin.reflect.KFunction
import kotlin.reflect.full.hasAnnotation
import kotlin.reflect.full.primaryConstructor


/**
 *  Created by KingCjy on 2020/10/19
 *  Github: https://github.com/KingCjy
 */
fun findPrimaryConstructor(type: KClass<*>)
        = type.constructors.find { it.hasAnnotation<PrimaryConstructor>() }?: type.primaryConstructor!!

fun findParameters(beanFactory: BeanFactory, constructor: KFunction<*>)
        = getMethodParameters(constructor).map { beanFactory.getBean(it.type) }

fun getMethodParameters(function: KFunction<*>)
        = function.parameters.map { MethodParameter(function , it.type.classifier as KClass<*>, it.annotations, it.name!!) }.toTypedArray()