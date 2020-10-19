package io.github.kingcjy.bean.definition

import kotlin.reflect.KClass

interface BeanDefinition {
    val type : KClass<*>
    val name: String
}
