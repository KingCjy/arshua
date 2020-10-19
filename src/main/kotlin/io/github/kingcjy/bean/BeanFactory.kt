package io.github.kingcjy.bean

import kotlin.reflect.KClass

interface BeanFactory {
    fun <T : Any> getBean(type: KClass<T>): T
}

inline fun <reified T: Any> BeanFactory.get() = this.getBean(T::class)
