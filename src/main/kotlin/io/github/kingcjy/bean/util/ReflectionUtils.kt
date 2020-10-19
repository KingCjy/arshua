package io.github.kingcjy.bean.util

import io.github.kingcjy.bean.BeanFactory
import io.github.kingcjy.bean.MethodParameter
import java.lang.reflect.Executable
import kotlin.reflect.KClass
import kotlin.reflect.KFunction


/**
 *  Created by KingCjy on 2020/10/19
 *  Github: https://github.com/KingCjy
 */
fun findPrimaryConstructor(type: KClass<*>): KFunction<*> {
    return type.constructors.first()
}

fun findParameters(beanFactory: BeanFactory, constructor: KFunction<*>)
        = getMethodParameters(constructor).map { beanFactory.getBean(it.type) }

private fun getMethodParameters(function: KFunction<*>)
        = function.parameters.map { MethodParameter(function , it.type.classifier as KClass<*>, it.annotations, it.name!!) }.toTypedArray()