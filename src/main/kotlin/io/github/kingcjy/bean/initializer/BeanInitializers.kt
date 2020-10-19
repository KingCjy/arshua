package io.github.kingcjy.bean.initializer

import io.github.kingcjy.bean.BeanFactory
import io.github.kingcjy.bean.definition.BeanDefinition


/**
 *  Created by KingCjy on 2020/10/19
 *  Github: https://github.com/KingCjy
 */
class BeanInitializers(
    vararg beanInitializerArgs: BeanInitializer
): BeanInitializer {

    private val beanInitializers = mutableSetOf(*beanInitializerArgs)

    override fun support(beanDefinition: BeanDefinition) = beanInitializers.any { it.support(beanDefinition) }

    override fun instantiate(beanDefinition: BeanDefinition, beanFactory: BeanFactory)
        = beanInitializers.first { it.support(beanDefinition) }.instantiate(beanDefinition, beanFactory)

}