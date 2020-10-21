package io.github.kingcjy.bean.initializer

import io.github.kingcjy.bean.BeanFactory
import io.github.kingcjy.bean.definition.BeanDefinition
import io.github.kingcjy.bean.definition.ClassBeanDefinition
import io.github.kingcjy.bean.util.findParameters
import io.github.kingcjy.bean.util.findPrimaryConstructor
import java.util.*


/**
 *  Created by KingCjy on 2020/10/19
 *  Github: https://github.com/KingCjy
 */
class ClassBeanInitializer: BeanInitializer {
    override fun support(beanDefinition: BeanDefinition) = beanDefinition is ClassBeanDefinition

    override fun instantiate(beanDefinition: BeanDefinition, beanFactory: BeanFactory): Any {
        val constructor = findPrimaryConstructor(beanDefinition.type)
        val parameters = findParameters(beanFactory, constructor).toTypedArray()

        return constructor.call(*parameters)
    }
}