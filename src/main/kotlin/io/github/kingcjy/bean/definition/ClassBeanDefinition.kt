package io.github.kingcjy.bean.definition

import kotlin.reflect.KClass


/**
 *  Created by KingCjy on 2020/10/19
 *  Github: https://github.com/KingCjy
 */
data class ClassBeanDefinition(
    override val type: KClass<*>,
    override val name: String
): BeanDefinition {
    constructor(type: KClass<*>): this(type, type.qualifiedName!!)
}