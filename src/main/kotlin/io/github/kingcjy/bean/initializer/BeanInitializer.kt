package io.github.kingcjy.bean.initializer

import io.github.kingcjy.bean.BeanFactory
import io.github.kingcjy.bean.definition.BeanDefinition


/**
 *  Created by KingCjy on 2020/10/19
 *  Github: https://github.com/KingCjy
 */
interface BeanInitializer {
    fun support(beanDefinition: BeanDefinition): Boolean
    fun instantiate(beanDefinition: BeanDefinition, beanFactory: BeanFactory): Any
}