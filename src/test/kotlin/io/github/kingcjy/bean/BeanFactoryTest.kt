package io.github.kingcjy.bean

import io.github.kingcjy.bean.definition.ClassBeanDefinition
import io.github.kingcjy.bean.initializer.ClassBeanInitializer
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test


/**
 *  Created by KingCjy on 2020/10/19
 *  Github: https://github.com/KingCjy
 */
class BeanFactoryTest {

    @Test
    fun instantiateBeanTest() {
        val beanFactory = DefaultBeanFactory(ClassBeanInitializer())
        beanFactory.registerBeanDefinition(ClassBeanDefinition(TestComponent::class))
        beanFactory.instantiate()

        val testComponent = beanFactory.get<TestComponent>()

        assertThat(testComponent.getName()).isEqualTo(TestComponent::class.qualifiedName)
    }

    @Test
    fun instantiateDependencyBeanTest() {
        val beanFactory = DefaultBeanFactory(ClassBeanInitializer())
        beanFactory.registerBeanDefinition(ClassBeanDefinition(TestComponent1::class))
        beanFactory.registerBeanDefinition(ClassBeanDefinition(TestComponent2::class))
        beanFactory.instantiate()

        val testComponent = beanFactory.getBean(TestComponent1::class)

        assertThat(testComponent.getName()).isEqualTo(TestComponent2::class.qualifiedName)
    }

    class TestComponent {
        fun getName() = this::class.qualifiedName
    }

    class TestComponent1(
        val testComponent2: TestComponent2
    ) {
        fun getName() = testComponent2.getName()
    }

    class TestComponent2 {
        fun getName() = this::class.qualifiedName
    }
}