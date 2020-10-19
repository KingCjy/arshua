package io.github.kingcjy.bean.registry

import io.github.kingcjy.bean.definition.BeanDefinition


/**
 *  Created by KingCjy on 2020/10/19
 *  Github: https://github.com/KingCjy
 */
interface BeanRegistry {
    fun registerBeanDefinition(beanDefinition: BeanDefinition)
}