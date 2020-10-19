package io.github.kingcjy.bean

import io.github.kingcjy.bean.definition.BeanDefinition
import io.github.kingcjy.bean.initializer.BeanInitializer
import io.github.kingcjy.bean.initializer.BeanInitializers
import io.github.kingcjy.bean.registry.BeanRegistry
import kotlin.reflect.KClass

class DefaultBeanFactory(
    vararg beanInitializerArgs: BeanInitializer
): BeanFactory, BeanRegistry {

    private val beanDefinitions = mutableListOf<BeanDefinition>()
    private val beans = mutableMapOf<String, Any>()

    private val beanInitializer = BeanInitializers(*beanInitializerArgs)

    fun instantiate() {
        beanDefinitions.forEach { instantiateBeanDefinition(it) } }

    private fun instantiateBeanDefinition(beanDefinition: BeanDefinition): Any {
        if(beans[beanDefinition.name] != null) {
            return beans[beanDefinition.name]!!
        }

        val instance = beanInitializer.instantiate(beanDefinition, this)
        beans[beanDefinition.name] = instance
        return instance
    }

    override fun <T : Any> getBean(type: KClass<T>): T = beans[type.qualifiedName] as T

    override fun registerBeanDefinition(beanDefinition: BeanDefinition) {
        beanDefinitions.add(beanDefinition)
    }
}
